package com.nextosd.restaurant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.MenuExample;
import com.nextosd.restaurant.beans.other.ResultBean;
import com.nextosd.restaurant.mapper.common.MenuMapper;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuMapper menuService;
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	/**
	 * 查询
	 * @return
	 */
	@GetMapping(value = "/selectAll")
	public List<Menu> selectAll(){
		logger.info("查询所有信息");
		MenuExample example = new MenuExample();
		List<Menu> foods = menuService.selectByExample(example);
		logger.info("查询成功");
		return foods;
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
	 * 批量插入
	 * @return
	 */
	@PostMapping(value = "/list")	
	public ResultBean insertList(@RequestBody List<Menu> params) {
		return null;
	}
	
	/**
	 * 单独更新
	 * @return
	 */
	@PutMapping(value = "/form")
	public ResultBean updateForm(@ModelAttribute Menu params) {
		return null;
	}
	
	/**
	 * 批量更新
	 * @return
	 */
	@PutMapping(value = "/list")	
	public ResultBean updateList(@RequestBody List<Menu> params) {
		return null;
	}
	
	/**
	 * 按主键删除一个
	 * @return
	 */
	@DeleteMapping(value = "/id")
	public ResultBean deleteById(@RequestBody Menu params) {
		return null;
	}
	
	/**
	 * 按主键删除多个
	 * @return
	 */
	@DeleteMapping(value = "/ids")	
	public ResultBean deleteByIds(@RequestBody Menu params) {
		return null;
	}
	
	/**
	 * 按条件删除
	 * @return
	 */
	@DeleteMapping(value = "/cond")	
	public ResultBean deleteByCond(@RequestBody Menu params) {
		return null;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@GetMapping(value = "/page")
	public Map<String, Object> selectByPage(@ModelAttribute Menu params){
		PageHelper.startPage(params.getPage(), params.getLimit());
		MenuExample example = new MenuExample();
		List<Menu> list = menuService.selectByExample(example);
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);
		map.put("data",pageInfo.getList());
		map.put("count",pageInfo.getTotal());
		map.put("msg",null);
		map.put("code",0);
		return map;
	}
}

