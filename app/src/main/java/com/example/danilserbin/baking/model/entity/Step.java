package com.example.danilserbin.baking.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Food.class,
        parentColumns = "id",
        childColumns = "food_id",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("food_id")})
public class Step {

    @PrimaryKey(autoGenerate = true)
    private Integer sqlId;
    private Integer id;
    @ColumnInfo(name = "food_id")
    private Integer recipeId;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public Integer getStepId() {
        return id;
    }

    public void setStepId(Integer step_id) {
        this.id = step_id;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }
}
