package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.activity.RecipeActivity;
import com.example.danilserbin.baking.ui.di.modul.RecipeModule;

import dagger.Subcomponent;

@Subcomponent(modules = RecipeModule.class)
public interface RecipeComponent {
    void inject(RecipeActivity activity);
}