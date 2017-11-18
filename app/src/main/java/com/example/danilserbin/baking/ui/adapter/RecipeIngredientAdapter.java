package com.example.danilserbin.baking.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.entity.Ingredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.RecipeStepViewHolder> {

    private final List<Ingredient> list = new ArrayList<>();

    @Inject
    public RecipeIngredientAdapter() {
    }

    @Override
    public RecipeIngredientAdapter.RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recipe_ingr, parent, false);
        return new RecipeIngredientAdapter.RecipeStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeIngredientAdapter.RecipeStepViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(Collection<Ingredient> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    static class RecipeStepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNameIngredient)
        TextView tvNameIngredient;
        @BindView(R.id.tvMeasureIngredient)
        TextView tvMeasureIngredient;
        @BindView(R.id.tvQuantityIngredient)
        TextView tvQuantityIngredient;
        Context context;

        RecipeStepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        public void bind(Ingredient instruction) {
            tvNameIngredient.setText(context.getResources().getString(R.string.ingredient)
                    + ": " + instruction.getIngredient());
            tvMeasureIngredient.setText(context.getResources().getString(R.string.measure)
                    + ": " + instruction.getMeasure());
            tvQuantityIngredient.setText(context.getResources().getString(R.string.quantity)
                    + ": " + String.valueOf(instruction.getQuantity()));
        }
    }
}