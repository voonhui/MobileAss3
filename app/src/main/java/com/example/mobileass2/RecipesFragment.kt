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
import com.example.mobileass2.Adapter.DirectionAdapter
import com.example.mobileass2.Adapter.IngredientAdapter
import com.example.mobileass2.dataClass.direction
import com.example.mobileass2.dataClass.ingredient
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class RecipesFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var recyclerView2: RecyclerView? = null
    var ingredientList = ArrayList<ingredient>()
    var directionList = ArrayList<direction>()
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
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        val t1: TextView = view.findViewById(R.id.t1)
        val t2: TextView = view.findViewById(R.id.t2)
        val time: TextView = view.findViewById(R.id.recipe_time)
        val img: ImageView = view.findViewById(R.id.imageView2)
        val cal: TextView = view.findViewById(R.id.cal)
        val fat: TextView = view.findViewById(R.id.fat)
        val pro: TextView = view.findViewById(R.id.pro)
        val carbs: TextView = view.findViewById(R.id.carbs)

        val args = this.arguments
        val meal_name = args?.get("mealName")
        val meal_ID = args?.get("mealID")
        val meal_image = args?.get("mealImage")

        t1.text = meal_name.toString()
        Picasso.get().load(meal_image.toString()).into(img)

        val rootRef = FirebaseDatabase.getInstance().reference
        rootRef.child("Recipes").child(meal_ID.toString()).get().addOnSuccessListener {
            if (it.exists()){
                val details = it.child("details").value.toString()
                val time_meal = it.child("time").value.toString()
                val calories = it.child("cal").value.toString()
                val fat_recipes = it.child("fat").value.toString()
                val protein = it.child("pro").value.toString()
                val carbs_recipes = it.child("carbs").value.toString()


                t2.text = details
                time.text = time_meal
                cal.text = calories
                fat.text = fat_recipes
                pro.text = protein
                carbs.text = carbs_recipes

            }
        }

        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("Recipes")?.child(meal_ID.toString())

        val FirebaseListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.child("ingredient").children
                child.forEach{
                    var items = ingredient(it.child("quantity").value.toString(),
                        it.child("name").value.toString(),
                        )

                    ingredientList.add(items)
                }

                val dir = snapshot.child("direction").children
                dir.forEach{
                    var items = direction(it.child("step").value.toString(),

                    )
                    directionList.add(items)
                }
                val adapter = IngredientAdapter(ingredientList)
                val adapter2 = DirectionAdapter(directionList)
                recyclerView2?.adapter = adapter2
                recyclerView?.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("test",error.message)
            }

        }

        reference?.addValueEventListener(FirebaseListener)


        recyclerView = view.findViewById(R.id.ingredient_recycler_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)

        recyclerView2 = view.findViewById(R.id.direction_recycler_view)
        recyclerView2?.setHasFixedSize(true)
        recyclerView2?.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)

       // recyclerView?.layoutManager = object : LinearLayoutManager(context){
       //     override fun canScrollVertically(): Boolean {
        //        return false
        //    }
       // }

        return view
    }


}