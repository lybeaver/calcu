package com.nextosd.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.mapper.common.OrdersMapper;

@RestController
public class OrderController {
	
	@Autowired
	private OrdersMapper orderMapper;
	


}
