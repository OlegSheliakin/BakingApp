package com.example.danilserbin.baking.presenter.impl;

import com.example.danilserbin.baking.model.repository.IdRepository;
import com.example.danilserbin.baking.model.repository.RecipeRepository;
import com.example.danilserbin.baking.model.repository.impl.IdRepositoryImpl;
import com.example.danilserbin.baking.presenter.MainPresenter;
import com.example.danilserbin.baking.ui.view.MainView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {

    private RecipeRepository recipeRepository;
    private MainView view;
    private IdRepository idRepository;

    @Inject
    public MainPresenterImpl(MainView view,
                             RecipeRepository recipeRepository,
                             IdRepository idRepository){
        this.recipeRepository = recipeRepository;
        this.view = view;
        this.idRepository = idRepository;
    }

    @Override
    public void start() {
        recipeRepository.getRecipeCallback()
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribeOn(Schedulers.io())
                .subscribe(recipes -> view.fill(recipes),
                        throwable -> view.showError(throwable.getMessage()));
    }

    @Override
    public void clickItem(int id) {
        idRepository.saveId(IdRepositoryImpl.RECIPE_ID,id);
        view.updateWidget();
    }
}