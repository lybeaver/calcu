package com.nextosd.restaurant.service;

import java.util.List;

import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.beans.other.BaseBean;

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
	
	
	/**
	 *	用户信息限制查询
	 * @param baseBean
	 * @return
	 */
	public List<User> selectLimitUsers(BaseBean baseBean);
}
