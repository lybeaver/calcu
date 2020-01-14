package com.nextosd.orderingonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.orderingonline.beans.User;
import com.nextosd.orderingonline.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 	初始化页面
	 * @return
	 */
	@RequestMapping(value = "/signup",method = RequestMethod.GET)
	public String init() {
		return "signup";
	}
	
	/**
	 * 	根据用户名查询用户是否已存在
	 * @param userName	用户名
	 * @return
	 */
	@RequestMapping(value = "/checkUser",method = RequestMethod.GET)
	public int checkMessage(String userName) {
		User user = userService.selectUserByUserName(userName);
		int result = 0;
		if (user == null) {
			//如果用户可用，返回1
			result = 1;
		}
		//如果用户存在，返回0
		return result;
	}
	
	/**
	 * 	添加用户信息(注册)
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/insertUser")
	public int insertUser(User user) {
		System.out.println("开始注册");
		int result = userService.insertUser(user);
		return result;
	}
	
	/**
	 * 	登录
	 * @param userName
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public int login(String userName,String password) {
		User user = userService.selectUserByUserName(userName);
		int result = 0;
		if (user == null) {
			System.out.println("用户名不存在");
			return result;
		}
		if (user.getPassword().equals(password)) {
			System.out.println("登录成功");
			result = 1;
			return result;
		}
		System.out.println("密码错误");
		return result;
	}
	
	/**
	 * 	根据用户名查询用户密码
	 * @param userName
	 * @return
	 */
	@GetMapping(value = "/selectPassword")
	public String selectPasswordByUserName(String userName) {
		User user = userService.selectUserByUserName(userName);
		String password = user.getPassword();
		return password;
	}
	
	/**
	 * 	根据用户名修改密码
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/updatePassword")
	public int updatePassword(String userName,String password) {
		int result = userService.updatePassword(userName, password);
		return result;
	}
	
	/**
	 * 	根据用户名查询用户状态
	 * @param userName
	 * @return
	 */
	@GetMapping(value = "/selectUserType")
	public int selectUserTypeByUserName(String userName) {
		User user = userService.selectUserByUserName(userName);
		int userType = user.getUserType();
		return userType;
	}

}
