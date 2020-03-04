package com.nextosd.restaurant.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCar {
	
	private int carId;
	
	private int carUserId;
	
	private String CarUserName;
	
	private int CarFoodId;
	
	private String CarFoodName;
	
	private int CarFoodNum;
	
	private int CarOnePrice;
	
	private int CarAllPrice;

}
