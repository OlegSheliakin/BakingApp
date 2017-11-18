package com.example.danilserbin.baking.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.danilserbin.baking.BakingApp;
import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.entity.Step;
import com.example.danilserbin.baking.presenter.IngredientPresenter;
import com.example.danilserbin.baking.ui.adapter.RecipeStepAdapter;
import com.example.danilserbin.baking.ui.di.modul.IngredientModule;
import com.example.danilserbin.baking.ui.view.IngredientView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientFragment extends Fragment
        implements RecipeStepAdapter.OnStepClick, IngredientView {
    @BindView(R.id.rvRecipeStep)
    RecyclerView rvRecipeStep;
    @BindView(R.id.btnIngredient)
    Button btnInstruction;
    @Inject
    RecipeStepAdapter adapterStep;
    @Inject
    IngredientPresenter presenter;

    private IngredientFragment.OnFragmentRecipeStep recipeStep;

    @Inject
    public IngredientFragment(){
        BakingApp.getComponent().createIngredientComponent(new IngredientModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();

        presenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recipeStep = (IngredientFragment.OnFragmentRecipeStep)context;
    }

    private void initial(){
        btnInstruction.setOnClickListener(v -> recipeStep.clickButtonIngredient());
        rvRecipeStep.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecipeStep.setAdapter(adapterStep);
        rvRecipeStep.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        adapterStep.setClick(this);
    }

    @Override
    public void click(int idStep) {
        recipeStep.clickStepItem(idStep);
    }

    @Override
    public void fillStep(List<Step> step) {
        adapterStep.setList(step);
    }

    @Override
    public void setTitle(String s) {
        getActivity().setTitle(s);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentRecipeStep{
        void clickStepItem(int stepId);
        void clickButtonIngredient();
    }

}