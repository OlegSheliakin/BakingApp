package com.example.danilserbin.baking.presenter.impl;

import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.RecipePresenter;
import com.example.danilserbin.baking.ui.view.RecipeView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipePresenterImpl implements RecipePresenter {

    private RecipeView view;
    private IdRepository idRepository;
    private RecipeRepository recipeRepository;

    @Inject
    public RecipePresenterImpl(RecipeView view,
                               IdRepository idRepository,
                               RecipeRepository recipeRepository){
        this.view = view;
        this.idRepository = idRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveStepId(int stepId) {
        idRepository.saveId(IdRepositoryImpl.STEP_ID,stepId);
    }

    @Override
    public void deleteId(String key) {
        idRepository.clearId(key);
    }

    @Override
    public void start() {
        int recipeId = idRepository.getId(IdRepositoryImpl.RECIPE_ID);

        recipeRepository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        food -> view.setRecipeTitle(food.get(recipeId).getName()),
                        throwable -> view.showError(throwable.getLocalizedMessage()));
    }

}
