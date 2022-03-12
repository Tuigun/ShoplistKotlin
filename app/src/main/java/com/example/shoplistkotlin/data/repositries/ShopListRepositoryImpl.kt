package com.example.shoplistkotlin.data.repositries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shoplistkotlin.App
import com.example.shoplistkotlin.data.ShopListMapper
import com.example.shoplistkotlin.domain.ShopListRepository
import com.example.shoplistkotlin.domain.model.ShopItem
import com.example.shoplistkotlin.domain.model.ShopItemDbModel
import kotlin.random.Random

class ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private val shoplistLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 1
    private val mapper = ShopListMapper()



    override fun addshopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem))

    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        App.dataBase.shopListDao().getShopList()
    ) {
        mapper.mapListDbModelListEntity(it)
    }

    override suspend fun delateShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().deleteShopitem(shopItem.id)
        updateList()

    }

    private fun updateList() {
        shoplistLD.value = shopList.toList()

    }

    override  fun editShopItem(shopItem: ShopItem) {
//        App.dataBase.shopListDao().deleteShopitem(shopItem.id)
//        App.dataBase.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem))
        App.dataBase.shopListDao().updateShopItem(mapper.mapEntityToDbModel(shopItem))

    }

    override fun getShopItem(id: Int): ShopItem {
        return mapper.mapDbModelEntity(App.dataBase.shopListDao().getShopItem(id))
    }
}

