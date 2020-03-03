package com.nextosd.restaurant.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextosd.restaurant.beans.Menu;
import com.nextosd.restaurant.beans.MenuExample;
import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.beans.UserExample;
import com.nextosd.restaurant.beans.other.BaseBean;
import com.nextosd.restaurant.beans.other.LoginBean;
import com.nextosd.restaurant.mapper.UserMapperBack;
import com.nextosd.restaurant.mapper.common.UserMapper;
import com.nextosd.restaurant.service.UserService;
import com.nextosd.restaurant.utils.Md5Util;
import com.nextosd.restaurant.utils.VerifyUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMapperBack userMapperBack;
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//获取验证码
	@GetMapping("/verify-code")
	public void getCode(HttpServletResponse response,HttpSession session) throws Exception{
	    Map<String, Object> map = VerifyUtil.createImage();
	    String sb = (String) map.get("code");
	    logger.info("获取验证码："+sb);
	    session.setAttribute("checkCode", sb);
	    logger.info("session放入验证码"+(String)session.getAttribute("checkCode"));
	    //将图片输出给浏览器
	    BufferedImage image = (BufferedImage) map.get("image");
	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(image, "png", os);
	}

	/**
	  * 	登录
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年2月24日
	 */
	@PostMapping(value = "/login")
	public int login(LoginBean lgBean,HttpSession session) throws Exception {
		int result = 0;
		String check = (String)session.getAttribute("checkCode");
		logger.info("session:"+check);
		logger.info("用户名:"+lgBean.getUserName());
//		if (!lgBean.equals(check)) {
//			logger.info("验证码错误");
//			result = 2 ;
//			return result;
//		}
		User exUser = userMapperBack.selectUserByUserName(lgBean.getUserName());
		if (exUser == null) {
			logger.info("用户名不存在");
			return result;
		}
		String md5EmcodeString = Md5Util.md5Encode(lgBean.getPassword());
		if (exUser.getPassword().equals(md5EmcodeString)) {
			logger.info("登录成功");
			result = 1;
			return result;
		}
		logger.info("密码错误");
		return result;
	}
	/**
	  *	注册用户
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年3月2日
	 */
	@PostMapping(value = "/insertUser")
	public int insertUser(User user) throws Exception {
		System.out.println("开始注册");
		String pd = user.getPassword();
		logger.info(user.getUserName());
		String md5EncodeString = Md5Util.md5Encode(pd);
		logger.info("md5加密后"+md5EncodeString);
		user.setPassword(md5EncodeString);
		Date now = new Date();
		user.setLogTime(now);
		user.setUserType(user.getUserType());//2的等级为用户，0为管理者，1为商家
		int result = userMapper.insertSelective(user);
		return result;
	}
	/**
	  *	校验用户是否存不存在
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年3月2日
	 */
	@GetMapping(value = "/checkUser")
	public int checkMessage(String userName) {
		User user = userMapperBack.selectUserByUserName(userName);
		int result = 0;
		if (user == null) {
			//如果用户可用，返回1
			result = 1;
		}
		//如果用户存在，返回0
		return result;
	}
	/**
	  *	个人资料
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年3月2日
	 */
	@GetMapping(value = "/personalData")
	public User personalData(String userName) {
		logger.info("个人资料跳转.........");
		User user = userMapperBack.selectUserByUserName(userName);
		return user;
	}
	
	@PostMapping(value = "/updateUser")
	public int updateUser(User user) {
		logger.info("修改密码跳转.......");
		User ouser = userMapperBack.selectUserByUserName(user.getUserName());
		int result = userMapper.updateByPrimaryKeySelective(user);
		
		return 0;
	}
	
	
	/**
	 * 分页查询
	 * @return
	 */
	@GetMapping(value = "/page")
	public Map<String, Object> selectByPage(@ModelAttribute BaseBean params){
		PageHelper.startPage(params.getPage(), params.getLimit());
		logger.info("当前是第"+params.getPage()+"页,每页显示"+params.getLimit()+"条。");
		//计算每页的起始记录条数
		int pageCount = (params.getPage()-1)*params.getLimit();
		params.setPage(pageCount);
		List<User> users = userService.selectLimitUsers(params);
		//装箱发货
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		UserExample example = new UserExample();
		//计算总记录数
		long count = userMapper.countByExample(example);
		logger.info("总记录数:"+count);
		map.put("data",pageInfo.getList());
		map.put("count",count);
		map.put("msg",null);
		map.put("code",0);
		return map;
	}
}
