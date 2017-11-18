package com.example.danilserbin.baking.model.network;

import com.example.danilserbin.baking.model.pojo.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipe();
}