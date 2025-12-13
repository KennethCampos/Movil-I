package com.example.myapplication

import Controller.RecipeController
import Entity.Recipe
import Util.EXTRA_RECETA_ID
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_recipe_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe_detail)

        // Referencias correctas
        val imgPhoto = findViewById<ImageView>(R.id.imgRecipePhoto)
        val txtName = findViewById<TextView>(R.id.txtRecipeName)
        val txtCategory = findViewById<TextView>(R.id.txtRecipeCategory)
        val txtDescription = findViewById<TextView>(R.id.txtRecipeDescription)
        val txtIngredients = findViewById<TextView>(R.id.txtRecipeIngredients)
        val txtSteps = findViewById<TextView>(R.id.txtRecipeSteps)

       //sacar el id
        val id = intent.getStringExtra(EXTRA_RECETA_ID)

        if (id == null) {
            txtName.text = "Error: ID nulo"
            return
        }

       //llama al controller y busca el id
        val controller = RecipeController(this)
        val recipe: Recipe? = controller.getById(id)

        if (recipe == null) {
            txtName.text = "Error: Receta no encontrada"
            return
        }

      //mostrar datos
        txtName.text = recipe.Name
        txtCategory.text = recipe.Category
        txtSteps.text = recipe.Steps
        txtDescription.text = recipe.Description
        txtIngredients.text = recipe.Ingredients

        recipe.Photo?.let {
            imgPhoto.setImageBitmap(it)
        }

        val btnEdit = findViewById<Button>(R.id.btnEditRecipe)


        btnEdit.setOnClickListener {
            if (id != null) {
                Util.Util.openActivity(
                    this,
                    Activity_agregar_receta::class.java,
                    EXTRA_RECETA_ID,
                    id
                )
            }
        }

    }
}
