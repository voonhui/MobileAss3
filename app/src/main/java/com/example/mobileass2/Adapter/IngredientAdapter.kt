package com.example.mobileass2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileass2.R
import com.example.mobileass2.dataClass.ingredient

class IngredientAdapter (private var ingredient: MutableList<ingredient>): RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>(){
    class IngredientViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var quantity: TextView = view.findViewById(R.id.quantity)
        var ingredientName: TextView = view.findViewById(R.id.ingredient_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_view,parent,false)
        return IngredientViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.ingredientName.text = ingredient[position].name
        holder.quantity.text = ingredient[position].quantity
    }

    override fun getItemCount(): Int {
        return ingredient.size
    }

}