package com.example.myapplication

import Entity.Recipe
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import interfaces.OnItemClickListener

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtName: TextView = view.findViewById(R.id.txtPersonNameItem_recycler)

    val txtCategory: TextView = view.findViewById(R.id.txtAddressItem_recycler)
    val txtsteps: TextView = view.findViewById(R.id.txtPhoneItem_recycler)
    val imgPhoto: ImageView = view.findViewById(R.id.imgPhoto_ItemRecycler)

    fun bind(item: Recipe, clickListener: OnItemClickListener) {
        txtName.setText(item.Name.toString())
        txtCategory.setText(item.Category.toString())
        txtsteps.setText(item.Steps.toString())
        imgPhoto.setImageBitmap(item.Photo)


        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}

class RecipeListAdapter(
    private val itemList: List<Recipe>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        var item =itemList[position]
        holder.bind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}
