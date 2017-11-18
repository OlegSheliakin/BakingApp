package com.example.danilserbin.baking.ui.di.component;

import com.example.danilserbin.baking.ui.activity.InstructionActivity;
import com.example.danilserbin.baking.ui.di.modul.InstructionModule;

import dagger.Subcomponent;

@Subcomponent(modules = {InstructionModule.class})
public interface InstructionComponent {
    void inject(InstructionActivity activity);
}
