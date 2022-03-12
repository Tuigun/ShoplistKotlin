package com.example.shoplistkotlin.data

import com.example.shoplistkotlin.domain.model.ShopItem
import com.example.shoplistkotlin.domain.model.ShopItemDbModel

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(

        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled


    )


    fun mapDbModelEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled
    )


    fun mapListDbModelListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelEntity(it)

    }


}
