package com.example.danilserbin.baking.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.danilserbin.baking.model.entity.Food;
import com.example.danilserbin.baking.model.entity.Ingredient;
import com.example.danilserbin.baking.model.entity.Step;

@Database(entities = {Food.class, Ingredient.class, Step.class}, version = 1,exportSchema = false)
public abstract class RecipeDataBase extends RoomDatabase{
    public static final String NAME = "recipe";
    public abstract RecipeDao recipeDao();
}