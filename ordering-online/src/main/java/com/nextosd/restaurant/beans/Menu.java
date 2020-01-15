package com.nextosd.restaurant.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int foodId;
	
	private String foodName;
	
	private String foodType;
	
	private int foodPrice;
	
	private int foodNum;

}
