package com.nextosd.orderingonline.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nextosd.orderingonline.beans.Item;
import com.nextosd.orderingonline.beans.Order;
@Mapper
public interface OrderMapper {
	
	/**
	 * 添加订单信息
	 * @param order
	 * @return
	 */
	int insertOrder(Order order);
	
	/**
	 * 添加订单详细信息
	 * @param item
	 * @return
	 */
	int insertOrderItems(Item item);
	
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
	List<Order> selectOrdersByOrderUserId(int orderUserId);
	
	/**
	 * 根据订单编号查询订单详细信息
	 * @param orderId
	 * @return
	 */
	List<Item> selectItemsByOrderId(int orderId);
	
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
	List<Order> selectAllOrders();

}
