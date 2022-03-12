package com.example.shoplistkotlin.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoplistkotlin.R
import com.example.shoplistkotlin.databinding.ActivityMainBinding
import com.example.shoplistkotlin.databinding.ShopListDisableBinding
import com.example.shoplistkotlin.domain.model.ShopItem
import com.example.shoplistkotlin.presentation.MainViewModel
import com.example.shoplistkotlin.presentation.task.TaskAdapter

class MainActivity : AppCompatActivity(R.layout.activity_task) {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var adapter: TaskAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initListeners()
        setupRv()
        initObservers()
    }

    private fun setupRv() {
        adapter = TaskAdapter()

    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
            Log.e("TAG", "getShopLiveData:$it")
        }

    }

    private fun initListeners() {
        binding.getListBtn.setOnClickListener {
        }
        binding.createItem.setOnClickListener {
            viewModel.addshopItem(addItem())
        }
        binding.delateItem.setOnClickListener {
            viewModel.delateItem(
                ShopItem(
                    "potato", 2, false,
                    binding.edit.text.toString().toInt()
                )

            )
        }
        binding.delateItem.setOnClickListener{

        }
        binding.EditBtn.setOnClickListener() {
//            viewModel.editShopItem {
//                val text = binding.EditBtn.text.toString()
//                if (text.isBlank())
//                    try {
////                        viewModel.editShopItem(text.toInt())
//                        Log.d("this", "initListeners: ")
//                    } catch (e: Exception) {
//                        Log.d("this", "Exeption: $e ")
//                    }
//            }
        }

    }

    private fun addItem(): ShopItem {
        Log.e("TAG", "addShopItem:")

        val text = binding.edit.text.toString()
        return if (text.isEmpty()) {
            ShopItem("potato", 2, false)
        } else {
            ShopItem(
                "potato",
                2,
                false,
                text.toInt()
            )
        }
    }

    companion object {

        fun start(context: Context){
            context.startActivity(Intent(context,MainActivity::class.java))
        }

    }

}
