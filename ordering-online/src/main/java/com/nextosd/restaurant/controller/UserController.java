package com.nextosd.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.mapper.common.UserMapper;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@PostMapping(value = "/checkUserName")
	public void checkUserName(String userName) {
		
	}
	

}
