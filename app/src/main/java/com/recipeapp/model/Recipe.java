package com.recipeapp.model;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient>Ingredient=new ArrayList<>();

    // nameフィールドとingredientsフィールドそれぞれに、同名の引数を代入するRecipeのコンストラクタ
    public Recipe(String name, ArrayList<Ingredient> ingredients){
        this.Ingredient=ingredients;
        this.name=name;
    }
    // nameフィールドを返す
    public String getName(){
        return name;
    }
    // ingredientsフィールドを返す
    public ArrayList<Ingredient> getIngredient(){
        return Ingredient;
    }
}
