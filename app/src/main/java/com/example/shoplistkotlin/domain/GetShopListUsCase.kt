package com.example.shoplistkotlin.domain

import androidx.lifecycle.LiveData
import com.example.shoplistkotlin.domain.model.ShopItem

class GetShopListUsCase(private val repository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>>{
        return repository.getShopList()
    }
}