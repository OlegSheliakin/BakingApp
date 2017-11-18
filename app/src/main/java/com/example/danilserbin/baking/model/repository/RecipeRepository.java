package com.example.danilserbin.baking.model.repository;


import com.example.danilserbin.baking.model.pojo.Recipe;

import java.util.List;

import io.reactivex.Observable;

public interface RecipeRepository {
    Observable<List<Recipe>> getRecipeCallback();
    Observable<List<Recipe>> getRecipeDBCallback();
}
