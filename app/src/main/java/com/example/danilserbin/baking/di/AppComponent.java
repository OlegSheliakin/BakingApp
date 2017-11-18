package com.example.danilserbin.baking.di;

import com.example.danilserbin.baking.ui.di.component.IngredientComponent;
import com.example.danilserbin.baking.ui.di.component.InstructionComponent;
import com.example.danilserbin.baking.ui.di.component.ListFoodComponent;
import com.example.danilserbin.baking.ui.di.component.MainComponent;
import com.example.danilserbin.baking.ui.di.component.PlayComponent;
import com.example.danilserbin.baking.ui.di.component.RecipeComponent;
import com.example.danilserbin.baking.ui.di.component.WidgetComponent;
import com.example.danilserbin.baking.ui.di.modul.IngredientModule;
import com.example.danilserbin.baking.ui.di.modul.InstructionModule;
import com.example.danilserbin.baking.ui.di.modul.ListFoodModule;
import com.example.danilserbin.baking.ui.di.modul.MainModule;
import com.example.danilserbin.baking.ui.di.modul.PlayModule;
import com.example.danilserbin.baking.ui.di.modul.RecipeModule;
import com.example.danilserbin.baking.ui.di.modul.WidgetModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    IngredientComponent createIngredientComponent(IngredientModule module);
    MainComponent createMainComponent(MainModule module);
    RecipeComponent createRecipeComponent(RecipeModule module);
    PlayComponent createPlayComponent(PlayModule module);
    InstructionComponent createInstructionComponent(InstructionModule module);
    ListFoodComponent createListFoodComponent(ListFoodModule module);
    WidgetComponent createWidgetComponent(WidgetModule module);
}
