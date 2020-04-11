package cn.tedu.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.utils.UploadUtil;
import com.jt.common.vo.PicUploadResult;
@Service
public class PicService {

	public PicUploadResult picUpload(MultipartFile pic) {
		PicUploadResult result=new PicUploadResult();
		String oName=pic.getOriginalFilename();
		String extName=oName.substring(oName.lastIndexOf("."));
		Boolean legal=extName.matches(".(png|jpg|gif)");
		if(!legal){
			result.setError(1);
			return result;
		}
		
		String dir=UploadUtil.getUploadPath(oName, "upload")+"/";
		
		String path="D://"+dir;
		String urlPath="http://image.order.com/"+dir;
		
		String fileName=UUID.randomUUID().toString()+extName;
		
		File _dir=new File(path);
		if(!_dir.exists()){
			_dir.mkdirs();
		}
		
		try {
			pic.transferTo(new File(path+fileName));
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);
		}
		
		result.setUrl(urlPath+fileName);
		
		return result;
	}

}
