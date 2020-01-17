package com.nextosd.restaurant.service;

import java.util.List;

import com.nextosd.restaurant.beans.OrderItem;
import com.nextosd.restaurant.beans.Orders;

public interface OrderService {
	
	/**
	 * 向订单添加基本信息
	 * @param order
	 * @return
	 */
	public int insertOrder(Orders order);
	
	/**
	 * 添加订单详细信息
	 * @param orderItem
	 * @return
	 */
	public int insertOrderItem(OrderItem orderItem);
	
	/**
	 * 根据订单id删除订单信息
	 * @param orderId
	 * @return
	 */
	public int deleteOrderByOrderId(int orderId);
	
	/**
	 * 根据用户id查询订单信息
	 * @param orderUserId
	 * @return
	 */
	public List<Orders> selectOrdersByOrderUserId(int orderUserId);
	
	/**
	 * 根据订单id查询订单详细信息
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> selectItemsByOrderId(int orderId);
	
	/**
	 * 根据订单id修改订单状态
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	public int updateOrderByOrderType(int orderId,String orderType);
	
	/**
	 * 根据订单id删除订单信息
	 * @param orderId
	 * @return
	 */
	public int deleteOrdersByOrderId(int orderId);
	
	/**
	 * 根据订单id删除订单详细信息
	 * @param orderId
	 * @return
	 */
	public int deleteOrderItemsByOrderItemId(int orderItemId);
	
	/**
	 * 根据用户id查询订单未完成数量
	 * @param orderUserId
	 * @return
	 */
	public int selectOrderTypeNumByOrderUserId(int orderUserId);
	
	/**
	 * 查询所有订单信息
	 * @return
	 */
	public List<Orders> selectAllOrders();
}
