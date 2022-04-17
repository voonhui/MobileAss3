package com.example.mobileass2.Sport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mobileass2.R
import com.google.firebase.database.*
import kotlin.concurrent.fixedRateTimer

class SportDone : Fragment() {

    private lateinit var buttonFinish : Button
    private lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_sport_done, container, false)
        //firebase count
        val rootRef = FirebaseDatabase.getInstance().reference
        val pressedRef = rootRef.child("Total Sport Completed")

        buttonFinish = view.findViewById(R.id.buttonFinish)
        val textViewSport: TextView = view.findViewById(R.id.editTextCalories)

        textViewSport.setText("380 kcal")

        buttonFinish.setOnClickListener{

            Navigation.findNavController(view).navigate(R.id.sport_nav)
            pressedRef.child("Total").setValue(ServerValue.increment(1))
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Sport")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    //for (snapshot in snapshot.children) {
                    val sport = snapshot.getValue(SportData::class.java)
                    Log.d("TAG", "Views: $sport")
                    //}
                } else {
                    //Toast.makeText(context, "No Data to Shown", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Firebase failed", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}