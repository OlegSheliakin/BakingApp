package com.example.danilserbin.baking.ui.di.modul;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.danilserbin.baking.model.db.RecipeDataBase;
import com.example.danilserbin.baking.model.network.ApiClient;
import com.example.danilserbin.baking.model.network.RecipeNetwork;
import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.model.repository.impl.RecipeRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class WidgetModule {

    @Provides
    IdRepository provideIdRepository(SharedPreferences preferences) {
        return new IdRepositoryImpl(preferences);
    }

    @Provides
    RecipeRepository provideRrecipeRepository(RecipeDataBase db, ApiClient api) {
        return new RecipeRepositoryImpl(db, api);
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(IdRepositoryImpl.ID_PATH, Context.MODE_PRIVATE);
    }

    @Provides
    RecipeDataBase provideRecipeDataBase(Context context) {
        return Room.databaseBuilder(context, RecipeDataBase.class, RecipeDataBase.NAME).build();
    }

    @Provides
    ApiClient provideApiClient() {
        return RecipeNetwork.getInstance();
    }

}
