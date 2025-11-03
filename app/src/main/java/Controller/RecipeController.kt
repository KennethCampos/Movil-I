package Controller

import Data.IDataManager
import Data.MemoryRecipeManager
import Entity.Recipe
import android.content.Context
import com.example.mirecetario.R


class RecipeController {

        private var dataManager: IDataManager = MemoryRecipeManager
        private var context: Context

        constructor(context: Context) {
            this.context = context
        }

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

        fun getById(id: String): Recipe {
            try {
                val result = dataManager.getById(id)
                if (result == null) {
                    throw Exception(context.getString(R.string.MsgDataNotFound))
                }
                return result
            } catch (e: Exception) {
                throw Exception(context.getString(R.string.ErrorMsgGetByID))
            }
        }

        fun getByName(name: String): Recipe {
            try {
                val result = dataManager.getByName(name)
                if (result == null) {
                    throw Exception(context.getString(R.string.MsgDataNotFound))
                }
                return result
            } catch (e: Exception) {
                throw Exception(context.getString(R.string.ErrorMsgGetByName))
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