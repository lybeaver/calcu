package com.nextosd.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.mapper.MenuMapper;

@Service
public class MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	/**
	 * 	菜品信息全查询
	 * @return
	 */
	public List<Menu> selectAllFoods(){
		List<Menu> foods = menuMapper.selectAllFoods();
		return foods;
	}
	
	/**
	 * 	查询菜品信息(模糊查)
	 * @param foodName  菜名
	 * @return 	Menu	菜品信息
	 */
	public List<Menu> selectFoodsLikeFoodName(String foodName) {
		List<Menu> foods = menuMapper.selectFoodsLikeFoodName(foodName);
		return foods;
	}
	
	/**
	 * 	根据菜品ID查询菜品信息
	 * @param foodId
	 * @return
	 */
	public Menu selectFoodByFoodId(int foodId) {
		Menu food = menuMapper.selectMenuByFoodId(foodId);
		return food;
	}
	
	/**
	 * 	添加菜品
	 * @param menu
	 * @return
	 */
	public int insertFood(Menu menu) {
		int result = menuMapper.insertFood(menu);
		return result;
	}
	
	/**
	 * 	修改菜品信息
	 * @param menu
	 * @return
	 */
	public int updateFood(Menu menu) {
		int result = menuMapper.updateFood(menu);
		return result;
	}
	
	/**
	 * 	删除菜品信息
	 * @param foodId
	 * @return
	 */
	public int deleteFoodByFoodId(int foodId) {
		int result = menuMapper.deleteFoodByFoodId(foodId);
		return result;
	}
	
	/**
	 * 	根据菜品类型查询菜品信息
	 * @param foodType
	 * @return
	 */
	public List<Menu> selectFoodsByFoodType(String foodType){
		List<Menu> foods = menuMapper.selectFoodsByFoodType(foodType);
		return foods;
	}
	
	/**
	 * 	根据订单id修改剩余数量
	 * @param foodId
	 * @param foodNum
	 * @return
	 */
	public int updateFoodNumByFoodId(int foodId,int foodNum) {
		int result = menuMapper.updateFoodNumByFoodId(foodId, foodNum);
		return result;
	}

}
