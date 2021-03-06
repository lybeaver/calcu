package com.nextosd.restaurant.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.other.BaseBean;
import com.nextosd.restaurant.mapper.MenuMapperBack;
import com.nextosd.restaurant.service.MenuService;

@Service
public class MenuServiceImp implements MenuService {
	
	@Autowired
	private MenuMapperBack menuMapper;
	
	/**
	 * 菜品信息全查询
	 * @return
	 */
	public List<Menu> selectAllFoods(){
		List<Menu> foods = menuMapper.selectAllFoods();
		return foods;
	}
	
	/**
	 * 菜品信息限制查询
	 * @return
	 */
	public List<Menu> selectLimitFoods(BaseBean baseBean){
		List<Menu> foods = menuMapper.selectLimitFoods(baseBean);
		return foods;
	}
	
	/**
	 * 查询菜品信息(模糊查)
	 * @param foodName  菜名
	 * @return 	Menu	菜品信息
	 */
	public List<Menu> selectFoodsLikeFoodName(String foodName) {
		String name = "%" + foodName + "%";
		List<Menu> foods = menuMapper.selectFoodsLikeFoodName(name);
		return foods;
	}
	
	/**
	 * 根据菜品ID查询菜品信息
	 * @param foodId
	 * @return
	 */
	public Menu selectFoodByFoodId(int foodId) {
		Menu food = menuMapper.selectMenuByFoodId(foodId);
		return food;
	}
	
	/**
	 * 添加菜品
	 * @param menu
	 * @return
	 */
	public int insertFood(Menu menu) {
		int result = menuMapper.insertFood(menu);
		return result;
	}
	
	/**
	 * 修改菜品信息
	 * @param menu
	 * @return
	 */
	public int updateFood(Menu menu) {
		int result = menuMapper.updateFood(menu);
		return result;
	}
	
	/**
	 * 删除菜品信息
	 * @param foodId
	 * @return
	 */
	public int deleteFoodByFoodId(int foodId) {
		int result = menuMapper.deleteFoodByFoodId(foodId);
		return result;
	}
	
	/**
	 * 根据菜品类型查询菜品信息
	 * @param foodType
	 * @return
	 */
	public List<Menu> selectFoodsByFoodType(String foodType){
		List<Menu> foods = menuMapper.selectFoodsByFoodType(foodType);
		return foods;
	}
	
	/**
	 * 根据订单id修改剩余数量
	 * @param foodId
	 * @param foodNum
	 * @return
	 */
	public int updateFoodNumByFoodId(int foodId,int foodNum) {
		int result = menuMapper.updateFoodNumByFoodId(foodId, foodNum);
		return result;
	}
	
	/**
	 * 查询所有菜品类型
	 * @return
	 */
	public List<String> selectAllFoodTypes(){
		List<String> types = menuMapper.selectAllFoodTypes();
		return types;
	}

	/**
	 * 查询最贵的菜品价格
	 * @return
	 */
	public int selectMaxPrice() {
		int price = menuMapper.selectMaxPrice();
		return price;
	}

	/**
	 * 根据条件查询菜品信息
	 * @param menu
	 * @return list
	 */
	public List<Menu> selectMenuByTypes(Menu menu) {
		List<Menu> list = menuMapper.selectMenuByTypes(menu);
		return list;
	}

}
