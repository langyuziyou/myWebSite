package myWebSite.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import myWebSite.admin.entity.Shop;
import myWebSite.admin.service.CategoryService;
import myWebSite.admin.service.ShopService;

public class BaseController {
	
	
	@Autowired
	private ShopService shopService;
	
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
			List<Map<String, Object>> list = categoryService.categoryList();
			System.out.println(list);
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
	
	
	/**
	 * 测试的主函数 
	 * @author yzj
	 * @version 2.0 2017年10月25日 上午9:33:34
	 * 
	 * @param args
	 */
	public static void main(String args[]){

		Random random = new Random();
		
		ExecutorService executor = Executors.newScheduledThreadPool(10);
		
		//
		int waitTime = 500;
		for(int i=0;i<1000;i++){
			String name = "thread" + i;
			int time = random.nextInt(1);
			waitTime +=time;
			
			Runnable runner = new ExecutorThread(name,1);
		
			executor.execute(runner);
		}
		
		try {
			//Thread.sleep(waitTime);
			executor.shutdown();
			executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
			System.out.println(" end ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

	
	
	/**
	 * 获取翻页数据 
	 * @author yzj
	 * @version 2.0 2017年10月25日 下午2:15:02
	 *
	 */
	public void getUrl(String url,String fromWeb){
		
		 long start = System.currentTimeMillis();  
		    Document doc=null;  
		    try{  
		        doc = Jsoup.connect(url).get();  
		       
		    }  
		    catch(Exception e){  
		        e.printStackTrace();  
		    }  
		    finally{  
		        System.out.println("Time is:"+(System.currentTimeMillis()-start) + "ms");  
		    } 
		    Elements classs = doc.getElementsByClass("index");
		    List<Shop> shopList = new ArrayList<>();
		    for(Element c:classs){
		    	Shop shop = new Shop();
		    	shop.setFromType(Integer.parseInt(fromWeb));
		    	shop.setShopInfoName(c.getElementsByClass("cy-cp-name").text());
		    	shop.setShopInfoImage(c.getElementsByTag("img").attr("src"));
		    	shop.setFirstShopCategoryId(1);
		    	String dtailUrl = "http://www.cnlipin.cn"+c.getElementsByTag("a").attr("href");
		    	 try {
					Document detailDoc=Jsoup.connect(dtailUrl).get();
					Elements detailPriceClasss = detailDoc.getElementsByClass("article-author");
				    for(Element d:detailPriceClasss){
				  /*  	System.out.println("price1: " + d.getElementsByClass("f12").next().text());
				    	System.out.println("price: " + getPrice(d.getElementsByClass("f12").next().text()));*/
				    	shop.setPrice(getPrice(d.getElementsByClass("f12").next().text())+"");
				    }
					
				    String detail = detailDoc.getElementsByClass("article-img-box").html();
				    String content = detailDoc.getElementById("article_content").html();
				    shop.setDescription(detail+content);
				}catch (Exception e) {
					try {
						shop.setPrice(getPrice(c.getElementsByTag("li").first().text())+"");
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		    	 shopList.add(shop);
		    }

			
			Map<String, Object> paraList = new HashMap<String, Object>();
			paraList.put("shopList",shopList);
			paraList.put("user", "admin");
			int[] strList = shopService.shopListAdd(paraList);
			//System.out.println(sb);
		    
		    //System.out.println(classs.html());  
		    
/*		    Element id = doc.getElementById("article_content");
		    System.out.println(id.html());  */
		
	}
	
	/**
	 * 网页中的字符串 提取出 价格 
	 * @author yzj
	 * @version 2.0 2017年10月26日 下午2:37:38
	 * 
	 * @param str
	 */
	public static BigDecimal getPrice(String line) throws Exception{
		
		String price = "1";
		// 按指定模式在字符串查找
	      String pattern = "(\\D*)(\\d*)(.*)";
	 
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	    	 price =  m.group(2).trim();
	         //System.out.println("Found value: " + m.group(2) );
	      }
	      BigDecimal bd = new BigDecimal(price);
		return bd;
		
	}
}

class ExecutorThread implements Runnable{
	private final String name;
	private final int delay;
	public ExecutorThread(String name,int delay){
		this.name = name;
		this.delay = delay;
	}
	public void run() {
		for(int i=1;i<100911;i++)
		{
			//String url = "http://www.vstob.com/home/index";  
			String url = "http://www.cnlipin.cn/";
		    long start = System.currentTimeMillis();  
		    Document doc=null;  
		    try{  
		        doc = Jsoup.connect(url).get();  
		        System.out.println(Thread.currentThread().getName()+ " end  ");
		       
		    }  
		    catch(Exception e){  
		        e.printStackTrace();  
		    }  
		    finally{  
		        System.out.println("Time is:"+(System.currentTimeMillis()-start) + "ms");  
		    }  
		    
		  
		    Elements elem = doc.getElementsByTag("Title");  
		   /* System.out.println("Title is:" +elem.text());  
		    
		    
		    Elements classs = doc.getElementsByClass("article-img-box");
		    
		    System.out.println(classs.html());  
		    
		    Element id = doc.getElementById("article_content");
		    System.out.println(id.html());*/
		}
	}
	
}
