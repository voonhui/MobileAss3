package com.example.mobileass2.Sport

import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mobileass2.R
import com.google.firebase.database.*

class Sport : Fragment() {

    private lateinit var imageButtonSport1: ImageButton
    private lateinit var imageButtonSport2: ImageButton
    private lateinit var imageButtonSport5: ImageButton
    private lateinit var imageButtonSport6: ImageButton
    private lateinit var imageButtonSport7: ImageButton
    private lateinit var imageButtonSport9: ImageButton
    private lateinit var dbRef : DatabaseReference
    //private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Views")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sport, container, false)

       //firebase count
       val rootRef = FirebaseDatabase.getInstance().reference
       val pressedRef = rootRef.child("Sport View")

        imageButtonSport1 = view.findViewById(R.id.imageButtonSport1)
        imageButtonSport1.setOnClickListener{
            findNavController().navigate(R.id.action_sport_nav_to_sportActivity2)
            pressedRef.child("7 Min Workout").setValue(ServerValue.increment(1))

        }

        imageButtonSport2 = view.findViewById(R.id.imageButtonSport2)
        imageButtonSport2.setOnClickListener {
            findNavController().navigate(R.id.sportHiit)
            pressedRef.child("HIIT").setValue(ServerValue.increment(1))
        }

        imageButtonSport5 = view.findViewById(R.id.imageButtonSport5)
        imageButtonSport5.setOnClickListener {
            findNavController().navigate(R.id.sportFocusBody)
            pressedRef.child("FocusWaist").setValue(ServerValue.increment(1))
        }

        imageButtonSport6 = view.findViewById(R.id.imageButtonSport6)
        imageButtonSport6.setOnClickListener {
            findNavController().navigate(R.id.sportArm)
            pressedRef.child("FocusArm").setValue(ServerValue.increment(1))
        }

        imageButtonSport7 = view.findViewById(R.id.imageButtonSport7)
        imageButtonSport7.setOnClickListener {
            findNavController().navigate(R.id.sportLeg)
            pressedRef.child("FocusLeg").setValue(ServerValue.increment(1))
        }

        imageButtonSport9 = view.findViewById(R.id.imageButtonSport9)
        imageButtonSport9.setOnClickListener {
            findNavController().navigate(R.id.sportBodyRelax2)
            pressedRef.child("Relax").setValue(ServerValue.increment(1))
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

}