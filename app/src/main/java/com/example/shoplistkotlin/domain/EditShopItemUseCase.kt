package com.example.shoplistkotlin.domain

import com.example.shoplistkotlin.domain.model.ShopItem

class EditShopItemUseCase(private val repository: ShopListRepository) {

     fun editShopItem(shopItem: ShopItem) {
        repository.editShopItem(shopItem)

    }
}