package com.example.shoplistkotlin.presentation.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistkotlin.R
import com.example.shoplistkotlin.domain.model.ShopItem

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var list = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallback(list, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shop_list_enable,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.tv_name)
        private var count: TextView = itemView.findViewById(R.id.tv_count)

        fun onBind(shopItem: ShopItem) {
            title.text = shopItem.name
            count.text = shopItem.count.toString()
        }
    }
}

