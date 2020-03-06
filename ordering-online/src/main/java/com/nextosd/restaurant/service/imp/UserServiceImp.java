package com.nextosd.restaurant.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.beans.other.BaseBean;
import com.nextosd.restaurant.mapper.UserMapperBack;
import com.nextosd.restaurant.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserMapperBack userMapper;
	
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

	/**
	 * 分页查询用户信息
	 * @return
	 */
	public List<User> selectLimitUsers(BaseBean baseBean) {
		List<User> users=userMapper.selectLimitUsers(baseBean);
		return users;
	}

}
