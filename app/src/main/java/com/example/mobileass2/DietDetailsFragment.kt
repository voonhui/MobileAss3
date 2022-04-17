package com.example.mobileass2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileass2.Adapter.DetailsPlanAdapter
import com.example.mobileass2.dataClass.detailsPlan
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.util.*


class DietDetailsFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var detailsPlanList = ArrayList<detailsPlan>()
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
        val view = inflater.inflate(R.layout.fragment_diet_details, container, false)

        val nameD: TextView = view.findViewById(R.id.nameD)
        val detailsD: TextView = view.findViewById(R.id.detailsD)
        val img: ImageView = view.findViewById(R.id.imagePlan)

        //
        val args = this.arguments
        val inputData = args?.get("name")
        val details = args?.get("details")
        val imagePlan = args?.get("img")

        val view_plan = args?.get("views")
        val planID = args?.get("planID")

        nameD.text = inputData.toString()
        detailsD.text = details.toString()
        Picasso.get().load(imagePlan.toString()).into(img)

        //
        val num = view_plan.toString().toInt() + 1

        val rootRef = FirebaseDatabase.getInstance().reference
       rootRef.child("Diet").orderByChild("planID").equalTo(planID.toString())
           .addListenerForSingleValueEvent(object :ValueEventListener{
               override fun onDataChange(snapshot: DataSnapshot) {
                   for (change in snapshot.children){
                      val planKey = change.key.toString()

                       rootRef.child("Diet").child(planKey).child("views").setValue(num.toString())
                   }

               }

               override fun onCancelled(error: DatabaseError) {
                   Log.d("test",error.message)
               }

           })

        //
        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("Plan")?.child(planID.toString())


        val FirebaseListener = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.children
                child.forEach{
                    var items = detailsPlan(it.child("details").value.toString(),
                        it.child("image").value.toString(),
                        it.child("mealID").value.toString(),
                        it.child("mealName").value.toString(),
                        )

                    detailsPlanList.add(items)
                }
                val adapter = DetailsPlanAdapter(detailsPlanList)
                recyclerView?.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("test",error.message)
            }

        }
        reference?.addValueEventListener(FirebaseListener)


        recyclerView = view.findViewById(R.id.detailsPlan_recycler_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)


        return view
    }


}