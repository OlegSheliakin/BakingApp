package com.example.danilserbin.baking.presenter.impl;

import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.InstructionPresenter;
import com.example.danilserbin.baking.ui.view.InstructionView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstructionPresenterImpl implements InstructionPresenter {

    private IdRepository idRepository;
    private InstructionView view;
    private RecipeRepository recipeRepository;

    @Inject
    public InstructionPresenterImpl(InstructionView view,
                                    IdRepository idRepository,
                                    RecipeRepository recipeRepository) {
        this.idRepository = idRepository;
        this.view = view;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void start() {
        int recipeId = idRepository.getId(IdRepositoryImpl.RECIPE_ID);

        recipeRepository.getRecipeDBCallback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        food -> view.setInstructionTitle(food.get(recipeId).getName()),
                        throwable -> view.showError(throwable.getLocalizedMessage()
                        ));
    }
}