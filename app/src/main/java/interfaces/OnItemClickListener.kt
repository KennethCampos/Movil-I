package interfaces

import Entity.Recipe

interface OnItemClickListener {
    fun onItemClicked (recipe: Recipe)
}