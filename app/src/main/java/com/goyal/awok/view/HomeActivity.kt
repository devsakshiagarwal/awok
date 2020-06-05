package com.goyal.awok.view

import android.os.Bundle
import com.goyal.awok.R.layout
import com.goyal.awok.arch.BaseActivity

class HomeActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_home)

  }
}
