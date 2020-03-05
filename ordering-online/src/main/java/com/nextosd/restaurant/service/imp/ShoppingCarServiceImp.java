package com.nextosd.restaurant.service.imp;

import java.util.List;

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

	/**
	 * 查询购物车所有记录
	 * @return
	 */
	public List<ShoppingCar> getShoppingCarMsg() {
		List<ShoppingCar> list = shoppingCarMapperBack.getShoppingCarMsg();
		return list;
	}

	/**
	 * 根据foodId查询是否存在重复记录
	 * @return
	 */
	public ShoppingCar selectMsgByFoodId(int carFoodId, int carUserId) {
		ShoppingCar shoppingCar = shoppingCarMapperBack.selectMsgByFoodId(carFoodId,carUserId);
		return shoppingCar;
	}

	/**
	 * 根据foodId和userId修改信息
	 * @return
	 */
	public int updShoppingCarMsg(ShoppingCar shoppingCar) {
		int result = shoppingCarMapperBack.updShoppingCarMsg(shoppingCar);
		return result;
	}
	
	
	

}
