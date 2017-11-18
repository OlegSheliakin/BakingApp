package com.example.danilserbin.baking.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.entity.Ingredient;
import com.example.danilserbin.baking.presenter.ListFoodPresenter;
import com.example.danilserbin.baking.ui.adapter.RecipeIngredientAdapter;
import com.example.danilserbin.baking.ui.di.modul.ListFoodModule;
import com.example.danilserbin.baking.ui.view.ListFoodView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFoodFragment extends Fragment implements ListFoodView{

    @BindView(R.id.rvListFood)
    RecyclerView rvListFood;
    @Inject
    RecipeIngredientAdapter adapter;
    @Inject
    ListFoodPresenter presenter;

    @Inject
    public ListFoodFragment(){
        BakingApp.getComponent().createListFoodComponent(new ListFoodModule(this)).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_food, container, false);

        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();

        presenter.start();
    }

    private void initial(){
        adapter = new RecipeIngredientAdapter();
        rvListFood.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListFood.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        rvListFood.setAdapter(adapter);
    }

    @Override
    public void fill(List<Ingredient> list) {
        adapter.setList(list);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
}