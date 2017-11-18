package com.example.danilserbin.baking.presenter.impl;

import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.ListFoodPresenter;
import com.example.danilserbin.baking.ui.view.ListFoodView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListFoodPresenterImpl implements ListFoodPresenter {

    private RecipeRepository recipeRepository;
    private IdRepository idRepository;
    private ListFoodView view;

    public ListFoodPresenterImpl(ListFoodView view, RecipeRepository recipeRepository, IdRepository idRepository) {
        this.recipeRepository = recipeRepository;
        this.idRepository = idRepository;
        this.view = view;
    }

    @Override
    public void start() {
        int stepId = idRepository.getId(IdRepositoryImpl.RECIPE_ID);
        recipeRepository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> view.fill(recipes.get(stepId).getIngredients()),
                        throwable -> view.showError(throwable.getLocalizedMessage()));
    }
}
