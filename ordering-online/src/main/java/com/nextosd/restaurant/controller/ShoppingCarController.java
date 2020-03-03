package com.nextosd.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.beans.ShoppingCar;
import com.nextosd.restaurant.service.ShoppingCarService;

@RestController
@RequestMapping("/shoppingCar")
public class ShoppingCarController {
	
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	/**
	 * 向购物车添加信息
	 * @param shoppingCar
	 * @return
	 */
	@GetMapping(value = "/insertShoppingCar")
	public int insertCarMsg(ShoppingCar shoppingCar) {
		int result = shoppingCarService.insertCarMsg(shoppingCar);
		return result;
	}

}
