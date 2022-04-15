package com.example.mobileass2

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //firebase
        // Write a message to the database
        val database = Firebase.database("https://mobileassfirebase-default-rtdb.firebaseio.com/")
        val myRef = database.getReference("Sport")

        myRef.setValue("Hello, World!")
        myRef.child("first").setValue("tag 1")
        myRef.child("second").setValue("tag 2")
        myRef.child("third").setValue("tag 3")
        myRef.child("fourth").setValue("tag 4")
        myRef.child("fifth").setValue("tag 5")

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue()
                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)

        val navController = findNavController(R.id.container)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.sport_nav, R.id.diet_nav, R.id.profile_nav
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        setupWithNavController(bottomNavigationView, navController)

        //button click
        //imageButtonSport1.setOnClickListener{
          //  val intent = Intent(this, SportActivity::class.java)
        //}
    }

    fun handleSelection(view: View) {
        Toast.makeText(this, "Image is tapped", Toast.LENGTH_LONG).show()
    }
}