package cn.tedu.mapper;

import java.util.List;

import cn.order.pojo.Food;

public interface FoodMapper {

	void addFood(Food food);

	List<Food> queryAll();

	void delFood(Integer id);

	Food queryOne(Integer id);

	void update(Food food);

	List<Food> query_1(String classify);

	Food queryOneByName(String name);

}
