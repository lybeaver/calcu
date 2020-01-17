package com.nextosd.restaurant.beans;

import java.util.Date;

public class Orders {
    private Integer orderId;

    private Integer orderUserId;

    private Integer orderPrice;

    private Date orderTime;

    private String orderType;

    public Orders(Integer orderId, Integer orderUserId, Integer orderPrice, Date orderTime, String orderType) {
        this.orderId = orderId;
        this.orderUserId = orderUserId;
        this.orderPrice = orderPrice;
        this.orderTime = orderTime;
        this.orderType = orderType;
    }

    public Orders() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }
}