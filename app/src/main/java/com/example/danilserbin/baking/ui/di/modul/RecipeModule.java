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
import com.example.danilserbin.baking.presenter.RecipePresenter;
import com.example.danilserbin.baking.presenter.impl.RecipePresenterImpl;
import com.example.danilserbin.baking.ui.fragment.ListFoodFragment;
import com.example.danilserbin.baking.ui.fragment.PlayFragment;
import com.example.danilserbin.baking.ui.view.RecipeView;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeModule {

    private final RecipeView view;

    public RecipeModule(RecipeView view) {
        this.view = view;
    }

    @Provides
    PlayFragment providePlayFragment(){
        return new PlayFragment();
    }

    @Provides
    RecipeView provideRecipeView(){
        return view;
    }

    @Provides
    RecipePresenter provideRecipePresenter(RecipeView view,
                                           IdRepositoryImpl idRepository,
                                           RecipeRepository recipeRepository){
        return new RecipePresenterImpl(view,idRepository,recipeRepository);
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(IdRepositoryImpl.ID_PATH,Context.MODE_PRIVATE);
    }

    @Provides
    IdRepository provideIdRepository(SharedPreferences preference){
        return new IdRepositoryImpl(preference);
    }

    @Provides
    ApiClient provideApiClient(){
        return RecipeNetwork.getInstance();
    }

    @Provides
    RecipeRepository providePlayRecipeRepository(RecipeDataBase db, ApiClient apiClient){
        return new RecipeRepositoryImpl(db,apiClient);
    }

    @Provides
    RecipeDataBase provideRecipeRecipeDataBase(Context context){
        return Room.databaseBuilder(context,RecipeDataBase.class,RecipeDataBase.NAME).build();
    }

    @Provides
    ListFoodFragment provaideListFoodFragment(){
        return new ListFoodFragment();
    }

}
