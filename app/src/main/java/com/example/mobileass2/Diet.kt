package com.example.mobileass2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileass2.Adapter.DietAdapter
import com.example.mobileass2.dataClass.dietItem
import com.google.firebase.database.*



class Diet : Fragment() {
    var recyclerView: RecyclerView? = null
    var planList = ArrayList<dietItem>()
    private var database: FirebaseDatabase? =null
    private var reference: DatabaseReference? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view = inflater.inflate(R.layout.fragment_diet, container, false)

        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("Diet")


        val FirebaseListener = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.children
                child.forEach{
                    var items = dietItem(it.child("planID").value.toString(),
                        it.child("image").value.toString(),
                    it.child("name").value.toString(),
                    it.child("views").value.toString(),
                        it.child("details").value.toString(),
                    )

                    planList.add(items)
                }
                val adapter = DietAdapter(planList)
                recyclerView?.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("test",error.message)
            }

        }
        reference?.addValueEventListener(FirebaseListener)


        recyclerView = view.findViewById(R.id.diet_plan_recycle_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        return view
    }


}