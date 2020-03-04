package com.nextosd.restaurant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nextosd.restaurant.beans.ShoppingCar;

@Mapper
public interface ShoppingCarMapperBack {
	
	/**
	 * 向购物车中添加信息
	 * @param shoppingCar
	 * @return
	 */
	int insertCarMsg(ShoppingCar shoppingCar);
	
	/**
	 * 获取购物车数据条数
	 * @return
	 */
	Integer getShoppingCarCount();
	
	
	

}
