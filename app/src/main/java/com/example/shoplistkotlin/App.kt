package com.example.shoplistkotlin

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoplistkotlin.data.repositries.AppDataBase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this,AppDataBase::class.java,"dataBase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    companion object{
        lateinit var dataBase: AppDataBase
    }


}