package com.nextosd.restaurant.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	private int orderItemId;
	
	private int orderId;
	
	private int foodId;
	
	private String foodName;
	
	private int foodPrice;
	
	private int num;
	
	private int price;

}
