package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.order.pojo.Food;
import cn.order.pojo.SysResult;
import cn.tedu.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@RequestMapping("/addFood")
	public SysResult addFood(Food food){
		try{
			foodService.addFood(food);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/all")
	public List<Food> queryAll(){
		return foodService.queryAll();
	}
	
	@RequestMapping("/delfood")
	public SysResult delFood(Integer id){
		try{
			foodService.delFood(id);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/queryOne")
	public Food queryOne(Integer id){
		return foodService.queryOne(id);
	}
	
	@RequestMapping("/queryOneByName")
	public Food queryOneByName(String name){
		return foodService.queryOneByName(name);
	}
	
	@RequestMapping("/updateFood")
	public SysResult update(Food food){
		try{
			foodService.update(food);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/zhengcan")
	public List<Food> query_1(String classify){
		return foodService.query_1(classify);
	}
}
