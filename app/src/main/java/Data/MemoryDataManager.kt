package Data

import Entity.Recipe

object MemoryRecipeManager : IDataManager {
    private var recipeList = mutableListOf<Recipe>()

    override fun add(recipe: Recipe) {
        recipeList.add(recipe)
    }

    override fun remove(id: String) {
        recipeList.removeIf { it.ID.trim() == id.trim() }
    }

    override fun update(recipe: Recipe) {
        remove(recipe.ID)
        add(recipe)
    }

    override fun getAll() = recipeList

    override fun getById(id: String): Recipe? {
        val result = recipeList.filter { it.ID.trim() == id.trim() }
        return if (result.any()) result[0] else null
    }

    override fun getByName(name: String): Recipe? {
        val result = recipeList.filter { it.Name.trim() == name.trim() }
        return if (result.any()) result[0] else null
    }

    override fun searchByCategory(category: String): List<Recipe> {
        return recipeList.filter { it.Category.equals(category.trim(), ignoreCase = true) }
    }
}
