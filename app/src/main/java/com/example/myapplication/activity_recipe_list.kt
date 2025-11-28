package com.example.myapplication

import Controller.RecipeController
import Entity.Recipe
import Util.EXTRA_RECETA_ID
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import interfaces.OnItemClickListener
import kotlin.jvm.java
import Util.Util

class activity_recipe_list : AppCompatActivity(), OnItemClickListener {


    private lateinit var customAdapter: RecipeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val  recycler = findViewById<RecyclerView>(R.id.rvperson)
        val controller = RecipeController(this)

        customAdapter = RecipeListAdapter(controller.getRecipe(), this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recycler.layoutManager = layoutManager
        recycler.adapter = customAdapter
        customAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(recipe: Recipe) {
        Util.openActivity(this, Activity_agregar_receta::class.java, EXTRA_RECETA_ID, recipe.ID)
        //Toast.makeText(this,"Person name ${person.FullName()} \n Phone:${person.Phone.toString()}"
        //    ,Toast.LENGTH_LONG).show()
        //Log.i("CONTACT", contact.FullName)
   }
}
