package com.example.danilserbin.baking.model.repository.impl;

import com.example.danilserbin.baking.model.db.RecipeDataBase;
import com.example.danilserbin.baking.model.entity.Food;
import com.example.danilserbin.baking.model.entity.Ingredient;
import com.example.danilserbin.baking.model.entity.Step;
import com.example.danilserbin.baking.model.mapper.MapFoodToRecipe;
import com.example.danilserbin.baking.model.network.ApiClient;
import com.example.danilserbin.baking.model.pojo.Recipe;
import com.example.danilserbin.baking.model.repository.RecipeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RecipeRepositoryImpl implements RecipeRepository {

    private final RecipeDataBase db;
    private final ApiClient api;

    @Inject
    public RecipeRepositoryImpl(RecipeDataBase db, ApiClient api) {
        this.db = db;
        this.api = api;
    }

    @Override
    public Observable<List<Recipe>> getRecipeCallback() {
        Observable<List<Recipe>> observable = db.recipeDao().getRecipeAll().map(
                foodAndLists -> new MapFoodToRecipe().map(foodAndLists)).toObservable();

        return Observable.mergeDelayError(observable,
                        api.getRecipe().doOnNext(recipes -> {
                            db.recipeDao().deleteAll();
                            saveData(recipes);
                        }));
    }

    @Override
    public Observable<List<Recipe>> getRecipeDBCallback() {
        return db.recipeDao().getRecipeAll().map(
                foodAndLists -> new MapFoodToRecipe().map(foodAndLists)).toObservable();
    }

    private void saveData(Iterable<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            Food food = new Food(recipe.getId(), recipe.getName(),
                    recipe.getServings(), recipe.getImage());

            db.recipeDao().setRecipe(food);
            insertIngredientForFood(food, recipe.getIngredients());
            insertStepForFood(food, recipe.getSteps());
        }
    }

    private void insertStepForFood(Food food, List<Step> steps) {
        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).setRecipeId(food.getId());
        }
        db.recipeDao().insertStepAll(steps);
    }

    private void insertIngredientForFood(Food food, List<Ingredient> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.get(i).setRecipe_id(food.getId());
        }
        db.recipeDao().insertIngredientsAll(ingredients);
    }

}