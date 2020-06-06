package com.goyal.awok.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.snackbar.Snackbar
import com.goyal.awok.R
import com.goyal.awok.R.layout
import com.goyal.awok.arch.BaseActivity
import com.goyal.awok.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.pb_horizontal
import kotlinx.android.synthetic.main.activity_home.pb_vertical
import kotlinx.android.synthetic.main.activity_home.rv_horizontal
import kotlinx.android.synthetic.main.activity_home.rv_vertical

class HomeActivity : BaseActivity() {

  private lateinit var viewModel: HomeViewModel
  private lateinit var verticalAdapter: VerticalListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_home)
    initComponents()
  }

  override fun onDestroy() {
    super.onDestroy()
    viewModel.cancelApiCall()
  }

  private fun initComponents() {
    viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    viewModel.setRepository(compRoot()!!.getHomeRepository())
    verticalAdapter = VerticalListAdapter(this)
    rv_vertical.apply {
      layoutManager = LinearLayoutManager(this@HomeActivity)
      adapter = verticalAdapter
    }
    pb_horizontal.visibility = View.VISIBLE
    pb_vertical.visibility = View.VISIBLE
    startObserving()
    performPagination()
  }

  private fun startObserving() {
    viewModel.liveDataVerticalList.observe(this, Observer {
      pb_vertical.visibility = View.GONE
      if (it != null) {
        verticalAdapter.setData(it)
      } else {
        Toast.makeText(this, getString(R.string.err_vertical), Toast.LENGTH_SHORT)
            .show()
      }
    })
    viewModel.liveDataHorizontalList.observe(this, Observer {
      pb_horizontal.visibility = View.GONE
      if (it != null) {
        rv_horizontal.apply {
          adapter =
            HorizontalListAdapter(it, this@HomeActivity)
        }
      } else {
        Toast.makeText(this, getString(R.string.err_horizontal), Toast.LENGTH_SHORT)
            .show()
      }
    })
    viewModel.liveDataErrorMessage.observe(this, Observer {
      if (it != null) {
        pb_horizontal.visibility = View.GONE
        pb_vertical.visibility = View.GONE
        Snackbar.make(findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.RED)
            .show()
      }
    })
  }

  private fun performPagination() {
    rv_vertical.addOnScrollListener(object : OnScrollListener() {
      override fun onScrollStateChanged(
        recyclerView: RecyclerView,
        newState: Int
      ) {
        super.onScrollStateChanged(recyclerView, newState)
        if (!recyclerView.canScrollVertically(1)) {
          pb_vertical.visibility = View.VISIBLE
          viewModel.performPagination()
        }
      }
    })
  }
}
