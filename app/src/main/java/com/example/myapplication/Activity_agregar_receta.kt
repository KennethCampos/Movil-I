package com.example.myapplication

import Controller.RecipeController
import Entity.Recipe
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_agregar_receta : AppCompatActivity() {

    lateinit var txtId: EditText
    lateinit var txtName: EditText
    lateinit var txtCategory: EditText
    lateinit var txtIngredients: EditText
    lateinit var txtDescription: EditText
    lateinit var txtSteps: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_receta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtId = findViewById<EditText>(R.id.txtId_Agregar)
        txtName = findViewById<EditText>(R.id.txtName_Agregar)
        txtCategory = findViewById<EditText>(R.id.txtCategory_Agregar)
        txtIngredients = findViewById<EditText>(R.id.txtIngredientes_Agregar)
        txtDescription = findViewById<EditText>(R.id.txtdescription_Agregar)
        txtSteps = findViewById<EditText>(R.id.txtSteps_Agregar)


        val btnSave = findViewById<Button>(R.id.btnSave_Agregar)
        btnSave.setOnClickListener(View.OnClickListener{view ->
            saveRecipe()
        })
    }

    fun validateData(): Boolean {
        return  true
    }


    fun saveRecipe() {
        try {
            val recipe = Recipe()
            recipe.ID = txtId.text.toString()
            recipe.Name = txtName.text.toString()
            recipe.Category = txtCategory.text.toString()
            recipe.Ingredients = txtIngredients.text.toString()
            recipe.Description = txtDescription.text.toString()
            recipe.Steps = txtSteps.text.toString()

            if (validateData()) {
                val recipeController = RecipeController(this)
                recipeController.addRecipe(recipe)

                Toast.makeText(this, "Recipe added successfully!", Toast.LENGTH_LONG).show()
                clearFields()
            } else {
                Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }


    fun clearFields() {
        txtId.text.clear()
        txtName.text.clear()
        txtCategory.text.clear()
        txtIngredients.text.clear()
        txtDescription.text.clear()
        txtSteps.text.clear()
    }

}