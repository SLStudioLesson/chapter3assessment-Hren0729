package com.recipeapp.datahandler;

import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVDataHandler implements DataHandler {
    private final String filePath;

    public CSVDataHandler() {
        this.filePath = "app\\src\\main\\resources\\recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                String recipeName = parts[0];
                String[] ingredientNames = parts[1].split(",");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (String ingredientName : ingredientNames) {
                    ingredients.add(new Ingredient(ingredientName));
                }

                recipes.add(new Recipe(recipeName, ingredients));
            }
        }

        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        // レシピ名を取得
        StringBuilder line = new StringBuilder(recipe.getName());
        // 材料をカンマ区切りで追加
        for (Ingredient ingredient : recipe.getIngredient()) {
            line.append(",").append(ingredient.getName());
        }
        // ファイルに追記
        writer.write(line.toString());
        writer.newLine();
    }
    }

    @Override
    public ArrayList<Recipe> searchData(String query) {
        return new ArrayList<>();
    }
}
