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

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextosd.restaurant.beans.User;
import com.nextosd.restaurant.beans.UserExample;
import com.nextosd.restaurant.beans.other.BaseBean;
import com.nextosd.restaurant.beans.other.LoginBean;
import com.nextosd.restaurant.mapper.common.UserMapper;
import com.nextosd.restaurant.service.UserService;
import com.nextosd.restaurant.utils.Md5Util;
import com.nextosd.restaurant.utils.VerifyUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取验证码
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@GetMapping("/verify-code")
	public void getCode(HttpServletResponse response,HttpSession session) throws Exception{
	    Map<String, Object> map = VerifyUtil.createImage();
	    String sb = (String) map.get("code");
	    log.info("获取验证码："+sb);
	    session.setAttribute("checkCode", sb);
	    //将图片输出给浏览器
	    BufferedImage image = (BufferedImage) map.get("image");
	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(image, "png", os);
	}

	/**
	  * 登录
	  * @Title UserController.java  
	  * @param 
	  * @return  
	  * @date 2020/2/24
	 */
	@PostMapping(value = "/login")
	public int login(LoginBean lgBean,HttpSession session) throws Exception {
		int result = 1;
		String check = (String)session.getAttribute("checkCode");
		log.info("用户:"+lgBean.getUserName()+"登陆中....");
		if (!lgBean.getCheckNum().equalsIgnoreCase(check)) {
			log.info("验证码错误");
			result = 2;
		}
		User exUser = userService.selectUserByUserName(lgBean.getUserName());
		if (exUser == null) {
			log.info("用户名不存在");
			result = 0;
		}
		String md5EmcodeString = Md5Util.md5Encode(lgBean.getPassword());
		if (!exUser.getPassword().equals(md5EmcodeString)) {
			log.info("密码错误");
			result = 0;
		}
		log.info("登录成功");
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
		log.info("开始注册....");
		String pd = user.getPassword();
		log.info(user.getUserName());
		String md5EncodeString = Md5Util.md5Encode(pd);
		log.info("md5加密后密码:"+md5EncodeString);
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
		int result = 0;
		User user = userService.selectUserByUserName(userName);
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
		log.info("个人资料跳转中.........");
		User user = userService.selectUserByUserName(userName);
		return user;
	}
	
	/**
	  *  密码转换
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年3月4日
	 */
	@PostMapping("/switch")
	public String switchPassword(String password) throws Exception {
		String md5EncodeString = Md5Util.md5Encode(password);
		log.info(".....密码转换:"+md5EncodeString);
		return md5EncodeString;
	}
	
	/**
	  *	修改密码
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年3月4日
	  */
	@PostMapping(value = "/updateUser")
	public int updateUser(User user) {
		log.info("修改密码跳转.......");
		user.setLogTime(new Date());
		int result = userMapper.updateByPrimaryKeySelective(user);
		System.out.println(".............."+result);
		return result;
	}
	
	
	/**
	    * 分页查询
	 * @return
	 */
	@GetMapping(value = "/page")
	public Map<String, Object> selectByPage(@ModelAttribute BaseBean params){
		PageHelper.startPage(params.getPage(), params.getLimit());
		log.info("当前是第"+params.getPage()+"页,每页显示"+params.getLimit()+"条。");
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
		log.info("总记录数:"+count);
		map.put("data",pageInfo.getList());
		map.put("count",count);
		map.put("msg",null);
		map.put("code",0);
		return map;
	}
	
	/**
	 * 根据用户名查询用户id
	 * @param userName
	 * @return 
	 */
	@GetMapping(value = "/getUserId")
	public int getUserId(String userName) {
		User user = userService.selectUserByUserName(userName);
		int userId = user.getUserId();
		return userId;
	}
	
	/**
	  * 修改用户权限
	  * @Title: UserController.java  
	  * @param 
	  * @return  
	  * @date 2020年3月10日
	  */
	@PostMapping(value = "/updUserMsg")
	public  int updUserMsg(User user,@Param("cokName")String cokName) {
		log.info("修改用户权限.........."+cokName);
		User cuser = userService.selectUserByUserName(cokName);
		User ouer = userService.selectUserByUserName(user.getUserName());
		int result = 0;
		if (cuser.getUserType()< ouer.getUserType()) {
			 user.setLogTime(new Date());
			 result = userMapper.updateByPrimaryKeySelective(user);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}
