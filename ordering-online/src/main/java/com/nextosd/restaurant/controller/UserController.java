package com.nextosd.restaurant.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.beans.other.LoginBean;
import com.nextosd.restaurant.mapper.UserMapperBack;
import com.nextosd.restaurant.mapper.common.UserMapper;
import com.nextosd.restaurant.utils.Md5Util;
import com.nextosd.restaurant.utils.VerifyUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMapperBack userMapperBack;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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
		if (!lgBean.equals(check)) {
			logger.info("验证码错误");
			result = 2 ;
			return result;
		}
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
	
	//获取验证码
	@GetMapping("/verify-code")
	public void getCode(HttpServletResponse response,HttpSession session) throws Exception{
	    Map<String, Object> map = VerifyUtil.createImage();
	    String  sb = (String) map.get("code");
	    logger.info("获取验证码："+sb);
	    session.setAttribute("checkCode", sb);
	    logger.info("session放入验证码"+(String)session.getAttribute("checkCode"));
	    //将图片输出给浏览器
	    BufferedImage image = (BufferedImage) map.get("image");
	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(image, "png", os);
	}

	

}
