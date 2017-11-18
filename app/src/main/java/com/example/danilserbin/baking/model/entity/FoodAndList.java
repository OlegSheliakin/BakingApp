package com.example.danilserbin.baking.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class FoodAndList {

    @Embedded
    private Food food;

    @Relation(parentColumn = "id", entityColumn = "food_id", entity = Ingredient.class)
    private List<Ingredient> ingredients;

    @Relation(parentColumn = "id", entityColumn = "food_id", entity = Step.class)
    private List<Step> steps;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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
}
