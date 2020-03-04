package com.nextosd.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.beans.Order;
import com.nextosd.restaurant.mapper.common.OrdersMapper;
import com.nextosd.restaurant.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrdersMapper orderMapper;
	
	@Autowired
	private OrderService orderService;
	
	
	public void insIntoShoppingCar() {
		
	}
	
	/**
	 * 查询未完成订单id
	 * @return
	 */
	@GetMapping(value = "/getOrderType")
	public int getUndoneOrder() {
		Order order = orderService.selectUndoneOrder();
		if(order == null) {
			return 0;
		}else {
			return order.getOrderId();
		}
	}

}
