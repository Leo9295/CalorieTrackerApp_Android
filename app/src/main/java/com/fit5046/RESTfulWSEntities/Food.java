package com.fit5046.RESTfulWSEntities;

import java.io.Serializable;

public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    private long foodid;
    private String foodName;
    private String category;
    private int calorieAmount;
    private String servingunit;
    private Double servingamount;
    private int fatAmount;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalorieAmount() {
        return calorieAmount;
    }

    public void setCalorieAmount(int calorieAmount) {
        this.calorieAmount = calorieAmount;
    }

    public int getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(int fatAmount) {
        this.fatAmount = fatAmount;
    }

    public long getFoodid() {
        return foodid;
    }

    public void setFoodid(long foodid) {
        this.foodid = foodid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServingunit() {
        return servingunit;
    }

    public void setServingunit(String servingunit) {
        this.servingunit = servingunit;
    }

    public Double getServingamount() {
        return servingamount;
    }

    public void setServingamount(Double servingamount) {
        this.servingamount = servingamount;
    }
}
