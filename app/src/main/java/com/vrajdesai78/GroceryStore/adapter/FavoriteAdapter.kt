package com.vrajdesai78.GroceryStore.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vrajdesai78.GroceryStore.R
import com.vrajdesai78.GroceryStore.listener.OnClickItem
import com.vrajdesai78.GroceryStore.model.product.ProductEntity
import kotlinx.android.synthetic.main.item_favorite.view.*
import java.text.DecimalFormat

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val list: MutableList<ProductEntity> = mutableListOf()
    var onClickListener: OnClickItem? =null

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(productEntity: ProductEntity) {

            val price = productEntity.price
            val dec = DecimalFormat("#,###")
            val priceRupiah = dec.format(price)

            itemView.setOnClickListener {
                onClickListener?.onClick(productEntity)
            }

            Glide.with(itemView)
                .load(productEntity.picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(itemView.iv_picture_favorite)
            itemView.tv_name_favorite.text = productEntity.name
            itemView.tv_description_favorite.text = productEntity.description
            itemView.tv_price_favorite.text = "INR $priceRupiah"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deleteItem(pos:Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun setDataAdapter(data: List<ProductEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}