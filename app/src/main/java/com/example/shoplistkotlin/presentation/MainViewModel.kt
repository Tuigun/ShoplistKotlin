package com.example.shoplistkotlin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistkotlin.data.repositries.ShopListRepositoryImpl
import com.example.shoplistkotlin.domain.*
import com.example.shoplistkotlin.domain.model.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUsCase = GetShopListUsCase(repository)
    private val delateShopItemUseCase = DelateShopItemUseCase(repository)
    private val shopList = getShopListUsCase.getShopList()
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUsecase = GetShopItemUsecase(repository)


    fun addshopItem(shopItem: ShopItem) {
        addShopItemUseCase.addshopItem(shopItem)

    }

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    fun delateItem(shopItem: ShopItem) {
        viewModelScope.launch {
            delateShopItemUseCase.delateShopItem(shopItem)

        }

    }

     fun editShopItem(shopItem: ShopItem) {
         val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }

    fun getShopItem(id: Int): ShopItem {
      return getShopItemUsecase.getShopItem(id)
    }

}