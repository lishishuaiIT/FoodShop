package cn.tedu.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.utils.MapperUtil;

import cn.order.pojo.Food;
import cn.tedu.search.mapper.SearchMapper;
@Service
public class SearchService {

	@Autowired
	private SearchMapper searchMapper;
	@Autowired
	private TransportClient client;
	public void createIndex(String string) throws Exception {
		List<Food> pList=searchMapper.queryAll();
	
		IndicesAdminClient admin=client.admin().indices();
		if(!admin.prepareExists(string).get().isExists()){
			admin.prepareCreate(string).get();			
		}
		for (Food p : pList) {
			String jsonData=MapperUtil.MP.writeValueAsString(p);
			client.prepareIndex(string, "Food",p.getId()+"").setSource(jsonData).get();
		}
	}
	public List<Food> search(String test) {
		MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(test, "name","effect","classify");
		SearchResponse response = client.prepareSearch("emindex").setQuery(query).get();
		SearchHits allhits = response.getHits();
		SearchHit[] hits = allhits.getHits();
		System.out.println("all"+allhits.totalHits);
		List<Food> list=new ArrayList<Food>();
		for (SearchHit hit : hits) {
			String jsonData=hit.getSourceAsString();
			try{
				Food p=MapperUtil.MP.readValue(jsonData, Food.class);
				list.add(p);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
