package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.order.pojo.Food;
import cn.tedu.mapper.FoodMapper;

@Service
public class FoodService {

	@Autowired
	private FoodMapper foodMapper;
	
	public void addFood(Food food) {
		foodMapper.addFood(food);
	}

	public List<Food> queryAll() {
		return foodMapper.queryAll();
	}

	public void delFood(Integer id) {
		foodMapper.delFood(id);
	}

	public Food queryOne(Integer id) {
		return foodMapper.queryOne(id);
	}

	public void update(Food food) {
		foodMapper.update(food);
	}

	public List<Food> query_1(String classify) {
		return foodMapper.query_1(classify);
	}

	public Food queryOneByName(String name) {
		return foodMapper.queryOneByName(name);
	}
	
}
