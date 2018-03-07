package com.haima.crm.web.gather;

import com.haima.crm.core.constant.TzConstant;
import com.haima.crm.entity.Content;
import com.haima.crm.entity.User;
import com.haima.crm.service.content.IContentService;
import com.haima.crm.util.TmStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * @Desc: 站点采集
 * @Author: haima
 * @FileName: GatherController.java
 * @PackageName: com.haima.crm.web.gather
 * @Date: 2018-03-02 15:59
 * @E-mail: haimaclan@gmail.com
 */
@Controller
@RequestMapping("/sys/gather")
public class GatherController {

	@Autowired
	private IContentService contentService;
	
	@RequestMapping("list")
	public String list(){
		return "gather/list";
	}
	
	@RequestMapping("to")
	public String to(){
		return "gather/to";
	}

	/**
	 * 下载
	 * @param link
	 * @param destPath
	 * @param fileName
	 */
	public static void download(String link,String destPath,String fileName){
		try {
			URL url = new URL(link);
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			
			FileOutputStream outputStream = new FileOutputStream(new File(destPath,fileName));
			int len;
			int tlen = 0;
			int olen = connection.getContentLength();
			
			byte[] b = new byte[4096];
			DecimalFormat df = new DecimalFormat("#.00");
			
			while((len=inputStream.read(b))!=-1){
				tlen += len;
				System.out.println("下载进度:\t已经下载了--》 "+tlen+",还剩 --》 "+(tlen-len)+"--》 "+(df.format((1.0*tlen/olen)*100))+"%");
				outputStream.write(b, 0, len);
			}
			
			outputStream.close();
			inputStream.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 获取网页源码
	 * @param link
	 * @param charset
	 * @return
	 */
	public static String getHtmlSource(String link,String charset){
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(link);
			URLConnection connection = url.openConnection();
			
			//User-Agent:Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36 360
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36 360");
			
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);
			BufferedReader br = new BufferedReader(inputStreamReader);
			
			String line;
			while((line=br.readLine())!=null){
				sb.append(line+"\n");
			}
			
			br.close();
			inputStreamReader.close();
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return sb.toString();
	}
	
	
	private String who(String link){
		if(TmStringUtils.isNotEmpty(link)){
			if("http://news.sina.com.cn/".equalsIgnoreCase(link))
				return "sina";
			else if("http://news.163.com/".equalsIgnoreCase(link))
				return "163";
			else if("http://news.qq.com/".equalsIgnoreCase(link))
				return "qq";
		}
		return "sina";
	}
	
	
	@ResponseBody
	@RequestMapping(value="data",method=RequestMethod.POST)
	public String gather(@ModelAttribute("news") String news, HttpServletRequest request,HttpServletResponse response) {
		String link = news;
		System.out.println(link+" 《《《《《");
		
		String who = who(link);
		String source = getHtmlSource(link,who.equals("sina")?"utf-8":"gbk");
		//System.out.println(source);
		
		Set<String> set = new HashSet<>();
		
		Document doc = Jsoup.parse(source);
		Elements elements = doc.getElementsByTag("a");
		
		for (Element element : elements) {
			String href = element.attr("href");
			if(!href.contains("http:")){
				href = "http:"+href;
			}
			if(who.equals("qq")){
				if(TmStringUtils.isNotEmpty(href) && (href.startsWith("http://new.qq.com/a/")||href.startsWith("http://new.qq.com/omn/"))){
					set.add(href);
				}
			}else if(who.equals("sina")){
				if(TmStringUtils.isNotEmpty(href) && (href.startsWith("http://news.sina.com.cn/s/")||href.startsWith("http://news.sina.com.cn/o/"))){
					set.add(href);
				}
			}else if(who.equals("163")){
				if(TmStringUtils.isNotEmpty(href) && href.startsWith("http://news.163.com/18/")){
					set.add(href);
				}
			}
			//if(TmStringUtils.isNotEmpty(href) && (href.startsWith("http://news.qq.com/a/")||href.startsWith("https://news.qq.com/a/")||href.startsWith("http://news.qq.com/omn/"))){
			//	set.add(href);
			//}
		}
		
		/** 循环抓取*/
		
		for (String url : set) {
			
			try {
				Document document = Jsoup.connect(url).get();
				// 标题
				String title=document.getElementsByTag("h1").eq(0).text();
				
				if(who.equals("sina")){
					title = document.getElementsByTag("h1").eq(1).text();
				}
				// 内容
				String conId = "article";
				Element contentEle = null;
				if(who.equals("qq")){
					conId="content-article";
					contentEle = document.getElementsByClass(conId).first();
				}else if(who.equals("163")){
					conId="endText";
					contentEle = document.getElementById(conId);
				}else{
					contentEle = document.getElementById(conId);
				}
				
				String desc = contentEle.html();
				// 图片
				String imgClass="img_wrapper";
						
				if(who.equals("163")){
					imgClass="f_center";
				}else if(who.equals("qq")){
					imgClass="one-p";
				}
				Elements poo = contentEle.getElementsByClass(imgClass).eq(0);
				String img = poo.get(0).getElementsByTag("img").attr("src");
				
				//保存内容
				Content content = new Content();
				
				setContentData(content);
				
				content.setTitle(title);
				content.setContent(desc);
				content.setCoverImg(img);
				
				content.setHtmlCode(source);
				
				User u = (User) request.getSession().getAttribute(TzConstant.SESSION_USER);
				if(u!=null){
					content.setUserId(u.getId());
				}else{
					response.sendRedirect(request.getContextPath()+"/login");
					//return returnResult("fail");
				}
				contentService.save(content);
			} catch (Exception e) {
				continue;
			}
		}
		
		return returnResult("success");
	}

	private String returnResult(String r){
		String result = "	<div class='f-index'>"+
				"		<div class='i-f-h'>"+
				"			<h1 id='f-h1'>"+(TmStringUtils.isEmpty(r)?"success":r)+"</h1>"+
				"		</div>"+
				"		<div class=\"i-f-a\">"+
				"			<a id='f-a' href='../index'><i class=\"fa fa-fw fa-hand-o-right\"></i>前去首页</a>"+
				"		</div>"+
				"	</div>"+
				"<script type=\"text/javascript\">"+
				"	var h1= $('#f-h1').text();"+
				"       if(h1==='fail'){"+
				"           loading(\"欸,采集失败了...\",12);"+
				"       }else{"+
				"           loading(\"恭喜,数据已收入库中...\",10);"+
				"       }"+
				"</script>";
		return result;
	}

	private void setContentData(Content content) {
		content.setIsCode(0);
		content.setIsComment(0);
		content.setIsDelete(0);
		content.setIsPush(0);
		content.setIssuance(0);
		content.setIsTop(0);
		
		content.setHits(0);
		content.setLoves(0);
		content.setCollections(0);
		
		content.setType(1);
	}
	
	
	public static void main(String[] args) {
		
		//Date d = new Date();
		//SimpleDateFormat df = new SimpleDateFormat("MMdd");
		//http://mp3-cdn2.luoo.net/low/luoo/radio972/03.mp3
		/*落网
		 * for (int i = 1; i <= 10; i++) {
			String flag = "0"+i;
			if(i==10) flag="10";
			String link = "http://mp3-cdn2.luoo.net/low/luoo/radio971/"+flag+".mp3";
			download(link, "C:/temp/", flag+".mp3");
		}*/
		
		//String link = "http://news.163.com/shehui/";//网易新闻网
		//String link = "http://news.sina.com.cn/society/";//新浪新闻网
		String link = "http://news.qq.com/";//腾讯新闻
		
		Set<String> set = new HashSet<>();
		String source = getHtmlSource(link,"utf-8");
		
		Document doc = Jsoup.parse(source);
		Elements elements = doc.getElementsByTag("a");
		
		for (Element element : elements) {
			String href = element.attr("href");
			if(!href.contains("http:")){
				href = "http:"+href;
			}
			//if(TmStringUtils.isNotEmpty(href) && href.startsWith("http://news.163.com/18/")){
			//if(TmStringUtils.isNotEmpty(href) && (href.startsWith("http://news.sina.com.cn/s/")||href.startsWith("http://news.sina.com.cn/o/"))){
			if(TmStringUtils.isNotEmpty(href) && (href.startsWith("http://new.qq.com/a/")||href.startsWith("http://new.qq.com/omn/"))){
				set.add(href);
				System.out.println(href);
			}
		}
		
		
		/** 循环抓取*/
		for (String url : set) {
			try {
				Document document = Jsoup.connect(url).get();
				/** 标题 */
				String title = document.getElementsByTag("h1").eq(0).text();
				//String title = document.getElementsByTag("h1").eq(1).text();
				/** 内容 */
				//Element contentEle = document.getElementById("endText");//网易
				//Element contentEle = document.getElementById("article");//新浪
				Element contentEle = document.getElementsByClass("content-article").first();//腾讯
				/** 图片 */
				//Elements poo = contentEle.getElementsByClass("f_center").eq(0);//网易
				//Elements poo = contentEle.getElementsByClass("img_wrapper").eq(0);//新浪
				Elements poo = contentEle.getElementsByClass("one-p").eq(0);//腾讯
				String src = poo.get(0).getElementsByTag("img").attr("src");
				System.out.println(src);
				
				System.out.println(title);
				
				
				//System.out.println(df.format(d));
				//System.out.println(desc);
				//System.out.println(img);
			} catch (Exception e) {
				continue;
			}
		}
	}
}
