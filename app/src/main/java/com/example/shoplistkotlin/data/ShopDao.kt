package com.example.shoplistkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoplistkotlin.domain.model.ShopItemDbModel

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_item")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("SELECT * FROM shop_item WHERE id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): ShopItemDbModel

    @Query("DELETE FROM shop_item WHERE id=:shopItemId")
   suspend fun deleteShopitem(shopItemId: Int)
   @Update
   fun updateShopItem(shopItemDbModel: ShopItemDbModel)
}