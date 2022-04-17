package com.example.mobileass2.Sport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.mobileass2.R

class SportLeg : Fragment() {

    private lateinit var buttonStart4 : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_sport_leg, container, false)

        buttonStart4 = view.findViewById(R.id.buttonStart4)
        buttonStart4.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.sportStart)
        }

        return view
    }

}