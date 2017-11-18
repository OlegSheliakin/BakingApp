package com.example.danilserbin.baking.model.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RecipeNetwork {

    public static final String API_URL_BASE = "https://d17h27t6h515a5.cloudfront.net";
    private static ApiClient client;

    private RecipeNetwork() {
    }

    public static ApiClient getInstance() {

        if (client == null) {
            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor().
                    setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggin);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

            Retrofit retrofit = builder.client(httpClient.build()).build();

            client = retrofit.create(ApiClient.class);
        }

        return client;
    }
}
