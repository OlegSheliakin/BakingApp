package com.example.danilserbin.baking.ui.view;

import com.example.danilserbin.baking.model.entity.Step;

import java.util.List;

public interface IngredientView {
    void fillStep(List<Step> st);
    void setTitle(String s);
    void showError(String s);
}