package com.example.shoplistkotlin.presentation.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistkotlin.R
import com.example.shoplistkotlin.domain.model.ShopItem

class TaskListAdapter() :
    ListAdapter<ShopItem,
            TaskListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {
    var onShopClickListener: ((ShopItem) -> Unit)? = null
    var onShopLongClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_DISABLE -> R.layout.shop_list_disable
            VIEW_TYPE_ENABLE -> R.layout.shop_list_enable
            else -> throw RuntimeException("Unknown view type: $viewType")

        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopitem = getItem(position)
        holder.onBind(shopitem)

        holder.itemView.setOnClickListener {
            onShopClickListener?.invoke(shopitem)
        }

        holder.itemView.setOnLongClickListener {
            onShopLongClickListener?.invoke(shopitem)
            return@setOnLongClickListener true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!getItem(position).enabled) {
            VIEW_TYPE_DISABLE

        } else {
            VIEW_TYPE_ENABLE
        }
    }

    class ShopItemViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {

        fun onBind(shopItem: ShopItem) {

            view.findViewById<TextView>(R.id.tv_name).text = shopItem.name
            view.findViewById<TextView>(R.id.tv_count).text = shopItem.count.toString()
        }

    }

    companion object {

        const val VIEW_TYPE_ENABLE = 101
        const val VIEW_TYPE_DISABLE = 100

    }
}