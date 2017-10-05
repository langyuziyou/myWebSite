package myWebSite.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import myWebSite.admin.service.CategoryService;

public class BaseController {
	
	private static final Logger LOGGER = Logger  
            .getLogger(BaseController.class); 
	//分页默认值
	public static final Integer PAGE_NO = 1;
	public static final Integer PAGE_SIZE = 10;
	
	public static final String SESSION_LOAD_CATEGORY_FIRST = "SESSION_LOAD_CATEGORY_FIRST";
	
	
    @Autowired  
    private CategoryService categoryService;
	
	
    public final static String MD5(String pwd) {  
        //用于加密的字符  
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中  
            byte[] btInput = pwd.getBytes();  
               
            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
               
            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要  
            mdInst.update(btInput);  
               
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文  
            byte[] md = mdInst.digest();  
               
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {   //  i = 0  
                byte byte0 = md[i];  //95  
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5   
                str[k++] = md5String[byte0 & 0xf];   //   F  
            }  
               
            //返回经过加密后的字符串  
            return new String(str);  
               
        } catch (Exception e) {  
            return null;  
        }  
    }
    
    
    
    /**
     * 一级分类信息 
     * @author yzj
     * @version 2.0 2017年9月30日 下午1:52:19
     * 
     * @param request
     * @return
     */
	public List<Map<String, Object>> loadCategoryFirst(HttpServletRequest request) {
		List<Map<String, Object>> list = (List<Map<String, Object>>) request.getSession().getAttribute(SESSION_LOAD_CATEGORY_FIRST);
		if (list == null) {
			list = categoryService.categoryList();
			System.out.println(list);
			request.getSession().setAttribute(SESSION_LOAD_CATEGORY_FIRST, list);
		}
		return list;
	}
	
	public List<Map<String, Object>> loadCategoryByPid(HttpServletRequest request,String pId) {
		List<Map<String, Object>> list  = categoryService.findByPid(pId);
		return list;
	}
	
	/**
	 * json格式输出.
	 *
	 * @param HttpServletResponse
	 * @param json
	 *            object
	 * @author yangwenbin
	 * @date 2016年4月15日 下午3:47:44
	 * @version 1.0.0
	 */
	public void responseJson(HttpServletResponse response, JsonObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
}
