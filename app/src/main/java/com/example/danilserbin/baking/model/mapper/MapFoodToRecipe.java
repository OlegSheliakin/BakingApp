package com.example.danilserbin.baking.model.mapper;

import com.example.danilserbin.baking.model.entity.FoodAndList;
import com.example.danilserbin.baking.model.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MapFoodToRecipe implements Map<List<Recipe>,List<FoodAndList>> {
    @Override
    public List<Recipe> map(List<FoodAndList> foods) {
        List<Recipe> list = new ArrayList<>();
        for(FoodAndList food: foods){
            Recipe recipe = new Recipe(food.getFood());
            recipe.setIngredients(food.getIngredients());
            recipe.setSteps(food.getSteps());
            list.add(recipe);
        }
        return list;
    }
}
