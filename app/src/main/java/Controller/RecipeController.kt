package Controller

import Data.IDataManager
import Data.MemoryRecipeManager
import Entity.Recipe
import android.content.Context
import com.example.myapplication.R

class RecipeController(private val context: Context) {

    private var dataManager: IDataManager = MemoryRecipeManager

    fun addRecipe(recipe: Recipe) {
        try {
            dataManager.add(recipe)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateRecipe(recipe: Recipe) {
        try {
            dataManager.update(recipe)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getById(id: String): Recipe? {
        try {
            return dataManager.getById(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetByID))
        }
    }

    fun getByName(name: String): Recipe? {
        try {
            return dataManager.getByName(name)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetByName))
        }
    }

    fun removeRecipe(id: String) {
        try {
            val result = dataManager.getById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNotFound))
            }
            dataManager.remove(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    fun searchByCategory(category: String): List<Recipe> {
        try {
            return dataManager.searchByCategory(category)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgSearch))
        }
    }

    fun getAll(): List<Recipe> {
        try {
            return dataManager.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
}
