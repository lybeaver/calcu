package com.nextosd.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.other.BaseBean;
@Mapper
public interface MenuMapperBack {
	
	/**
	 * 	菜品信息全查询
	 * @return
	 */
	List<Menu> selectAllFoods();
	
	/**
	 * 菜品信息限制查询
	 * @param baseBean
	 * @return
	 */
	List<Menu> selectLimitFoods(BaseBean baseBean);
	
	/**
	 * 	菜品信息全查询(模糊查)
	 * @param foodName	菜名
	 * @return
	 */
	List<Menu> selectFoodsLikeFoodName(String foodName);
	
	/**
	 * 	根据菜品ID查询菜品信息
	 * @param foodId
	 * @return
	 */
	Menu selectMenuByFoodId(int foodId);
	
	/**
	 * 	添加菜品
	 * @param menu
	 * @return
	 */
	int insertFood(Menu menu);
	
	/**
	 * 	修改菜品信息
	 * @param menu
	 * @return
	 */
	int updateFood(Menu menu);
	
	/**
	 * 	删除菜品信息
	 * @param foodId
	 * @return
	 */
	int deleteFoodByFoodId(int foodId);
	
	/**
	 * 	根据菜品类型查询菜品信息
	 * @param foodType
	 * @return
	 */
	List<Menu> selectFoodsByFoodType(String foodType);
	
	/**
	 * 	根据菜品id修改剩余数量
	 * @param foodId
	 * @param foodNum
	 * @return
	 */
	int updateFoodNumByFoodId(int foodId,int foodNum);

}
