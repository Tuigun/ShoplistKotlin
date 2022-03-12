package com.example.shoplistkotlin.domain

import com.example.shoplistkotlin.domain.model.ShopItem

class AddShopItemUseCase(private val repository: ShopListRepository) {

    fun addshopItem(shopItem: ShopItem){
        repository.addshopItem(shopItem)

    }
}