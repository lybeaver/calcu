package com.nextosd.restaurant.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	private int orderId;
	
	private int orderUserId;
	
	private int orderPrice;
	
	private String orderTime;
	
	private String orderType;
	
	private String orderUserName;

}
