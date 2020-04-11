package cn.tedu.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.utils.MD5Util;
import com.jt.common.utils.MapperUtil;

import cn.order.pojo.User;
import cn.tedu.mapper.UserMapper;
import redis.clients.jedis.JedisCluster;

@Service
public class UserService {

	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jedis;
	
	public void register(User user) {
		user.setPassword(MD5Util.md5(user.getPassword()));
		userMapper.register(user);
	}
	public List<User> queryOne(String phone) {
		return userMapper.queryOne(phone);
	}
	public String login(User user) {
		try{
			user.setPassword(MD5Util.md5(user.getPassword()));
			User exist=userMapper.login(user);
			if(exist==null){
				return "";
			}else{
				String oTicket = jedis.get("login_"+user.getPhone());
				if(StringUtils.isNotEmpty(oTicket)){
					jedis.del(oTicket);
				}
				String ticket="order_user"+System.currentTimeMillis()+user.getPhone();
				String userJson = MapperUtil.MP.writeValueAsString(exist);
				jedis.setex(ticket, 60*60*2, userJson);
				jedis.setex("login_"+user.getPhone(), 60*60*2, ticket);
				return ticket;
			}
		}catch (Exception e) {
			return "";
		}
	}
	
	public String queryTicket(String ticket) {
		try{
			long expireTime=jedis.pttl(ticket);
			if(expireTime<1000*60*30){
				Long time=expireTime+1000*60*30;
				jedis.pexpire(ticket, time);
			}
			return jedis.get(ticket);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
