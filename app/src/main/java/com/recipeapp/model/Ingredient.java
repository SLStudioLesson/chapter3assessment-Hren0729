package com.recipeapp.model;

public class Ingredient {
    private String name;

    // nameフィールドに、同名の引数を代入する
    public Ingredient(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
