package com.example.myapplication

import Controller.RecipeController
import Entity.Recipe
import Util.Util
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class Activity_agregar_receta : AppCompatActivity() {

    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtCategory: EditText
    private lateinit var txtIngredients: EditText
    private lateinit var txtDescription: EditText
    private lateinit var txtSteps: EditText

    private lateinit var recipeController: RecipeController
    private var isEditMode: Boolean = false
    private lateinit var menuItemDelete: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_receta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar controlador
        recipeController = RecipeController(this)

        // Campos
        txtId = findViewById(R.id.txtId_Agregar)
        txtName = findViewById(R.id.txtName_Agregar)
        txtCategory = findViewById(R.id.txtCategory_Agregar)
        txtIngredients = findViewById(R.id.txtIngredientes_Agregar)
        txtDescription = findViewById(R.id.txtdescription_Agregar)
        txtSteps = findViewById(R.id.txtSteps_Agregar)

        //  Botón buscar receta
        val btnSearch = findViewById<ImageButton>(R.id.btnSearch_Receta)
        btnSearch.setOnClickListener {
            searchRecipe(txtId.text.toString().trim())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_crud, menu)
        menuItemDelete = menu!!.findItem(R.id.mnu_delete)
        menuItemDelete.isVisible = isEditMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnu_save -> {
                confirmSaveRecipe()
                true
            }
            R.id.mnu_delete -> {
                confirmDeleteRecipe()
                true
            }
            R.id.mnu_cancel -> {
                clearScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun validateData(): Boolean {
        val fields = listOf(txtId, txtName, txtCategory, txtIngredients, txtDescription, txtSteps)
        for (field in fields) {
            if (field.text.toString().trim().isEmpty()) {
                field.error = "Campo requerido"
                return false
            }
        }
        return true
    }

    private fun searchRecipe(id: String) {
        try {
            val recipe = recipeController.getById(id)
            if (recipe != null) {
                isEditMode = true
                txtId.setText(recipe.ID)
                txtId.isEnabled = false
                txtName.setText(recipe.Name)
                txtCategory.setText(recipe.Category)
                txtIngredients.setText(recipe.Ingredients)
                txtDescription.setText(recipe.Description)
                txtSteps.setText(recipe.Steps)
                if (this::menuItemDelete.isInitialized) {
                    menuItemDelete.isVisible = true
                }
            } else {
                Toast.makeText(this, "Recipe not found.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            clearScreen()
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    // 🔹 Confirmación antes de guardar
    private fun confirmSaveRecipe() {
        if (validateData()) {
            Util.showDialogCondition(this, "¿Desea guardar esta receta?") {
                saveRecipe()
            }
        } else {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveRecipe() {
        try {
            val recipe = Recipe().apply {
                ID = txtId.text.toString().trim()
                Name = txtName.text.toString().trim()
                Category = txtCategory.text.toString().trim()
                Ingredients = txtIngredients.text.toString().trim()
                Description = txtDescription.text.toString().trim()
                Steps = txtSteps.text.toString().trim()
            }

            if (recipeController.getById(recipe.ID) != null && !isEditMode) {
                Toast.makeText(this, "La receta ya existe.", Toast.LENGTH_LONG).show()
            } else {
                if (!isEditMode)
                    recipeController.addRecipe(recipe)
                else
                    recipeController.updateRecipe(recipe)

                clearScreen()
                Toast.makeText(this, "Receta guardada correctamente.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    // 🔹 Confirmación antes de eliminar
    private fun confirmDeleteRecipe() {
        Util.showDialogCondition(this, "¿Desea eliminar esta receta?") {
            deleteRecipe()
        }
    }

    private fun deleteRecipe() {
        try {
            recipeController.removeRecipe(txtId.text.toString().trim())
            clearScreen()
            Toast.makeText(this, "Receta eliminada correctamente.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun clearScreen() {
        isEditMode = false
        txtId.isEnabled = true
        txtId.setText("")
        txtName.setText("")
        txtCategory.setText("")
        txtIngredients.setText("")
        txtDescription.setText("")
        txtSteps.setText("")
        if (this::menuItemDelete.isInitialized) {
            menuItemDelete.isVisible = false
        }
        invalidateOptionsMenu()
    }
}
