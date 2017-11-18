package com.example.danilserbin.baking.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.danilserbin.baking.model.entity.Food;
import com.example.danilserbin.baking.model.entity.FoodAndList;
import com.example.danilserbin.baking.model.entity.Ingredient;
import com.example.danilserbin.baking.model.entity.Step;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM food")
    Flowable<List<FoodAndList>> getRecipeAll();

    @Query("DELETE FROM food")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStepAll(List<Step> steps);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredientsAll(List<Ingredient> ingredients);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setRecipe(Food food);
}