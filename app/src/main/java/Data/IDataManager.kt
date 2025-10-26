package Data

import Entity.Recipe

interface IDataManager {

        fun add(recipe: Recipe)
        fun remove(id: String)
        fun update(recipe: Recipe)
        fun getAll(): List<Recipe>
        fun getById(id: String): Recipe?
        fun getByName(name: String): Recipe?
        fun searchByCategory(category: String): List<Recipe>


}