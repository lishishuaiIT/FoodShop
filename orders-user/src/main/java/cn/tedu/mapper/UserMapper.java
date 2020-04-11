package cn.tedu.mapper;

import java.util.List;

import cn.order.pojo.User;


public interface UserMapper {

	void register(User user);

	List<User> queryOne(String phone);

	User login(User user);

}
