package com.example.danilserbin.baking.presenter.impl;

import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.IngredientPresenter;
import com.example.danilserbin.baking.ui.view.IngredientView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IngredientPresenterImpl implements IngredientPresenter {

    private RecipeRepository repository;
    private IngredientView view;
    private IdRepository idRepository;

    @Inject
    public IngredientPresenterImpl(IngredientView view,
                                   RecipeRepository recipeRepository,
                                   IdRepository idRepository) {
        this.repository = recipeRepository;
        this.idRepository = idRepository;
        this.view = view;
    }

    @Override
    public void start() {
        int id = idRepository.getId(IdRepositoryImpl.RECIPE_ID);
        repository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                            view.fillStep(recipes.get(id).getSteps());
                            view.setTitle(recipes.get(id).getName());
                        },
                        throwable -> view.showError(throwable.getLocalizedMessage()));
    }
}