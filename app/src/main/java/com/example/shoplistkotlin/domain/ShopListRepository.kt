package com.example.shoplistkotlin.domain

import androidx.lifecycle.LiveData
import com.example.shoplistkotlin.domain.model.ShopItem

interface ShopListRepository {

    fun addshopItem(shopItem: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

   suspend fun delateShopItem(shopItem: ShopItem)

     fun editShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int): ShopItem
}