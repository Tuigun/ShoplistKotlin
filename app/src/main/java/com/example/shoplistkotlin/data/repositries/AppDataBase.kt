package com.example.shoplistkotlin.data.repositries

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoplistkotlin.data.ShopDao
import com.example.shoplistkotlin.domain.model.ShopItemDbModel

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract fun shopListDao(): ShopDao

}