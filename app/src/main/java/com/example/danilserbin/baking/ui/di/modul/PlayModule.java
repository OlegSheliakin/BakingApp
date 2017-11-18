package com.example.danilserbin.baking.ui.di.modul;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;

import com.example.danilserbin.baking.model.db.RecipeDataBase;
import com.example.danilserbin.baking.model.network.ApiClient;
import com.example.danilserbin.baking.model.network.RecipeNetwork;
import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.model.repository.impl.RecipeRepositoryImpl;
import com.example.danilserbin.baking.presenter.PlayPresenter;
import com.example.danilserbin.baking.presenter.impl.PlayPresenterImpl;
import com.example.danilserbin.baking.ui.view.PlayView;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayModule {

    private final PlayView view;

    public PlayModule(PlayView view){
        this.view = view;
    }

    @Provides
    LinearLayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    PlayPresenter providePlayPresenter(PlayView view,
                                       RecipeRepository repositoryRecipe,
                                       IdRepository repositoryId){
        return new PlayPresenterImpl(view,repositoryRecipe,repositoryId);
    }

    @Provides
    RecipeRepository providePlayRecipeRepository(RecipeDataBase db, ApiClient apiClient){
        return new RecipeRepositoryImpl(db,apiClient);
    }

    @Provides
    RecipeDataBase providePlayRecipeDataBase(Context context){
        return Room.databaseBuilder(context,RecipeDataBase.class,RecipeDataBase.NAME).build();
    }

    @Provides
    ApiClient provideApiClient(){
        return RecipeNetwork.getInstance();
    }

    @Provides
    PlayView providePlayView(){
        return view;
    }

    @Provides
    IdRepository provideIdRepository(SharedPreferences preferences){
        return new IdRepositoryImpl(preferences);
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(IdRepositoryImpl.ID_PATH,Context.MODE_PRIVATE);
    }

}
