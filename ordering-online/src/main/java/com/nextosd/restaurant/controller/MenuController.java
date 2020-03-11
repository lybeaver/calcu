package com.nextosd.restaurant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.MenuExample;
import com.nextosd.restaurant.beans.other.BaseBean;
import com.nextosd.restaurant.beans.other.ResultBean;
import com.nextosd.restaurant.mapper.common.MenuMapper;
import com.nextosd.restaurant.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 查询所有菜品类型
	 */
	@GetMapping(value = "/getTypes")
	public List<String> getAllFoodTypes() {
		log.getClass();
		List<String> types = menuService.selectAllFoodTypes();
		return types;
	}
	
	/**
	 * 单独插入
	 * @return
	 */
	@PostMapping(value = "/form")
	public ResultBean inserForm(@ModelAttribute Menu params) {
		return null;
	}
	
	/**
	 * 更新菜品
	 * @return
	 */
	@PostMapping(value = "/updMenuMsg")
	public int updateMenuMsg(@ModelAttribute Menu params) {
		log.info("修改菜品信息中....");
		int result = menuService.updateFood(params);
		return result;
	}
	
	/**
	 * 按主键删除一个
	 * @return
	 */
	@PostMapping(value = "/delFoodById")
	public int deleteById(@RequestBody Menu params) {
		log.info("删除菜品，主键为:"+params.getFoodId());
		int result = menuMapper.deleteByPrimaryKey(params.getFoodId());
		return result;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@GetMapping(value = "/page")
	public Map<String, Object> selectByPage(@ModelAttribute BaseBean params){
		PageHelper.startPage(params.getPage(), params.getLimit());
		int nowPage = params.getPage();
		int nowLimit = params.getLimit();
		//计算每页的起始记录条数
		int pageCount = (params.getPage()-1)*params.getLimit();
		params.setPage(pageCount);
		//查询当前页记录
		List<Menu> foods = menuService.selectLimitFoods(params);
		//计算总记录数
		MenuExample example = new MenuExample();
		long count = menuMapper.countByExample(example);
		log.info("当前是第"+nowPage+"页,每页显示"+nowLimit+"条记录,总记录数:"+count);
		//装箱发货
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<Menu> pageInfo = new PageInfo<Menu>(foods);
		map.put("data",pageInfo.getList());
		map.put("count",count);
		map.put("msg",null);
		map.put("code",0);
		return map;
	}
	
	/**
	 * 菜品信息模糊查询
	 * @param foodName
	 * @return
	 */
	@GetMapping(value = "/getMenuLikeNameMsg")
	public Map<String, Object> getMenuLikeNameMsg(String foodName) {
		log.info("开始查询包含" + foodName + "的记录....");
		List<Menu> list = menuService.selectFoodsLikeFoodName(foodName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",list);
		map.put("count",list.size());
		map.put("msg",null);
		map.put("code",0);
		return map;
	}
}

