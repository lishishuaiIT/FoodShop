package cn.tedu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.utils.CookieUtils;


import cn.order.pojo.SysResult;
import cn.order.pojo.User;
import cn.tedu.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public SysResult register(User user){
		try{
			userService.register(user);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/queryOne")
	public SysResult queryOne(String phone){
		try{
			List<User> list=userService.queryOne(phone);
			if(list!=null){
				return SysResult.build(201, "账户已注册", null);
			}else{
				return SysResult.ok();				
			}
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/login")
	private SysResult login(User user,HttpServletRequest req,HttpServletResponse resp){
		try{
			String root=userService.login(user);
			if(!StringUtils.isEmpty(root)){
				CookieUtils.setCookie(req, resp, "order_user", root);
				return SysResult.ok();
			}else{
				return SysResult.build(201, "system error",null);
			}
		}catch (Exception e) {
			return SysResult.build(201, "登录失败", null);
		}
	}
	
	@RequestMapping("query/{ticket}")
	public SysResult queryTicket(@PathVariable
			String ticket){
		
		String userJson=userService.queryTicket(ticket);
		if(!StringUtils.isNotEmpty(userJson)){
			return SysResult.build(201, "", null);
		}else{
			return SysResult.build(200, "ok", userJson);
		}
		
	}
}
