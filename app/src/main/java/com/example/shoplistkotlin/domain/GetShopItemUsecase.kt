package com.example.shoplistkotlin.domain

import com.example.shoplistkotlin.domain.model.ShopItem

class GetShopItemUsecase(private val repository: ShopListRepository) {

    fun getShopItem(id: Int): ShopItem{
       return repository.getShopItem(id)
    }
}