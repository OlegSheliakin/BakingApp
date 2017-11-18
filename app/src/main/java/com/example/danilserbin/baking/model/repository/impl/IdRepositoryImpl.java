package com.example.danilserbin.baking.model.repository.impl;

import android.content.SharedPreferences;

import com.example.danilserbin.baking.model.repository.IdRepository;

import javax.inject.Inject;

public class IdRepositoryImpl implements IdRepository {

    private final SharedPreferences preferences;
    public static final String ID_PATH = "pathId";
    public final static String RECIPE_ID = "recipe_id";
    public final static String STEP_ID = "step_id";

    @Inject
    public IdRepositoryImpl(SharedPreferences preferences){
        this.preferences = preferences;
    }

    @Override
    public void saveId(String key, int id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,id);
        editor.apply();
    }

    @Override
    public int getId(String key) {
        return preferences.getInt(key,0);
    }

    @Override
    public void clearId(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
