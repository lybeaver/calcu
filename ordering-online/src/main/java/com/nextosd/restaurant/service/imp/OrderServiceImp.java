package com.nextosd.restaurant.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextosd.restaurant.beans.OrderItem;
import com.nextosd.restaurant.beans.Order;
import com.nextosd.restaurant.mapper.OrderMapper;
import com.nextosd.restaurant.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 向订单添加基本信息
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order) {
		int result = orderMapper.insertOrder(order);
		return result;
	}
	
	/**
	 * 添加订单详细信息
	 * @param orderItem
	 * @return
	 */
	public int insertOrderItem(OrderItem orderItem) {
		int result = orderMapper.insertOrderItems(orderItem);
		return result;
	}
	
	/**
	 * 根据订单id删除订单信息
	 * @param orderId
	 * @return
	 */
	public int deleteOrderByOrderId(int orderId) {
		int result = orderMapper.deleteOrderByOrderId(orderId);
		return result;
	}
	
	/**
	 * 根据用户id查询订单信息
	 * @param orderUserId
	 * @return
	 */
	public List<Order> selectOrdersByOrderUserId(int orderUserId){
		List<Order> orders = orderMapper.selectOrdersByOrderUserId(orderUserId);
		return orders;
	}
	
	/**
	 * 根据订单id查询订单详细信息
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> selectItemsByOrderId(int orderId){
		List<OrderItem> orderItems = orderMapper.selectItemsByOrderId(orderId);
		return orderItems;
	}
	
	/**
	 * 根据订单id修改订单状态
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	public int updateOrderByOrderType(int orderId,String orderType){
		int result = orderMapper.updateOrderByOrderType(orderId, orderType);
		return result;
	}
	
	/**
	 * 根据订单id删除订单信息
	 * @param orderId
	 * @return
	 */
	public int deleteOrdersByOrderId(int orderId) {
		int result = orderMapper.deleteOrdersByOrderId(orderId);
		return result;
	}
	
	/**
	 * 根据订单id删除订单详细信息
	 * @param orderId
	 * @return
	 */
	public int deleteOrderItemsByOrderItemId(int orderItemId) {
		int result = orderMapper.deleteOrderItemsByOrderItemId(orderItemId);
		return result;
	}
	
	/**
	 * 根据用户id查询订单未完成数量
	 * @param orderUserId
	 * @return
	 */
	public int selectOrderTypeNumByOrderUserId(int orderUserId) {
		int result = orderMapper.selectOrderTypeNumByOrderUserId(orderUserId);
		return result;
	}
	
	/**
	 * 查询所有订单信息
	 * @return
	 */
	public List<Order> selectAllOrders(){
		List<Order> orders = orderMapper.selectAllOrders();
		return orders;
	}

}
