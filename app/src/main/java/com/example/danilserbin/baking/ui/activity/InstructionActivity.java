package com.example.danilserbin.baking.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.presenter.InstructionPresenter;
import com.example.danilserbin.baking.ui.di.modul.InstructionModule;
import com.example.danilserbin.baking.ui.fragment.ListFoodFragment;
import com.example.danilserbin.baking.ui.fragment.PlayFragment;
import com.example.danilserbin.baking.ui.view.InstructionView;
import com.example.danilserbin.baking.util.ExoPlayerVideoSingleton;

import javax.inject.Inject;

public class InstructionActivity extends AppCompatActivity implements InstructionView {

    @Inject
    InstructionPresenter presenter;
    @Inject
    PlayFragment playFragment;
    @Inject
    ListFoodFragment listFoodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        BakingApp.getComponent().createInstructionComponent(new InstructionModule(this)).inject(this);

        initial();

        presenter.start();
    }

    private void initial() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FragmentManager manager = getSupportFragmentManager();
        String nameFragment = getIntent().getStringExtra(RecipeActivity.FRAGMENT);

        Fragment fragment;
        if (nameFragment.equals(RecipeActivity.INGREDIENT)) {
            fragment = listFoodFragment;
        } else {
            fragment = playFragment;
        }

        manager
                .beginTransaction()
                .add(R.id.frInstruction, fragment)
                .commit();
    }

    @Override
    public void setInstructionTitle(String title) {
        setTitle(title);
    }

    @SuppressLint("ShowToast")
    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT);
    }

    @Override
    public void finish() {
        super.finish();
        ExoPlayerVideoSingleton.getInstance().clearPlayer();
    }
}