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
import com.nextosd.restaurant.utils.Md5Util;
import com.nextosd.restaurant.utils.VerifyUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	//@Autowired
	//private UserMapper userMapper;
	
	@Autowired
	private UserMapperBack userMapperBack;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	
	//获取验证码s
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
