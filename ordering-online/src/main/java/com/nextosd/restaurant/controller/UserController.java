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
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping(value = "/checkUserName")
	public void checkUserName(String userName) {
		
	}
	
	@GetMapping(value = "/getCheckNum")
	public String getCheckNum() {
		
		return null;
	}
	
	/**
	 * 	登录
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年2月24日
	 */
	@PostMapping(value = "/login")
	public int login(User user) throws Exception {
		int result = 0;
		logger.info(user.getUserName());
		User exUser = userMapper.selectByName(user.getUserName());
		String md5EmcodeString = Md5Util.md5Encode(user.getPassword());
		if (exUser == null) {
			logger.info("用户名不存在");
			return result;
		}
		if (exUser.getPassword().equals(md5EmcodeString)) {
			logger.info("登录成功");
			result = 1;
			return result;
		}
		System.out.println("密码错误");
		return result;
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
