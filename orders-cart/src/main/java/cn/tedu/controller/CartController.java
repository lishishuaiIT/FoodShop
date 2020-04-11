package cn.tedu.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.order.pojo.Cart;
import cn.order.pojo.SysResult;
import cn.tedu.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/query")
	public List<Cart> query(String userid){
		if(StringUtils.isNotEmpty(userid)){
			return cartService.queryCart(userid);			
		}else{
			return null;
		}	
		
	}
	
	@RequestMapping("/save")
	public SysResult addCart(Cart cart){
		try{
			cartService.addCart(cart);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/update")
	public SysResult update(String userid,String fname,Integer num){
		System.out.println(fname);
		try{
			cartService.update(userid,fname,num);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/delete")
	public SysResult update(String userid,String fname){
		System.out.println(fname);
		try{
			cartService.delete(userid,fname);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
}
