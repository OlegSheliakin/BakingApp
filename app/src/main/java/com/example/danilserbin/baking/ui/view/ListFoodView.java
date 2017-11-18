package com.example.danilserbin.baking.ui.view;

import com.example.danilserbin.baking.model.entity.Ingredient;

import java.util.List;

public interface ListFoodView {
    void fill(List<Ingredient> list);
    void showError(String s);
}
