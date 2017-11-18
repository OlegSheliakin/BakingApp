package com.example.danilserbin.baking.ui.activity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.pojo.Recipe;
import com.example.danilserbin.baking.presenter.MainPresenter;
import com.example.danilserbin.baking.ui.adapter.RecipeAdapter;
import com.example.danilserbin.baking.ui.di.modul.MainModule;
import com.example.danilserbin.baking.ui.view.MainView;
import com.example.danilserbin.baking.ui.widget.BakingWidget;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        RecipeAdapter.FoodClickItem, MainView{

    @BindView(R.id.rvRecipe)
    RecyclerView rvRecipe;
    @Inject
    RecipeAdapter adapter;
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BakingApp.getComponent().createMainComponent(new MainModule(this)).inject(this);

        initial();

        presenter.start();

    }

    private void initial() {

        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(this);
        } else {
            layoutManager = new GridLayoutManager(this, 3);
        }
        rvRecipe.setLayoutManager(layoutManager);
        rvRecipe.setAdapter(adapter);
        setTitle(R.string.recipe);

        adapter.setClickItem(this);
    }

    @Override
    public void click(int id) {
        presenter.clickItem(id);
        startActivity(new Intent(this,RecipeActivity.class));
    }

    @Override
    public void fill(List<Recipe> recipes) {
        adapter.setList(recipes);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateWidget() {
        Intent intent = new Intent(this, BakingWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager
                .getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(),BakingWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }
}