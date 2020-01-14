package com.nextosd.orderingonline.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
	
	private int foodId;
	
	private String foodName;
	
	private String foodType;
	
	private int foodPrice;
	
	private int foodNum;

}
