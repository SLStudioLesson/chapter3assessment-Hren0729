package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {
        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }
    // 設問5
    private void displayRecipes() throws IOException {
        ArrayList<Recipe> recipes = dataHandler.readData();
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        System.out.println();
        System.out.println("Recipes:");
        for (Recipe recipe : recipes) {
            System.out.println("-----------------------------------");
            System.out.println("Recipe Name: " + recipe.getName());

            ArrayList<String> ingredients = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredient()) {
                ingredients.add(ingredient.getName());
            }

            System.out.println("Main Ingredients: " + String.join(", ", ingredients));
        }
        System.out.println("-----------------------------------");
    }
    // 設問6
    private void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            if (recipeName.isEmpty()) {
                System.out.println("Recipe name cannot be empty. Returning to menu.");
                return;
            }

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientName = reader.readLine();
                if ("done".equalsIgnoreCase(ingredientName)) {
                    break;
                }
                if (!ingredientName.isEmpty()) {
                    ingredients.add(new Ingredient(ingredientName));
                }
            }

            Recipe newRecipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(newRecipe);
            System.out.println("Recipe added successfully.");

        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
