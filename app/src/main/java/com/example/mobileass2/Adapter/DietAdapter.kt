package com.example.mobileass2.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileass2.DietDetailsFragment
import com.example.mobileass2.R
import com.example.mobileass2.dataClass.dietItem
import com.squareup.picasso.Picasso


class DietAdapter (private var dietItem: MutableList<dietItem>):RecyclerView.Adapter<DietAdapter.DietViewHolder>(){


    class DietViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var dietImage: ImageView= view.findViewById(R.id.diet_img)
        var dietName: TextView = view.findViewById(R.id.name)
        var dietViews: TextView = view.findViewById(R.id.views)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return DietViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        val item = dietItem[position]
        Picasso.get().load(dietItem[position].image).into(holder.dietImage)
        holder.dietName.text = dietItem[position].name
        holder.dietViews.text = dietItem[position].views
        holder.itemView.setOnClickListener (object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val bundle = Bundle()
                bundle.putString("name",item.name)
                bundle.putString("details",item.details)
                bundle.putString("img",item.image)
                bundle.putString("views",item.views)
                bundle.putString("planID",item.planID)

               val activity=p0!!.context as AppCompatActivity
               val d = DietDetailsFragment()
                d.arguments = bundle
               activity.supportFragmentManager.beginTransaction().replace(R.id.dietF,d).addToBackStack(null).commit()

            }

        })


    }

    override fun getItemCount(): Int {
        return dietItem.size
    }
}