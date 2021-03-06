package com.nextosd.restaurant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.beans.ShoppingCar;
import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.mapper.common.UserMapper;
import com.nextosd.restaurant.service.ShoppingCarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		int result = 0;
		int userId = shoppingCar.getCarUserId();
		//根据用户id获取用户名
		User user = userMapper.selectByPrimaryKey(userId);
		String carUserName = user.getUserName();
		shoppingCar.setCarUserName(carUserName);
		//根据foodId和userId判断购物车内是否存在重复记录
		ShoppingCar car = shoppingCarService.selectMsgByFoodId(shoppingCar.getCarFoodId(),userId);
		if (car == null) {
			log.info("加入购物车中....不存在重复记录，直接执行添加。");
			//执行加入购物车操作
			result = shoppingCarService.insertCarMsg(shoppingCar);
		}else {
			log.info("加入购物车中....存在重复记录，执行修改已存在数据的数量操作。");
			//执行修改现有记录操作
			int carId = car.getCarId();
			int carFoodNum = car.getCarFoodNum() + 1;
			int carAllPrice = car.getCarOnePrice() * carFoodNum;
			shoppingCar.setCarId(carId);
			shoppingCar.setCarFoodNum(carFoodNum);
			shoppingCar.setCarAllPrice(carAllPrice);
			result = shoppingCarService.updShoppingCarMsg(shoppingCar);
		}
		return result;
	}
	
	/**
	 * 获取购物车记录条数
	 * @return
	 */
	@GetMapping(value = "/getShoppingCarCount")
	public int getShoppingCarCount() {
		int count = shoppingCarService.getShoppingCarCount();
		log.info("已获得购物车记录条数:" + count);
		return count;
	}
	
	/**
	 * 查询购物车所有记录
	 * @return
	 */
	@GetMapping(value = "/getCarMsg")
	public Map<String, Object> getShoppingCarMsg() {
		log.info("查询购物车所有记录中....");
		List<ShoppingCar> list = shoppingCarService.getShoppingCarMsg();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("msg", null);
		map.put("code", 0);
		return map;
	}
	
	/**
	 * 执行修改记录的数量操作
	 * @param shoppingCar
	 * @return
	 */
	@PostMapping(value = "/updFoodNum")
	public int updFoodNum(ShoppingCar shoppingCar) {
		log.info("修改购物车记录数量中....");
		int result = shoppingCarService.updShoppingCarMsg(shoppingCar);
		return result;
	}
	
	/**
	 * 购物车删除一条记录
	 * @param carId
	 * @return
	 */
	@PostMapping(value = "/delShoppingMsg")
	public int delShoppingMsg(int carId) {
		log.info("删除购物车记录,id:" + carId);
		int result = shoppingCarService.deleteShoppingMsg(carId);
		return result;
	}
	
	/**
	 * 计算选中的数据数量和总价
	 * @param list
	 * @return
	 */
	@PostMapping(value = "/getAllNumAndPrice")
	public Map<String, Integer> getAllNumAndPrice(@RequestBody List<ShoppingCar> list) {
		int carFoodNum = 0;
		int carAllPrice = 0;
		for (ShoppingCar shoppingCar : list) {
			//System.out.println(shoppingCar);
			carFoodNum = shoppingCar.getCarFoodNum() + carFoodNum;
			carAllPrice = shoppingCar.getCarAllPrice() + carAllPrice;
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("carFoodNum", carFoodNum);
		map.put("carAllPrice", carAllPrice);
		return map;
	}
	
	
	
	
	
	
	
	
	

}
