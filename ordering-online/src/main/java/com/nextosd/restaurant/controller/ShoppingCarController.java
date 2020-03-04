package com.nextosd.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.beans.ShoppingCar;
import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.mapper.common.UserMapper;
import com.nextosd.restaurant.service.ShoppingCarService;

@RestController
@RequestMapping("/shoppingCar")
public class ShoppingCarController {
	
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 向购物车添加信息
	 * @param shoppingCar
	 * @return
	 */
	@PostMapping(value = "/insertShoppingCar")
	public int insertCarMsg(ShoppingCar shoppingCar) {
		int userId = shoppingCar.getCarUserId();
		//根据用户id获取用户名
		User user = userMapper.selectByPrimaryKey(userId);
		String carUserName = user.getUserName();
		shoppingCar.setCarUserName(carUserName);
		//执行加入购物车操作
		int result = shoppingCarService.insertCarMsg(shoppingCar);
		return result;
	}
	
	/**
	 * 获取购物车记录条数
	 * @return
	 */
	@GetMapping(value = "/getShoppingCarCount")
	public int getShoppingCarCount() {
		int count = shoppingCarService.getShoppingCarCount();
		return count;
	}

}
