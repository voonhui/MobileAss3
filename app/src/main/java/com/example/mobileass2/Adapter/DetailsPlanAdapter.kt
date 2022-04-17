package com.example.mobileass2.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileass2.R
import com.example.mobileass2.RecipesFragment
import com.example.mobileass2.dataClass.detailsPlan
import com.squareup.picasso.Picasso

class DetailsPlanAdapter(private var detailsPlan: MutableList<detailsPlan>): RecyclerView.Adapter<DetailsPlanAdapter.DetailsPlanViewHolder>() {
    class DetailsPlanViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.imageD1)
        var mealName: TextView = view.findViewById(R.id.mealD1)
        var details: TextView = view.findViewById(R.id.detailsD1)
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsPlanViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).inflate(R.layout.diet_details_view,parent,false)
        return DetailsPlanViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: DetailsPlanViewHolder, position: Int) {
        val item = detailsPlan[position]
        Picasso.get().load(detailsPlan[position].image).into(holder.image)
        holder.mealName.text = detailsPlan[position].mealName
        holder.details.text = detailsPlan[position].details

        holder.itemView.setOnClickListener (object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val bundle = Bundle()
                bundle.putString("mealID",item.mealID)
                bundle.putString("mealName",item.mealName)
                bundle.putString("mealImage",item.image)

                val activity=p0!!.context as AppCompatActivity
                val r = RecipesFragment()
                r.arguments = bundle
                activity.supportFragmentManager.beginTransaction().replace(R.id.detailsF,r,null).addToBackStack(null).commit()


            }

        })
    }

    override fun getItemCount(): Int {
        return detailsPlan.size
    }
}