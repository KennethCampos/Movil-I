
/*
const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

 base de datos temporal
    let recipes = [
    {
        id: "1",
                name: "Arroz con leche",
            category: "Postre",
            description: "Receta tradicional",
            ingredients: "Arroz, leche, azúcar",
            steps: "Hervir, mezclar, servir"
    }
];


crear
app.post("/recipes", (req, res) => {
  const recipe = {
                id: Date.now().toString(),
                name: req.body.name,
                category: req.body.category,
                description: req.body.description,
                ingredients: req.body.ingredients,
                steps: req.body.steps
  };

        recipes.push(recipe);
        res.status(201).json(recipe);
    });


get
app.get("/recipes", (req, res) => {
        res.json(recipes);
    });


buscar
app.get("/recipes/:id", (req, res) => {
  const recipe = recipes.find(r => r.id === req.params.id);
        recipe
                ? res.json(recipe)
                : res.status(404).json({ message: "Receta no encontrada" });
    });


modificar
app.put("/recipes/:id", (req, res) => {
  const index = recipes.findIndex(r => r.id === req.params.id);

        if (index === -1) {
            return res.status(404).json({ message: "Receta no encontrada" });
        }

        recipes[index] = {
    ...recipes[index],
                name: req.body.name,
                category: req.body.category,
                description: req.body.description,
                ingredients: req.body.ingredients,
                steps: req.body.steps
  };

        res.json(recipes[index]);
    });


borrar
app.delete("/recipes/:id", (req, res) => {
  const index = recipes.findIndex(r => r.id === req.params.id);

        if (index === -1) {
            return res.status(404).json({ message: "Receta no encontrada" });
        }

        recipes.splice(index, 1);
        res.json({ message: "Receta eliminada correctamente" });
    });


coneccion
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
        console.log("API CRUD Recetas corriendo en puerto", PORT);
    });
/*

