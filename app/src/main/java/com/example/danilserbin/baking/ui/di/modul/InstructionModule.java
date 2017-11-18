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
import com.example.danilserbin.baking.presenter.InstructionPresenter;
import com.example.danilserbin.baking.presenter.impl.InstructionPresenterImpl;
import com.example.danilserbin.baking.ui.fragment.IngredientFragment;
import com.example.danilserbin.baking.ui.fragment.ListFoodFragment;
import com.example.danilserbin.baking.ui.fragment.PlayFragment;
import com.example.danilserbin.baking.ui.view.InstructionView;

import dagger.Module;
import dagger.Provides;

@Module
public class InstructionModule {

    private final InstructionView view;

    public InstructionModule(InstructionView view){
        this.view = view;
    }

    @Provides
    ListFoodFragment provideListFoodFragment(){
        return new ListFoodFragment();
    }

    @Provides
    PlayFragment providePlayFragment(){
        return new PlayFragment();
    }

    @Provides
    InstructionPresenter provideInstructionPresenter(InstructionView view,
                                                     IdRepository idRepository,
                                                     RecipeRepository recipeRepository){
        return new InstructionPresenterImpl(view,idRepository,recipeRepository);
    }

    @Provides
    InstructionView provideInstractionView(){
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

    @Provides
    RecipeRepository provideRecipeRepository(RecipeDataBase db, ApiClient client){
        return new RecipeRepositoryImpl(db,client);
    }

    @Provides
    RecipeDataBase provideRecipeDataBase(Context context){
        return Room.databaseBuilder(context,RecipeDataBase.class,RecipeDataBase.NAME).build();
    }

    @Provides
    ApiClient provideApiClient(){
        return RecipeNetwork.getInstance();
    }
}
