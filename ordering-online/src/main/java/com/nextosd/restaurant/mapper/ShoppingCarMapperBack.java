package com.nextosd.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 查询购物车所有记录
	 * @return
	 */
	List<ShoppingCar> getShoppingCarMsg();
	
	/**
	 * 根据foodId查询是否存在重复记录
	 * @return
	 */
	ShoppingCar selectMsgByFoodId(@Param("carFoodId") int carFoodId, @Param("carUserId") int carUserId);
	
	/**
	 * 根据foodId和userId修改信息
	 */
	int updShoppingCarMsg(ShoppingCar shoppingCar);
	
	/**
	 * 删除一条购物车记录
	 * @param carId
	 * @return
	 */
	int deleteShoppingMsg(int carId);
	
	
	

}
