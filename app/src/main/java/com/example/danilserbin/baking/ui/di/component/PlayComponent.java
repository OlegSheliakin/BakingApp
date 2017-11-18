package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.di.modul.PlayModule;
import com.example.danilserbin.baking.ui.fragment.PlayFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {PlayModule.class})
public interface PlayComponent {
    void inject(PlayFragment fragment);
}
