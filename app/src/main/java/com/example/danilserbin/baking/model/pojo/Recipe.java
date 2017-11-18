package com.example.danilserbin.baking.model.pojo;

import com.example.danilserbin.baking.model.entity.Food;
import com.example.danilserbin.baking.model.entity.Ingredient;
import com.example.danilserbin.baking.model.entity.Step;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private int servings;
    private String image;

    public Recipe(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.servings = food.getServings();
        this.image = food.getImage();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Food getFood(){
        return new Food(id,name,servings,image);
    }

}