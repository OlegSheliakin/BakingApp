package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.di.modul.IngredientModule;
import com.example.danilserbin.baking.ui.fragment.IngredientFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {IngredientModule.class})
public interface IngredientComponent {
    void inject(IngredientFragment fragment);
}
