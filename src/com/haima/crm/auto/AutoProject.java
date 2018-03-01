package com.haima.crm.auto;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.haima.crm.util.TmFileUtil;
import com.haima.crm.util.TmStringUtils;


/**
 * 自动生成项目（包，类，注释...）
 * bean,dao,service,web
 *@author: haima
 *@fileName: AutoProject.java
 *@packageName: com.haima.crm.auto
 *@date: 2018年1月17日 下午12:47:13
 */
public class AutoProject {

	/** beanName 首字母大写*/
	private static String beanName = "Channel";
	private static String smallBeanName = beanName.toLowerCase();
	
	/**目录结构*/
	private static String srcPath="src/";
	private static String beanPath = "com/haima/crm/entity/";
	private static String daoPath ="com/haima/crm/dao/";
	private static String servicePath = "com/haima/crm/service/";
	private static String serviceImplPath = "com/haima/crm/service/"+smallBeanName+"/impl/";
	private static String webPath = "com/haima/crm/web/";
	private static String pagePath = "/WebRoot/WEB-INF/pages/";
	
	
	//=================================================================//
	
	/**bean模板目录*/
	private static String beanTemplate = "template/bean.txt";
	/**dao模板目录*/
	private static String daoTemplate = "template/mapper.txt";
	/**service模板目录*/
	private static String serviceTemplate = "template/service.txt";
	/**service impl模板目录*/
	private static String serviceImplTemplate = "template/serviceimpl.txt";
	/**web模板目录*/
	private static String webTemplate = "template/web.txt";
	/**xml模板目录*/
	private static String xmlTemplate = "template/xml.txt";
	/**page list模板所在目录*/
	private static String pagelistTemplate = "template/pagelist.txt";
	/**page modularity模块模板目录*/
	private static String pagemodularityTemplate="template/pagemodularity.txt";
	
	//=================================================================//
	
	/**bean包名*/
	private static String beanPackage="com.haima.crm.entity";
	/**dao包*/
	private static String daoPackage="com.haima.crm.dao";
	/**service包*/
	private static String servicePackage="com.haima.crm.service";
	/**web包*/
	private static String webPackage = "com.haima.crm.web";
	/** util包*/
	private static String utilPackage = "com.haima.crm.util";
	
