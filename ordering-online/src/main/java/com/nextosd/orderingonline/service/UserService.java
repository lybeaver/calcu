package com.nextosd.orderingonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextosd.orderingonline.beans.User;
import com.nextosd.orderingonline.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 	根据用户名查询用户信息是否已存在
	 * @param userName
	 * @return
	 */
	public User selectUserByUserName(String userName) {
		User user = userMapper.selectUserByUserName(userName);
		return user;
	}
	
	/**
	 * 	添加用户信息（注册）
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		int result = userMapper.insertUser(user);
		return result;
	}
	
	/**
	 * 	根据用户名修改密码
	 * @param userName
	 * @param password
	 * @return
	 */
	public int updatePassword(String userName,String password) {
		int result = userMapper.updatePassword(userName, password);
		return result;
	}
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	public User selectUserByUserId(int userId) {
		User user = userMapper.selectUserByUserId(userId);
		return user;
	}

}
