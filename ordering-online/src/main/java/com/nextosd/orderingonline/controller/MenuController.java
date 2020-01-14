package com.nextosd.orderingonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.orderingonline.beans.Menu;
import com.nextosd.orderingonline.service.MenuService;

@RestController
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 	菜品信息全查询
	 * @return
	 */
	@GetMapping(value = "/selectAll")
	public List<Menu> selectAllFoods(){
		System.out.println("菜品信息全查询");
		List<Menu> foods = menuService.selectAllFoods();
		System.out.println("查询成功");
		return foods;
	}
	
	/**
	 * 	根据菜名查询菜品信息(模糊查)
	 * @param foodName
	 * @return
	 */
	@RequestMapping(value = "/menu",method = RequestMethod.GET)
	public List<Menu> selectFoodsLikeFoodName(String foodName) {
		foodName = "%"+foodName+"%";
		System.out.println("开始查询"+foodName);
		List<Menu> foods = menuService.selectFoodsLikeFoodName(foodName);
		System.out.println("查询成功");
		return foods;
	}	
	
	/**
	 * 	根据菜品ID查询菜品信息(用于修改菜品信息前)
	 * @param foodId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insertFoodFirst",method = RequestMethod.GET)
	public Menu selectFoodByFoodId(int foodId) {
		Menu food = menuService.selectFoodByFoodId(foodId);
		return food;
	}
	
	/**
	 * 	添加菜品信息
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insertFood",method = RequestMethod.POST)
	public int insertFood(Menu menu) {
		System.out.println("开始添加");
		int result = menuService.insertFood(menu);
		System.out.println("添加成功");
		return result;
	}
	
	/**
	 * 	修改菜品信息
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateFood",method = RequestMethod.POST)
	public int updateFood(Menu menu) {
		int result = menuService.updateFood(menu);
		return result;
	}
	
	/**
	 * 	删除菜品信息
	 * @param foodId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteFood",method = RequestMethod.POST)
	public int deleteFoodByFoodId(int foodId) {
		System.out.println("开始删除"+foodId);
		int result = menuService.deleteFoodByFoodId(foodId);
		System.out.println("删除成功");
		return result;
	}
	
	/**
	 * 	根据菜品类型查询菜品信息
	 * @param foodType
	 * @return
	 */
	@GetMapping(value = "/foodType")
	public List<Menu> selectFoodsByFoodType(String foodType){
		List<Menu> foods = menuService.selectFoodsByFoodType(foodType);
		return foods;
	}
	
}
