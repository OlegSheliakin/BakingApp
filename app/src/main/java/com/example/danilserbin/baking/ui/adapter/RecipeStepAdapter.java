package com.example.danilserbin.baking.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.entity.Step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder>{

    private final List<Step> list = new ArrayList<>();

    private OnStepClick click;

    @Inject
    public RecipeStepAdapter() {
    }

    @Override
    public RecipeStepAdapter.RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recipe_step, parent, false);
        return new RecipeStepAdapter.RecipeStepViewHolder(view, click);
    }

    @Override
    public void onBindViewHolder(RecipeStepAdapter.RecipeStepViewHolder holder, int position) {
        holder.bind(list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClick(OnStepClick click) {
        this.click = click;
    }

    public void setList(Collection<Step> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    static class RecipeStepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNameIngredient)
        TextView tvRecipeStep;
        private OnStepClick click;

        RecipeStepViewHolder(View itemView, final OnStepClick click) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.click = click;
        }

        public void bind(Step step,int position) {
            tvRecipeStep.setText(step.getShortDescription());
            itemView.setOnClickListener(v -> click.click(position));
        }
    }

    public interface OnStepClick {
        void click(int stepId);
    }
}