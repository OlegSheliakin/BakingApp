package com.example.danilserbin.baking.ui.view;

import com.example.danilserbin.baking.model.pojo.Recipe;

import java.util.List;

public interface MainView {
    void fill(List<Recipe> recipes);
    void showError(String s);
    void updateWidget();
}