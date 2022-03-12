package com.example.shoplistkotlin.presentation.task

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoplistkotlin.R
import com.example.shoplistkotlin.databinding.ActivityTaskBinding
import com.example.shoplistkotlin.presentation.MainViewModel

class TaskActivity : AppCompatActivity(R.layout.activity_task) {

    private val binding: ActivityTaskBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var shopListAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        initObservers()
        initLesteners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Print ID:"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(id: String?): Boolean {
                id?.toInt()?.let {

                    val text = viewModel.getShopItem(it)

                    Toast.makeText(this@TaskActivity, text.id.toString(), Toast.LENGTH_SHORT).show()

                }
                return false

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)

    }


    private fun initLesteners() {

        shopListAdapter.onShopClickListener = {

            Toast.makeText(applicationContext, it.id.toString(), Toast.LENGTH_SHORT).show()

        }

        binding.fab.setOnClickListener {
            DetailActivity.start(this)
        }

        shopListAdapter.onShopLongClickListener = {

            viewModel.editShopItem(it)

        }

    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
//            adapterTask.list = it
            shopListAdapter.submitList(it)
        }


    }

    private fun setUpRecyclerView() {
        shopListAdapter = TaskListAdapter()
        binding.taskRv.layoutManager = LinearLayoutManager(this)
        binding.taskRv.apply {
            adapter = shopListAdapter
        }
        SetUpSwipeListener(binding.taskRv)

    }

    private fun SetUpSwipeListener(taskRv: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.delateItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(taskRv)
    }

}