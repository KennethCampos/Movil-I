package com.example.myapplication

import Entity.Recipe
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMain = findViewById<Button>(R.id.btnListRecipe)
        btnMain.setOnClickListener(View.OnClickListener{ view->
            Util.Util.openActivity(this
                , Activity_agregar_receta::class.java)
        })

        val btnPersonList_main = findViewById<Button>(R.id.RecipeList_main)
        btnPersonList_main.setOnClickListener(View.OnClickListener{ view->
            Util.Util.openActivity(this
                , activity_recipe_list::class.java)
        })




    }

}