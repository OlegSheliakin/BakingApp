package com.example.danilserbin.baking;

import android.app.Application;

import com.example.danilserbin.baking.di.AppComponent;
import com.example.danilserbin.baking.di.AppModule;
import com.example.danilserbin.baking.di.DaggerAppComponent;

public class BakingApp extends Application{
    private static AppComponent component;

    public BakingApp() {
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(){
        return component;
    }

}