package com.example.danilserbin.baking.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.RecipePresenter;
import com.example.danilserbin.baking.ui.di.modul.RecipeModule;
import com.example.danilserbin.baking.ui.fragment.IngredientFragment;
import com.example.danilserbin.baking.ui.fragment.ListFoodFragment;
import com.example.danilserbin.baking.ui.fragment.PlayFragment;
import com.example.danilserbin.baking.ui.view.RecipeView;
import com.example.danilserbin.baking.util.ExoPlayerVideoSingleton;

import javax.inject.Inject;

public class RecipeActivity extends AppCompatActivity implements
        IngredientFragment.OnFragmentRecipeStep, RecipeView {
    private final static String RECIPE = "RECIPE";
    public final static String FRAGMENT = "FRAGMENT";
    public final static String INGREDIENT = "INGREDIENT";
    public final static String PLAY = "PLAY";

    @Inject
    PlayFragment playFragment;
    @Inject
    ListFoodFragment listFoodFragment;
    @Inject
    RecipePresenter presenter;
    IngredientFragment instFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        BakingApp.getComponent().createRecipeComponent(new RecipeModule(this)).inject(this);

        presenter.start();

        initial();
    }

    private void initial() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        manager = getSupportFragmentManager();
        instFragment = (IngredientFragment) manager.findFragmentById(R.id.frInstruction);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (manager.findFragmentByTag(RECIPE) == null) {
                manager
                        .beginTransaction()
                        .add(R.id.frPlay, playFragment, RECIPE)
                        .commit();

                presenter.deleteId(IdRepositoryImpl.STEP_ID);
            }
        }
    }

    @Override
    public void clickStepItem(int stepId) {
        presenter.saveStepId(stepId);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, InstructionActivity.class);
            intent.putExtra(FRAGMENT,PLAY);
            startActivity(intent);
        } else {
            manager
                    .beginTransaction()
                    .replace(R.id.frPlay, playFragment, RECIPE)
                    .commit();
            playFragment.restart();
        }
    }

    @Override
    public void clickButtonIngredient() {
        ExoPlayerVideoSingleton.getInstance().clearPlayer();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, InstructionActivity.class);
            intent.putExtra(FRAGMENT,INGREDIENT);
            startActivity(intent);
        } else {
            manager
                    .beginTransaction()
                    .replace(R.id.frPlay, listFoodFragment, RECIPE)
                    .commit();
        }

    }

    @Override
    public void setRecipeTitle(String title) {
        setTitle(title);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        ExoPlayerVideoSingleton.getInstance().clearPlayer();
    }
}