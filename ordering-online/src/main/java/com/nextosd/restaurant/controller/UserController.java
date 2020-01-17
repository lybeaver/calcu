package com.nextosd.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.mapper.common.UserMapper;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	

}
