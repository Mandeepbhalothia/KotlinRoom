package com.mandeep.kotlinroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.kotlinroom.R
import com.mandeep.kotlinroom.db.entities.ShoppingItem
import com.mandeep.kotlinroom.viewmodel.ShoppingViewModel
import kotlinx.android.synthetic.main.item_rv.view.*

class ShoppingAdapter(
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    private var items: List<ShoppingItem> = emptyList()
    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemView.itemName.text = currentItem.name
        holder.itemView.itemNo.text = currentItem.number.toString()

        holder.itemView.deleteItem.setOnClickListener { viewModel.deleteItem(currentItem) }
        holder.itemView.addItem.setOnClickListener {
            currentItem.number += 1
            holder.itemView.itemNo.text = currentItem.number.toString()
            viewModel.addItem(currentItem)
        }

        holder.itemView.minusItem.setOnClickListener {
            if (currentItem.number > 0) {
                currentItem.number -= 1
                holder.itemView.itemNo.text = currentItem.number.toString()
                viewModel.addItem(currentItem)
            } else Toast.makeText(
                holder.itemView.context,
                "No Item available in cart",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun setListItems(itemList: List<ShoppingItem>){
        items = itemList
        notifyDataSetChanged()
    }

}