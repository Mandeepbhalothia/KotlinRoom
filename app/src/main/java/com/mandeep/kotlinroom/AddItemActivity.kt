package com.mandeep.kotlinroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mandeep.kotlinroom.ShoppingActivity.Companion.itemName
import com.mandeep.kotlinroom.ShoppingActivity.Companion.itemNumber
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        addItemBtn.setOnClickListener {
            val name = itemNameEt.text.toString()
            val number: Int
            try {
                number = itemNumberEt.text.toString().toInt()
                if (name.isNotEmpty()) {
                    val intent = Intent()
                    intent.putExtra(itemName, name)
                    intent.putExtra(itemNumber, number)

                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else Toast.makeText(this, "Enter Proper Data", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Enter Proper Data", Toast.LENGTH_SHORT).show()
            }
        }

    }
}