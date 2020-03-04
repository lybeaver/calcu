package com.nextosd.restaurant.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextosd.restaurant.beans.ShoppingCar;
import com.nextosd.restaurant.mapper.ShoppingCarMapperBack;
import com.nextosd.restaurant.service.ShoppingCarService;

@Service
public class ShoppingCarServiceImp implements ShoppingCarService{
	
	@Autowired
	private ShoppingCarMapperBack shoppingCarMapperBack;
	
	/**
	 * 向购物车中添加信息
	 * @param shoppingCar
	 * @return
	 */
	public int insertCarMsg(ShoppingCar shoppingCar) {
		int result = shoppingCarMapperBack.insertCarMsg(shoppingCar);
		return result;
	}

	/**
	 * 获取购物车数据条数
	 * @return
	 */
	public int getShoppingCarCount() {
		int count = shoppingCarMapperBack.getShoppingCarCount();
		return count;
	}
	
	
	

}
