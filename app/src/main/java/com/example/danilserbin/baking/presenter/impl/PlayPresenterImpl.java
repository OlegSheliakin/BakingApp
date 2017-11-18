package com.example.danilserbin.baking.presenter.impl;

import android.view.View;

import com.example.danilserbin.baking.model.entity.Step;
import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.PlayPresenter;
import com.example.danilserbin.baking.ui.view.PlayView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PlayPresenterImpl implements PlayPresenter {

    private RecipeRepository recipeRepository;
    private IdRepository idRepository;
    private PlayView view;
    private final int recipeId;

    @Inject
    public PlayPresenterImpl(PlayView view, RecipeRepository repository, IdRepository idRepository) {
        this.idRepository = idRepository;
        this.recipeRepository = repository;
        this.view = view;
        this.recipeId = idRepository.getId(IdRepositoryImpl.RECIPE_ID);
    }

    @Override
    public void play() {
        int stepId = idRepository.getId(IdRepositoryImpl.STEP_ID);

        recipeRepository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                    view.setTextIngredient(recipes.get(recipeId).getSteps().get(stepId).getDescription());
                    view.setVideo(recipes.get(recipeId).getSteps().get(stepId).getVideoURL());
                    if (stepId == 0) {
                        view.beforeEnable(View.INVISIBLE);
                    } else if (stepId + 1 == recipes.get(recipeId).getSteps().size()) {
                        view.afterEnable(View.INVISIBLE);
                    }
                }, throwable -> view.showError(throwable.getLocalizedMessage()));
    }

    @Override
    public void clickBtnBefore() {
        int stepId = idRepository.getId(IdRepositoryImpl.STEP_ID);
        recipeRepository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                            if (stepId - 1 == 0) {
                                view.beforeEnable(View.INVISIBLE);
                            }
                            Step step = recipes.get(recipeId).getSteps().get(stepId - 1);
                            idRepository.saveId(IdRepositoryImpl.STEP_ID, stepId - 1);
                            view.setTextIngredient(step.getDescription());
                            view.setVideo(step.getVideoURL());
                            view.afterEnable(View.VISIBLE);
                        },
                        throwable -> view.showError(throwable.getLocalizedMessage()));
    }

    @Override
    public void clickBtnAfter() {
        int stepId = idRepository.getId(IdRepositoryImpl.STEP_ID);
        recipeRepository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                            if (stepId + 1 == recipes.get(recipeId).getSteps().size() - 1) {
                                view.afterEnable(View.INVISIBLE);
                            }
                            Step step = recipes.get(recipeId).getSteps().get(stepId + 1);
                            idRepository.saveId(IdRepositoryImpl.STEP_ID, stepId + 1);
                            view.setTextIngredient(step.getDescription());
                            view.setVideo(step.getVideoURL());
                            view.beforeEnable(View.VISIBLE);
                        },
                        throwable -> view.showError(throwable.getLocalizedMessage()));
    }

}