package Entity

import android.graphics.Bitmap
import java.util.Date

class Recipe {

        // Atributos privados
        private var id: String = ""
        private var name: String = ""
        private var description: String = ""
        private var ingredients: String = ""
        private var steps: String = ""  //pasos
        private var category: String = ""
        private lateinit var creationDate: Date
        private var photo: Bitmap? = null

        // Constructor inicio
        constructor()

        constructor(
            id: String,
            name: String,
            description: String,
            ingredients: String,
            steps: String,
            category: String,
            creationDate: Date,
            photo: Bitmap?
        )
        {
            this.id = id
            this.name = name
            this.description = description
            this.ingredients = ingredients
            this.steps = steps//pasos
            this.category = category
            this.creationDate = creationDate
            this.photo = photo
        }//final del con

        // 🔹 Get y Set inicio
        var ID: String
            get() = this.id
            set(value) { this.id = value }

        var Name: String
            get() = this.name
            set(value) { this.name = value }

        var Description: String
            get() = this.description
            set(value) { this.description = value }

        var Ingredients: String
            get() = this.ingredients
            set(value) { this.ingredients = value }

        var Steps: String
            get() = this.steps
            set(value) { this.steps = value }

        var Category: String
            get() = this.category
            set(value) { this.category = value }

        var CreationDate: Date
            get() = this.creationDate
            set(value) { this.creationDate = value }

        var Photo: Bitmap?
            get() = this.photo
            set(value) { this.photo = value }
        //final de get y set


}







