package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

import cn.tedu.service.PicService;

@RestController
@RequestMapping("/pic")
public class PicController {
	
	@Autowired
	private PicService picService;

	@RequestMapping("/upload")
	public PicUploadResult picUpload(MultipartFile pic){
		return picService.picUpload(pic);
	}
}