	//=================================================================//
	/**
	 * 模板注释
	 * */
	/** 模板注释中的内容*/
	private static String description="栏目采集";
	/**模板注释中的作者*/
	private static String author = "haima";
	/**模板注释中的邮箱*/
	private static String email = "haimaclan@gmail.com";
	/**模板注释中的文件名*/
	private static String fileName = beanName+".java";
	/**模板注释中的日期*/
	private static String date = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss").format(new Date());
	
	
	/**控制台输入对象*/
	private static final Scanner scanner = new Scanner(System.in);
	
	
	/**
	 * 创建page modularity目录结构
	 * @throws IOException 
	 */
	public static void createPageModularity() throws IOException{
		/*pagemodularityPath*/
		String rootPath=getRoot(pagePath+smallBeanName);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();

		/*template.jsp*/
		File beanFile = new File(rootFile,"template.jsp");
		//System.out.println(beanFile);
		
		/*page modularity模板*/
		String template = getRoot(pagemodularityTemplate);

		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		//System.out.println(content);

		/*判断文件是否存在，提示是否覆盖操作*/
		if (beanFile.exists()) {
			
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	}
	
	
	/**
	 * 创建pages list目录结构
	 * @throws IOException 
	 */
	public static void createPageList() throws IOException{
		String rootPath=getRoot(pagePath+smallBeanName);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,"list.jsp");
		//System.out.println(beanFile);
		
		//pagelist模板
		String template = getRoot(pagelistTemplate);

		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		//System.out.println(content);

		/*/判断文件是否存在，提示是否覆盖操作*/
		if (beanFile.exists()) {
			
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	}
	
	
	/**
	 * 创建web目录结构
	 * @throws IOException 
	 */
	public static void createWeb() throws IOException{
		/*/webPath*/
		String rootPath=getRoot(srcPath+webPath+smallBeanName);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,beanName+"Controller.java");
		//System.out.println(beanFile);
		//web模板
		String template = getRoot(webTemplate);

		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		//System.out.println(content);

		/*判断文件是否存在，提示是否覆盖操作*/
		if (beanFile.exists()) {
			
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	}
	
	
	/**
	 * 创建service impl目录结构
	 * @throws IOException 
	 */
	public static void createServiceImpl() throws IOException{
		/*serviceImplPath*/
		String rootPath=getRoot(srcPath+serviceImplPath);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,beanName+"ServiceImpl.java");
		//System.out.println(beanFile);
		//serviceimpl模板
		String template = getRoot(serviceImplTemplate);

		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		//System.out.println(content);

		/*判断文件是否存在，提示是否覆盖操作*/
		if (beanFile.exists()) {
			
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	}
	
	
	/**
	 * 创建service目录结构
	 * @throws IOException 
	 */
	public static void createService() throws IOException{
		/*servicePath*/
		String rootPath=getRoot(srcPath+servicePath+smallBeanName);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,"I"+beanName+"Service.java");
		//System.out.println(beanFile);
		//service模板
		String template = getRoot(serviceTemplate);
		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		
		//判断文件是否存在，提示是否覆盖操作
		if (beanFile.exists()) {
			
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	
	}
	
	
	/**
	 * 创建mapper.xml
	 * @throws IOException 
	 */
	public static void createMapperXML() throws IOException{
		/*daoPath*/
		String rootPath=getRoot(srcPath+daoPath+smallBeanName);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,beanName+".xml");
		//xml模板
		String template = getRoot(xmlTemplate);
		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		
		//判断文件是否存在，提示是否覆盖操作
		if (beanFile.exists()) {
			
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	
	}
	
	
	/**
	 * 创建dao目录结构
	 * @throws IOException 
	 */
	public static void createDao() throws IOException{
		/*daoPath*/
		String rootPath=getRoot(srcPath+daoPath+smallBeanName);
		
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,"I"+beanName+"Mapper.java");
		//dao模板
		String template = getRoot(daoTemplate);
		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		
		//判断文件是否存在，提示是否覆盖操作
		if (beanFile.exists()) {
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
	
	}
	
	
	/**
	 * 创建bean结构目录
	 * @throws IOException 
	 */
	public static void createBean() throws IOException{
		/*beanPath*/
		String rootPath=getRoot(srcPath+beanPath);
		File rootFile = new File(rootPath);
		
		if(!rootFile.exists())
			rootFile.mkdirs();
		
		File beanFile = new File(rootFile,beanName+".java");
		//bean模板
		String template = getRoot(beanTemplate);
		
		//读取模板内容
		String content = TmFileUtil.readFileByLines(template);
		//替换模板内容
		content=replaceTemplate(content);
		
		//判断文件是否存在，提示是否覆盖操作
		if (beanFile.exists()) {
			System.out.println("【提示】【"+beanFile+"】已经存在，是否覆盖(yes/no)!\n");
			//获取控制台输入的值
			String mark = scanner.nextLine();
			if(mark.equalsIgnoreCase("yes")||mark.equalsIgnoreCase("y")){
				//将模板内容写入到文件中
				FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
			}
		}else{
			System.out.println("【提示】创建【"+beanFile+"】文件成功!!!");
			//将模板内容写入到文件中
			FileUtils.writeStringToFile(beanFile.getAbsoluteFile(), content, "UTF-8");
		}
		
	}
	
	/**
	 * 将模板中的特定内容替换
	 * @param content
	 * @return
	 */
	public static String replaceTemplate(String content){
		/**
		 *包名: [beanPackage]
		 *注释内容 [description]
		 *作者: [author]
		 *文件名: [fileName]
		 *日期: [date]
		 *类名:[beanName]
		 *小写类名[smallBeanName]
		 */
		if(TmStringUtils.isNotEmpty(content)){
			content=content.replaceAll("\\[beanPackage]", beanPackage)
					.replaceAll("\\[daoPackage]", daoPackage)
					.replaceAll("\\[servicePackage]", servicePackage)
					.replaceAll("\\[webPackage]", webPackage)
					.replaceAll("\\[utilPackage]", utilPackage)
					.replaceAll("\\[description]", description)
					.replaceAll("\\[author]", author)
					.replaceAll("\\[email]", email)
					.replaceAll("\\[fileName]", fileName)
					.replaceAll("\\[date]", date)
					.replaceAll("\\[beanName]", beanName)
					.replaceAll("\\[smallBeanName]", smallBeanName);
			
			return content;
		}else{
			return "";
		}
	}
	
	
	/**
	 * 获取项目中的根目录
	 * @param path
	 * @return
	 */
	public static String getRoot(String path){
		return new File(System.getProperty("user.dir"),path).getAbsolutePath();
	}
	
	public static void main(String[] args) {
		try {
			//createBean();
			createDao();
			createMapperXML();
			createService();
			createServiceImpl();
			createWeb();
			createPageList();
			createPageModularity();
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(getRoot(""));
		//当前系统用户主目录
		//System.out.println(System.getProperty("user.home"));
		/*Properties properties = System.getProperties();
		Enumeration<Object> keys = properties.keys();
		
		while(keys.hasMoreElements()){
			Object obj = keys.nextElement();
			//获取项目中的信息（包括系统的基本信息）
			System.out.println(obj+"====>>>"+properties.get(obj));
		}*/
	}
}
