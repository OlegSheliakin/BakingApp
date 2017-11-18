package com.example.danilserbin.baking.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Food.class,
        parentColumns = "id",
        childColumns = "food_id",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("food_id")})
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;



    @ColumnInfo(name = "food_id")
    private int recipe_id;
    private Double quantity;
    private String measure;
    private String ingredient;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}