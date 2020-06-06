package com.goyal.awok.view

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goyal.awok.R
import com.goyal.awok.model.schema.Item
import com.goyal.awok.view.VerticalListAdapter.VerticalItemViewHolder
import kotlinx.android.synthetic.main.item_product_vertical.view.*

class VerticalListAdapter(private val context: Context) :
    RecyclerView.Adapter<VerticalItemViewHolder>() {

  private var list : ArrayList<Item> = ArrayList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): VerticalItemViewHolder {
    return VerticalItemViewHolder(
        LayoutInflater.from(context)
            .inflate(R.layout.item_product_vertical, parent, false)
    )
  }

  override fun onBindViewHolder(
    holder: VerticalItemViewHolder,
    position: Int
  ) {
    val item: Item = list[position]
    holder.tvProductName.text = item.name
    holder.tvOldPrice.text = getPrice(item.prices.oldPrice)
    holder.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    holder.tvNewPrice.text = getPrice(item.prices.newPrice)
    holder.tvOffer.text = getOffer(getPrice(item.prices.discount))
    Glide.with(context)
        .load(item.image.imageSource)
        .centerCrop()
        .placeholder(R.drawable.ic_place_holder)
        .error(R.drawable.ic_place_holder)
        .fallback(R.drawable.ic_place_holder)
        .into(holder.ivProduct)
    holder.btnAddToCart.setOnClickListener {
      Toast.makeText(
          context, item.name + context.getString(R.string.added_to_cart), Toast.LENGTH_SHORT
      )
          .show()
    }
  }

  fun setData(items: List<Item>) {
    list.addAll(items)
    notifyDataSetChanged()
  }

  private fun getPrice(price: String): String {
    return context.getString(R.string.aed) + price
  }

  private fun getOffer(price: String): String {
    return price + context.getString(R.string.off)
  }

  override fun getItemCount(): Int = list.size

  class VerticalItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvProductName: AppCompatTextView = view.tv_product_name
    val tvOldPrice: AppCompatTextView = view.tv_old_price
    val tvNewPrice: AppCompatTextView = view.tv_new_price
    val tvOffer: AppCompatTextView = view.tv_offer
    val ivProduct: AppCompatImageView = view.iv_product
    val btnAddToCart: AppCompatButton = view.btn_add_to_cart
  }
}