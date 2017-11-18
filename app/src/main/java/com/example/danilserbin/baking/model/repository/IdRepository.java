package com.example.danilserbin.baking.model.repository;

public interface IdRepository {

    void saveId(String key,int id);
    int getId(String key);
    void clearId(String key);
}
