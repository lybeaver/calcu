package com.nextosd.restaurant.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer orderItemId;

    private Integer orderId;

    private Integer foodId;

    private String foodName;

    private Integer foodPrice;

    private Integer num;

    private Integer price;

}