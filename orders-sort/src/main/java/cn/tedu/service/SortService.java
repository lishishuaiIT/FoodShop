package cn.tedu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.common.utils.MapperUtil;

import cn.order.pojo.Cart;
import cn.order.pojo.SortF;
import redis.clients.jedis.JedisCluster;

@Service
public class SortService {

	@Autowired
	private JedisCluster cluster;

	public List<SortF> sort() throws Exception {
		Set<String> set1=cluster.zrange("sort", 0, 10);
		List<SortF> list=new ArrayList<SortF>();
		List<SortF> list2=new ArrayList<SortF>();
		for (String s : set1) {
			SortF sort=MapperUtil.MP.readValue(s, SortF.class);
			list.add(sort);
		}
		for (int i =list.size()-1; i >= 0; i--) {
			list2.add(list.get(i));
		}
		return list2;
	}

	public void add(String userid) {
		String cartUser = "cart_user_" + userid;
		Set<String> set = cluster.smembers(cartUser);
		Set<String> set1=cluster.zrange("sort", 0, -1);
		for (String product : set) {
			String cartJson = cluster.get(product);
			try {
				Cart cart = MapperUtil.MP.readValue(cartJson, Cart.class);
				if(set1.size()!=0){
					for (String s : set1) {
						SortF sort=MapperUtil.MP.readValue(s, SortF.class);
						if(sort.getSname().equals(cart.getFname())){
							sort.setSnum(cart.getNum()+sort.getSnum());
							String sortJson=MapperUtil.MP.writeValueAsString(sort);
							cluster.zincrby("sort", cart.getNum()+0.0, sortJson);
							String cartKey = "cart_" + cart.getUserid() + cart.getFname();
							cluster.del(cartKey);
							break;
						}
						SortF sf=new SortF();
						sf.setSimg(cart.getImg());
						sf.setSname(cart.getFname());
						sf.setSnum(cart.getNum());
						sf.setSprice(cart.getFprice());
						String sortJson=MapperUtil.MP.writeValueAsString(sf);
						cluster.zadd("sort",cart.getNum()+0.0,sortJson);
						String cartKey = "cart_" + cart.getUserid() + cart.getFname();
						cluster.del(cartKey);
						break;
					}
				}else{
					SortF sf=new SortF();
					sf.setSimg(cart.getImg());
					sf.setSname(cart.getFname());
					sf.setSnum(cart.getNum());
					sf.setSprice(cart.getFprice());
					String sortJson=MapperUtil.MP.writeValueAsString(sf);
					cluster.zadd("sort",cart.getNum()+0.0,sortJson);	
					String cartKey = "cart_" + cart.getUserid() + cart.getFname();
					cluster.del(cartKey);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cluster.del(cartUser);
		
	}
	
	

}
