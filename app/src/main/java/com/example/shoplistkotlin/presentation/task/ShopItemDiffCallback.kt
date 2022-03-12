package com.example.shoplistkotlin.presentation.task

import androidx.recyclerview.widget.DiffUtil
import com.example.shoplistkotlin.domain.model.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean = (oldItem == newItem)
}