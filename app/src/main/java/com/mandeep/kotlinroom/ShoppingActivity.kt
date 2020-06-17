package com.mandeep.kotlinroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandeep.kotlinroom.adapter.ShoppingAdapter
import com.mandeep.kotlinroom.db.entities.ShoppingItem
import com.mandeep.kotlinroom.viewmodel.ShoppingViewModel
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity(private val TAG: String = "ShoppingActivity") : AppCompatActivity() {

    private var itemList: List<ShoppingItem> = emptyList()
    private val addItemRequestCode = 100
    private lateinit var shoppingViewModel: ShoppingViewModel

    companion object {
        const val itemName = "new_item"
        const val itemNumber = "item_quantity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        // init viewModel
        shoppingViewModel = ViewModelProvider(this).get(ShoppingViewModel::class.java)

        // set adapter and init recyclerView
        val shoppingAdapter = ShoppingAdapter(shoppingViewModel)
        listRvMain.apply {
            layoutManager = LinearLayoutManager(this@ShoppingActivity)
            adapter = shoppingAdapter
        }

        // observe data changes in db
        shoppingViewModel.getAllItems.observe(this, Observer { items ->
            items?.let {
                itemList = it
                shoppingAdapter.notifyDataSetChanged()
                Log.d(TAG, "onCreate: " + it.size + " " + itemList.size)
                for (item in itemList) {
                    Log.d(TAG, "onCreate: $item")
                }
                shoppingAdapter.setListItems(itemList)
            }
        })

        // add item
        addFabMain.setOnClickListener {
            startActivityForResult(
                Intent(this@ShoppingActivity, AddItemActivity::class.java),
                addItemRequestCode
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if ((requestCode == addItemRequestCode) and (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                val itemName = data.getStringExtra(itemName) ?: ""
                val itemNumber = data.getIntExtra(itemNumber, 0)
                addItemToDb(itemName, itemNumber)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addItemToDb(itemName: String, itemNumber: Int) {
        if (this::shoppingViewModel.isInitialized) {
            shoppingViewModel.addItem(ShoppingItem(itemName, itemNumber))
        } else Toast.makeText(this, "ViewModel not initialised", Toast.LENGTH_SHORT).show()
    }
}