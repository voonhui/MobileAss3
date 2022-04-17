package com.example.mobileass2.Sport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mobileass2.R

class SportActivity : Fragment() {

    private lateinit var buttonStart : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_sport_activity, container, false)

        buttonStart = view.findViewById(R.id.buttonStart)

        buttonStart.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.sportStart)
        }

        //Navigation.findNavController(view).popBackStack()

        return view
    }

}