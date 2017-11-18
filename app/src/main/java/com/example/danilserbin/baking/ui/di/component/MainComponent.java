package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.activity.MainActivity;
import com.example.danilserbin.baking.ui.di.modul.MainModule;

import dagger.Subcomponent;

@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
