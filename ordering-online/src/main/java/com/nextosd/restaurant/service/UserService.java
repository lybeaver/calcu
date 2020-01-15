package com.nextosd.restaurant.service;

import com.nextosd.restaurant.beans.User;

public interface UserService {
	
	/**
	 * 	根据用户名查询用户信息是否已存在
	 * @param userName
	 * @return
	 */
	public User selectUserByUserName(String userName);
	
	/**
	 * 	添加用户信息（注册）
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 	根据用户名修改密码
	 * @param userName
	 * @param password
	 * @return
	 */
	public int updatePassword(String userName,String password);
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	public User selectUserByUserId(int userId);

}
