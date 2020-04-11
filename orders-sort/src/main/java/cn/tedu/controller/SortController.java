package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.order.pojo.SortF;
import cn.order.pojo.SysResult;
import cn.tedu.service.SortService;

@RestController
@RequestMapping("/sort")
public class SortController {
	
	@Autowired
	private SortService sortService;
	
	@RequestMapping("/query")
	public List<SortF> query() throws Exception{
		return sortService.sort();
	}
	
	@RequestMapping("/add")
	public SysResult add(String userid){
		try{
			sortService.add(userid);
			return SysResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
}
