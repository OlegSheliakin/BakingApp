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
import com.example.danilserbin.baking.presenter.MainPresenter;
import com.example.danilserbin.baking.presenter.impl.MainPresenterImpl;
import com.example.danilserbin.baking.ui.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final MainView mainView;

    public MainModule(MainView view){
        this.mainView = view;
    }

    @Provides
    MainPresenter provideMainPresenterImpl(MainView view,
                                           RecipeRepository repositoryRecipe,
                                           IdRepository idRepository){
        return new MainPresenterImpl(view,repositoryRecipe,idRepository);
    }

    @Provides
    RecipeRepository provideMainRecipeRepository(RecipeDataBase db,ApiClient apiClient){
        return new RecipeRepositoryImpl(db,apiClient);
    }

    @Provides
    RecipeDataBase provideMainRecipeDataBase(Context context){
        return Room.databaseBuilder(context,RecipeDataBase.class,RecipeDataBase.NAME).build();
    }

    @Provides
    ApiClient provideApiClient(){
        return RecipeNetwork.getInstance();
    }

    @Provides
    MainView provideMainView(){
        return mainView;
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
