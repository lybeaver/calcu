package com.nextosd.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.beans.Item;
import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.Order;
import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.service.MenuService;
import com.nextosd.restaurant.service.OrderService;
import com.nextosd.restaurant.service.UserService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 根据用户编号和订单总价添加订单基本信息
	 * @param userName
	 * @param orderPrice
	 */
	@PostMapping(value = "/insertOrders")
	public int insertOrders(String userName,int orderPrice) {
		//根据用户名查询用户id
		User user = userService.selectUserByUserName(userName);
		int orderUserId = user.getUserId();
		System.out.println("开始添加");
		Order order = new Order();
		order.setOrderUserId(orderUserId);
		order.setOrderPrice(orderPrice);
		int result = orderService.insertOrder(order);
		int id = 0;
		if (result > 0) {
			System.out.println("订单创建成功");
			//获得订单主键
			id = order.getOrderId();
		}
		return id;
	}
	
	/**
	 * 添加订单详细信息
	 * @param items
	 * @return
	 */
	@PostMapping(value = "/insertItems")
	public int insertOrderItems(@RequestBody List<Item> items) {
		for (Item item: items){
            int result = orderService.insertOrderItem(item);
            //修改菜品剩余数量
            int foodId =item.getFoodId();
            int num = item.getNum();
            Menu menu = menuService.selectFoodByFoodId(foodId);
            int newFoodNum = menu.getFoodNum()-num;
            int a = menuService.updateFoodNumByFoodId(foodId, newFoodNum);
            if (result == 0 || a == 0) {
				return 0;
			}
        }
		return 1;
	}
	
	/**
	 * 根据订单id删除无效订单
	 * @param orderId
	 * @return
	 */
	@PostMapping(value = "/deleteNullOrder")
	public int deleteOrderByOrderId(int orderId) {
		System.out.println("删除订单"+orderId);
		int result = orderService.deleteOrderByOrderId(orderId);
		return result;
	}
	
	/**
	 * 根据用户名查询订单信息(待处理订单)
	 * @param userName
	 * @return
	 */
	@GetMapping(value = "/selectOrders")
	public List<Order> selectOrdersByOrderUserId(String userName){
		System.out.println("开始查询");
		User user = userService.selectUserByUserName(userName);
		int orderUserId = user.getUserId();
		System.out.println("用户id"+orderUserId);
		List<Order> orders = orderService.selectOrdersByOrderUserId(orderUserId);
		for (int i = 0; i < orders.size() ;i++) {
			orders.get(i).setOrderUserName(user.getUserName());
		}
		List<Order> list = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {
			if (!orders.get(i).getOrderType().equals("已完成")) {
				list.add(orders.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 根据用户名查询订单信息(全部订单)
	 * @param userName
	 * @return
	 */
	@GetMapping(value = "/selectAllOrders")
	public List<Order> selectAllOrdersByOrderUserId(String userName){
		System.out.println("开始查询");
		User user = userService.selectUserByUserName(userName);
		int orderUserId = user.getUserId();
		System.out.println("用户id"+orderUserId);
		List<Order> orders = orderService.selectOrdersByOrderUserId(orderUserId);
		for (int i = 0; i < orders.size() ;i++) {
			orders.get(i).setOrderUserName(user.getUserName());
		}
		return orders;
	}
	
	/**
	 * 根据订单id查询订单详细信息
	 * @param orderId
	 * @return
	 */
	@GetMapping(value = "/selectOrderItems")
	public List<Item> seleItemsByOrderId(int orderId){
		System.out.println("开始查询订单详细信息id:"+orderId);
		List<Item> items = orderService.selectItemsByOrderId(orderId);
		return items;
	}
	
	/**
	 * 根据订单id修改订单状态
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	@PostMapping(value = "/changeOrderType")
	public int changeOrderTypeByOrderId(int orderId,String orderType) {
		System.out.println("开始修改订单状态");
		int result = orderService.updateOrderByOrderType(orderId, orderType);
		if (result > 0) {
			System.out.println("订单状态已修改为:"+orderType);
		}
		return result;
	}
	
	/**
	 * 根据订单id删除订单信息
	 * @param orderId
	 * @return
	 */
	@PostMapping(value = "/deleteOrders")
	public int deleteOrdersByOrderId(int orderId) {
		System.out.println("开始删除订单,id:"+orderId);
		int result = orderService.deleteOrdersByOrderId(orderId);
		if (result > 0) {
			System.out.println("订单删除成功,开始删除订单详情");
			List<Item> items = orderService.selectItemsByOrderId(orderId);
			for (int i = 0; i < items.size(); i++) {
				int orderItemId = items.get(i).getOrderItemId();
				orderService.deleteOrderItemsByOrderItemId(orderItemId);
			}
			System.out.println("订单详情删除成功");
		}
		return result;
	}
	
	/**
	 * 	批量删除订单信息
	 * @param ids
	 * @return
	 */
	@PostMapping(value = "/deleteOrdersArray")
	public int deleteOrdersArray(int[] ids) {
		int a = 1;
		for (int i = 0; i < ids.length; i++) {
			int orderId = ids[i];
			int result = orderService.deleteOrdersByOrderId(orderId);
			if (result > 0) {
				//System.out.println("订单删除成功,开始删除订单详情");
				List<Item> items = orderService.selectItemsByOrderId(orderId);
				for (int j = 0; j < items.size(); j++) {
					int orderItemId = items.get(j).getOrderItemId();
					orderService.deleteOrderItemsByOrderItemId(orderItemId);
				}
				//System.out.println("订单详情删除成功");
			}
			else {
				a = 0;
			}
		}
		return a;
	}
	
	/**
	 * 根据用户名查询用户未完成订单状态数量
	 * @param userName
	 * @return
	 */
	@GetMapping(value = "/selectOrderTypeNum")
	public int selectOrderTypeNumByOrderUserId(String userName) {
		User user = userService.selectUserByUserName(userName);
		int orderUserId = user.getUserId();
		int result = orderService.selectOrderTypeNumByOrderUserId(orderUserId);
		return result;
	}
	
	/**
	 * 查询所有订单信息(未完成)
	 * @return
	 */
	@GetMapping(value = "/allOrders")
	public List<Order> selectAllOrders(){
		List<Order> orders = orderService.selectAllOrders();
		for (int i = 0; i < orders.size() ;i++) {
			User user = userService.selectUserByUserId(orders.get(i).getOrderUserId());
			orders.get(i).setOrderUserName(user.getUserName());
		}
		List<Order> list = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {
			if (!orders.get(i).getOrderType().equals("已完成")) {
				list.add(orders.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有订单信息
	 * @return
	 */
	@GetMapping(value = "/allAllOrders")
	public List<Order> selectAllAllOrders(){
		List<Order> orders = orderService.selectAllOrders();
		for (int i = 0; i < orders.size() ;i++) {
			User user = userService.selectUserByUserId(orders.get(i).getOrderUserId());
			orders.get(i).setOrderUserName(user.getUserName());
		}
		return orders;
	}
	

}
