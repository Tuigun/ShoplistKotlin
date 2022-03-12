package com.example.shoplistkotlin.domain

import com.example.shoplistkotlin.domain.model.ShopItem

class DelateShopItemUseCase(private val repository: ShopListRepository) {
   suspend fun delateShopItem(shopItem: ShopItem){
        repository.
        delateShopItem(shopItem)

    }

}





