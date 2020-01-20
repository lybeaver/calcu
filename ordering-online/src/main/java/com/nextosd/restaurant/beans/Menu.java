package com.nextosd.restaurant.beans;

import com.nextosd.restaurant.beans.other.BaseBean;

public class Menu extends BaseBean {
    private Integer foodId;

    private String foodName;

    private String foodType;

    private Integer foodPrice;

    private Integer foodNum;

    public Menu(Integer foodId, String foodName, String foodType, Integer foodPrice, Integer foodNum) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
        this.foodNum = foodNum;
    }

    public Menu() {
        super();
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType == null ? null : foodType.trim();
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }
}