package com.nextosd.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nextosd.restaurant.beans.User;
@Mapper
public interface UserMapper {
	
	/**
	 *	按照用户名查询用户信息
	 * @param userName
	 * @return
	 */
	User selectUserByUserName(String userName);
	
	/**
	 * 	添加用户信息（注册）
	 * @param user
	 * @return
	 */
	int insertUser(User user);
	
	/**
	 * 	根据用户名修改密码
	 * @param userName
	 * @param password
	 * @return
	 */
	int updatePassword(String userName,String password);
	
	/**
	 * 根据用户id查询用户信息
	 * @return
	 */
	User selectUserByUserId(int userId);

}
