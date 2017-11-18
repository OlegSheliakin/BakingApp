package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.di.modul.WidgetModule;
import com.example.danilserbin.baking.ui.widget.BakingFactory;

import dagger.Subcomponent;

@Subcomponent(modules = {WidgetModule.class})
public interface WidgetComponent {
    void inject(BakingFactory widget);
}
