package com.example.danilserbin.baking.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.entity.Ingredient;
import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.ui.di.modul.WidgetModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BakingFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Ingredient> list;
    private Context context;

    @Inject
    IdRepository idRepository;
    @Inject
    RecipeRepository recipeRepository;

    public BakingFactory(Context context) {
        this.context = context;
        list = new ArrayList<>();
        BakingApp.getComponent().createWidgetComponent(new WidgetModule()).inject(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        int idRecipe = idRepository.getId(IdRepositoryImpl.RECIPE_ID);
        list = recipeRepository.getRecipeDBCallback().blockingFirst().get(idRecipe).getIngredients();
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item_widget);
        remoteViews.setTextViewText(R.id.tvNameIngredient,
                context.getResources().getString(R.string.ingredient) +
                        ": " + list.get(position).getIngredient());
        remoteViews.setTextViewText(R.id.tvMeasureIngredient,
                context.getResources().getString(R.string.measure) +
                        ": " + list.get(position).getMeasure());
        remoteViews.setTextViewText(R.id.tvQuantityIngredient,
                context.getResources().getString(R.string.quantity) +
                        ": " + list.get(position).getQuantity());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
