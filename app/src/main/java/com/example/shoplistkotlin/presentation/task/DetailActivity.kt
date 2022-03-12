package com.example.shoplistkotlin.presentation.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoplistkotlin.R
import com.example.shoplistkotlin.databinding.ActivityDetailBinding
import com.example.shoplistkotlin.domain.model.ShopItem
import com.example.shoplistkotlin.presentation.MainViewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initListener()
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener{
            val name  = binding.etName.text.toString()
            val count = binding.edCount.text.toString().toInt()
            viewModel.addshopItem(ShopItem(name = name,count = count,false))

            if (name.isEmpty()){
                Toast.makeText(this, "Name is emty", Toast.LENGTH_SHORT).show()

            }
            if (count.equals(String)){
                Toast.makeText(this, "only count", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}