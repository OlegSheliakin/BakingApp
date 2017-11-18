package com.example.danilserbin.baking.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danilserbin.baking.R;
import com.example.danilserbin.baking.model.pojo.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> list;
    private FoodClickItem clickItem;

    @Inject
    public RecipeAdapter() {
        this.list = new ArrayList<>();
    }

    public void setList(Collection<Recipe> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setClickItem(FoodClickItem clickItem) {
        this.clickItem = clickItem;
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recipe, parent, false);
        return new RecipeAdapter.RecipeViewHolder(view, clickItem);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
        holder.bind(list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivRecipe)
        ImageView imageView;
        @BindView(R.id.tvRecipe)
        TextView textView;
        private Context context;
        private int id;

        RecipeViewHolder(View itemView, FoodClickItem clikcItem) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(v -> clikcItem.click(id));
        }

        void bind(Recipe recipe, int id) {
            this.id = id;
            if (!recipe.getImage().isEmpty()) {
                Picasso.with(context)
                        .load(recipe.getImage())
                        .error(R.drawable.borscht)
                        .into(imageView);
            }else{
                imageView.setVisibility(View.GONE);
            }
            textView.setText(recipe.getName());
        }
    }

    public interface FoodClickItem {
        void click(int id);
    }
}