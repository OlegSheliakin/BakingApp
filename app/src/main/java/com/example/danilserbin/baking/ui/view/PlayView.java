package com.example.danilserbin.baking.ui.view;

public interface PlayView {
    void beforeEnable(int visible);
    void afterEnable(int visible);
    void showError(String s);
    void setTextIngredient(String s);
    void setVideo(String path);
    void setTitle(String title);
}