package com.nextosd.restaurant.service;

import com.nextosd.restaurant.beans.ShoppingCar;

public interface ShoppingCarService {
	
	/**
	 * 向购物车中添加信息
	 * @param shoppingCar
	 * @return
	 */
	int insertCarMsg(ShoppingCar shoppingCar);

}
