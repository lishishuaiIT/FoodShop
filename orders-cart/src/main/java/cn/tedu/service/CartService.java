package cn.tedu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jt.common.utils.MapperUtil;

import cn.order.pojo.Cart;
import cn.tedu.mapper.CartMapper;
import redis.clients.jedis.JedisCluster;

@Service
public class CartService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private JedisCluster cluster;
	
	public void addCart(Cart cart) {
		String cartUser = "cart_user_" + cart.getUserid();
		String cartKey = "cart_" + cart.getUserid() + cart.getFname();
		if (cluster.sismember(cartUser, cartKey)) {
			String cartJson = cluster.get(cartKey);
			try {
				Cart exist = MapperUtil.MP.readValue(cartJson, Cart.class);
				exist.setNum(exist.getNum() + cart.getNum());
				cluster.set(cartKey, MapperUtil.MP.writeValueAsString(exist));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String cartJson;
			try {
				cartJson = MapperUtil.MP.writeValueAsString(cart);
				cluster.sadd(cartUser, cartKey);
				cluster.set(cartKey, cartJson);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<Cart> queryCart(String userId) {
		
		String cartUser = "cart_user_" + userId;
		if (cluster.exists(cartUser)) {
			Set<String> set = cluster.smembers(cartUser);
			List<Cart> cartList = new ArrayList<Cart>();
			for (String product : set) {
				String cartJson = cluster.get(product);
				try {
					Cart cart = MapperUtil.MP.readValue(cartJson, Cart.class);
					cartList.add(cart);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return cartList;
		} else {
			return null;
		}
	}

	public void update(String userid, String fname,Integer num) {
		String cartKey = "cart_" + userid + fname;
		String cartJson = cluster.get(cartKey);
		try {
			Cart exist = MapperUtil.MP.readValue(cartJson, Cart.class);
			exist.setNum(num);
			cluster.set(cartKey, MapperUtil.MP.writeValueAsString(exist));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(String userid, String fname) {
		String cartKey = "cart_" + userid + fname;
		String cartUser = "cart_user_" + userid;
		cluster.del(cartKey);
		if (cluster.smembers(cartUser)==null) {
			cluster.del(cartUser);
		}
	}

}
