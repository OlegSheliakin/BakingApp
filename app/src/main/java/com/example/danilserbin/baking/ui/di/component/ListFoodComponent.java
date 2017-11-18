package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.di.modul.ListFoodModule;
import com.example.danilserbin.baking.ui.fragment.ListFoodFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {ListFoodModule.class})
public interface ListFoodComponent {
    void inject(ListFoodFragment fragment);
}
