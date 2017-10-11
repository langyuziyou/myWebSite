package myWebSite.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import myWebSite.admin.entity.ImageData;
import myWebSite.admin.tools.AjaxJson;
import myWebSite.admin.tools.DateUtil;


@Scope("prototype")
@Controller
@RequestMapping("/upload")
public class FileController extends BaseController{
	
	private static final Logger LOGGER = Logger  
            .getLogger(FileController.class);  
	
	/**
	 * 
	 * method: 单图片上传
	 *
	 * @param
	 * @return
	 * @author yzj
	 * @date 2016年8月18日 上午10:38:07
	 * @exception @version
	 *                1.0.0
	 */
	/**
	 * 
	 * @author yzj
	 * @version 2.0 2017年10月1日 下午3:07:43
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uploadImag", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadImag(@RequestParam MultipartFile file, HttpServletRequest request){
		String enviroment = "http://localhost:8080/myWebSite/shop/";
		String basePath = request.getSession().getServletContext().getRealPath("shop/upload/imgs");
		StringBuffer sb = new StringBuffer();
		
		boolean up = true;
		JSONObject object = new JSONObject();
		String fileName = file.getOriginalFilename();
		try{
			InputStream stream = file.getInputStream();
			String fileAdd = basePath + "/" + DateUtil.getDate();
			
			File file1 = new File(fileAdd);
			if (!file1.exists() && !file1.isDirectory()) {
				file1.mkdir();
			}
			// 扫描文件夹下是否存在相同文件
			File f = new File(fileAdd + "/" + fileName);
			if (f.exists()) {
				up = false;
			}
			
			if (up) 
			{
			FileOutputStream fs = new FileOutputStream(fileAdd + "/" + fileName);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			stream.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ImageData data = new ImageData();
		data.setSrc(enviroment + "upload\\imgs\\"+ DateUtil.getDate()+"\\" +  fileName);
		object.put("code", 0);
		object.put("data", data);
		object.put("src", "upload\\imgs\\"+ DateUtil.getDate()+"\\" +  fileName);
		object.put("title",fileName);
		return object;
	}

}
