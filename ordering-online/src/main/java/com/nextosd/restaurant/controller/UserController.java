package com.nextosd.restaurant.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextosd.restaurant.mapper.common.UserMapper;
import com.nextosd.restaurant.utils.VerifyUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@PostMapping(value = "/checkUserName")
	public void checkUserName(String userName) {
		
	}
	
	
	@GetMapping(value = "/getCheckNum")
	public String getCheckNum() {
		
		return null;
	}
	
	//获取验证码
	@GetMapping("/verify-code")
	public void getCode(HttpServletResponse response) throws Exception{
	    Map<String, Object> map = VerifyUtil.createImage();
	    //将图片输出给浏览器
	    BufferedImage image = (BufferedImage) map.get("image");
	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(image, "png", os);
	}

	

}
