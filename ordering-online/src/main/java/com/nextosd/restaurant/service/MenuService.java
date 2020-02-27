package com.nextosd.restaurant.service;

import java.util.List;

import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.other.BaseBean;

public interface MenuService {
	
	/**
	 * 	菜品信息全查询
	 * @return
	 */
	public List<Menu> selectAllFoods();
	
	/**
	 * 菜品信息限制查询
	 * @param baseBean
	 * @return
	 */
	public List<Menu> selectLimitFoods(BaseBean baseBean);
	
	
	/**
	 * 	查询菜品信息(模糊查)
	 * @param foodName  菜名
	 * @return 	Menu	菜品信息
	 */
	public List<Menu> selectFoodsLikeFoodName(String foodName);
	
	/**
	 * 	根据菜品ID查询菜品信息
	 * @param foodId
	 * @return
	 */
	public Menu selectFoodByFoodId(int foodId);
	
	/**
	 * 	添加菜品
	 * @param menu
	 * @return
	 */
	public int insertFood(Menu menu);
	
	/**
	 * 	修改菜品信息
	 * @param menu
	 * @return
	 */
	public int updateFood(Menu menu);
	
	/**
	 * 	删除菜品信息
	 * @param foodId
	 * @return
	 */
	public int deleteFoodByFoodId(int foodId);
	
	/**
	 * 	根据菜品类型查询菜品信息
	 * @param foodType
	 * @return
	 */
	public List<Menu> selectFoodsByFoodType(String foodType);
	
	/**
	 * 	根据订单id修改剩余数量
	 * @param foodId
	 * @param foodNum
	 * @return
	 */
	public int updateFoodNumByFoodId(int foodId,int foodNum);
	
	/**
	 * 查询所有菜品类型
	 * @return
	 */
	public List<String> selectAllFoodTypes();

}
