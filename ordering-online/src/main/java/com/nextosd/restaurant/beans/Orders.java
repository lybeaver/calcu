package com.nextosd.restaurant.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer orderId;

    private Integer orderUserId;

    private Integer orderPrice;

    private Date orderTime;

    private String orderType;

}