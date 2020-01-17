package com.nextosd.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nextosd.restaurant.beans.OrderItem;
import com.nextosd.restaurant.beans.Orders;
@Mapper
public interface OrderMapper {
	
	/**
	 * 添加订单信息
	 * @param order
	 * @return
	 */
	int insertOrder(Orders order);
	
	/**
	 * 添加订单详细信息
	 * @param orderItem
	 * @return
	 */
	int insertOrderItems(OrderItem orderItem);
	
	/**
	 * 根据订单id删除订单
	 * @param orderId
	 * @return
	 */
	int deleteOrderByOrderId(int orderId);
	
	/**
	 * 根据用户id查询订单信息
	 * @param orderUserId
	 * @return
	 */
	List<Orders> selectOrdersByOrderUserId(int orderUserId);
	
	/**
	 * 根据订单编号查询订单详细信息
	 * @param orderId
	 * @return
	 */
	List<OrderItem> selectItemsByOrderId(int orderId);
	
	/**
	 * 根据订单id修改订单状态
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	int updateOrderByOrderType(int orderId,String orderType);
	
	/**
	 * 根据订单id删除订单信息
	 * @param orderId
	 * @return
	 */
	int deleteOrdersByOrderId(int orderId);
	
	/**
	 * 根据订单信息id删除订单详细信息
	 * @param orderId
	 * @return
	 */
	int deleteOrderItemsByOrderItemId(int orderItemId);
	
	/**
	 * 根据用户编号查询未完成订单数量
	 * @param orderUserId
	 * @return
	 */
	int selectOrderTypeNumByOrderUserId(int orderUserId);
	
	/**
	 * 查询所有订单信息
	 * @return
	 */
	List<Orders> selectAllOrders();

}
