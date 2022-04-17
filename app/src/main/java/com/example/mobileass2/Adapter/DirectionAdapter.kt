package com.example.mobileass2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileass2.R
import com.example.mobileass2.dataClass.direction

class DirectionAdapter (private var direction: MutableList<direction>): RecyclerView.Adapter<DirectionAdapter.DirectionViewHolder>(){
    class DirectionViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var step: TextView = view.findViewById(R.id.direction_step)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DirectionViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).inflate(R.layout.direction_view,parent,false)
        return DirectionViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: DirectionViewHolder, position: Int) {
        holder.step.text = direction[position].step
    }

    override fun getItemCount(): Int {
        return direction.size
    }

}