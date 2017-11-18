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
import com.example.danilserbin.baking.presenter.IngredientPresenter;
import com.example.danilserbin.baking.presenter.impl.IngredientPresenterImpl;
import com.example.danilserbin.baking.ui.view.IngredientView;

import dagger.Module;
import dagger.Provides;

@Module
public class IngredientModule {

    private final IngredientView view;

    public IngredientModule(IngredientView view){
        this.view = view;
    }

    @Provides
    IngredientPresenter provideInstructionPresenter(IngredientView view,
                                                    RecipeRepository repositoryRecipe,
                                                    IdRepository idRepository){
        return new IngredientPresenterImpl(view,repositoryRecipe, idRepository);
    }

    @Provides
    RecipeRepository provideInstructionRecipeRepository(RecipeDataBase db, ApiClient apiClient){
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
    IngredientView provideInstructionView(){
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