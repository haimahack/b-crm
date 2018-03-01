package com.haima.crm.auto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 根据表和实体模板（sql.txt）自动生成实体对象
 *@author: haima
 *@fileName: AutoSqlToBean.java
 *@date: 2018年1月31日 下午10:21:42
 */
public class AutoSqlToBean {

	public static void main(String[] args) {
		/**
		 * 属性名 属性说明 java.version Java 运行时环境版本 java.home Java 安装目录 os.name
		 * 操作系统的名称 os.version 操作系统的版本 user.name 用户的账户名称 user.home 用户的主目录
		 * user.dir 用户的当前工作目录
		 * 
		 * System.out.println(System.getProperty("java.version"));
		 * System.out.println(System.getProperty("java.home"));
		 * System.out.println(System.getProperty("os.name"));
		 * System.out.println(System.getProperty("os.version"));
		 * System.out.println(System.getProperty("user.name"));
		 * System.out.println(System.getProperty("user.home"));
		 * System.out.println(System.getProperty("user.dir"));
		 */
		
		//String str = "hello_world";
		//int i = str.indexOf("_", 0);
		//str = str.substring(0,i)+str.substring(i+1,i+2).toUpperCase()+str.substring(i+2);
		//System.out.println(str);
		
		writeToFile("Content");
	}

       
	/**
	 * 替换自定义字符
	 * 
	 * @param text
	 * @return
	 */
	private static String replaceText(Map<Integer,List<String>> map,String text) {

		if (text != null && text.length() > 0) {

			/** 存放的是MySQL字段 */
			List<String> fields = map.get(1);
			/** 存放的是MySQL数据类型 */
			List<String> types = map.get(2);
			
			/** 当前类名 */
			String name = Thread.currentThread().getStackTrace()[1].getClassName();
			/** 当前包名 */
			name = name.substring(0, name.lastIndexOf("."));

			/** 包名 */
			String packagePath = name;
			/** 导包 */
			String impackage = "";
			/** 私有成员 */
			String declaration = "";
			/** 构造方法形参 */
			String parameter = "";
			/** 构造方法语句 */
			String statement = "";
			/** get set 方法 */
			String function ="";
			
			
			if (fields != null && fields.size() > 0 && types!=null && types.size()>0) {
				boolean flag = false;
				if (fields.size() == 1 && types.size() == 1) {
					String ts = fields.get(0);
					String ps = types.get(0);
					/** 转成首字母大写  */
					String TS = ts.substring(0,1).toUpperCase()+ts.substring(1);
					switch (ps.toUpperCase()) {
						case "VARCHAR" :case "TINYTEXT":case "TEXT":case "MEDIUMTEXT":case "LONGTEXT":
							if(flag==true){
								declaration += "\tprivate String " + ts + ";\n";
								parameter += "String " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic String get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(String "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}else{
								declaration += "private String " + ts + ";\n";
								parameter += "String " + ts+",";
								statement += "this." + ts + " = " + ts + ";\n";
								function += "public String get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(String "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}
							break;
						case "TINYINT":case "SMALLINT":case "MEDIUMINT":case "INT":
							if(flag==true){
								declaration += "\tprivate Integer " + ts + ";\n";
								parameter += "Integer " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic Integer get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(Integer "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}else{
								declaration += "private Integer " + ts + ";\n";
								parameter += "Integer " + ts+",";
								statement += "this." + ts + " = " + ts + ";\n";
								function += "public Integer get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(Integer "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}
							break;
						case "BIGINT":
							if(flag==true){
								declaration += "\tprivate Long " + ts + ";\n";
								parameter += "Long " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic Long get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(Long "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}else{
								declaration += "private Long " + ts + ";\n";
								parameter += "Long " + ts+",";
								statement += "this." + ts + " = " + ts + ";\n";
								function += "public Long get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(Long "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}
							break;
						case "DATETIME" :case "TIMESTAMP" :case "DATE" :case "TIME" :case "YEAR" :
							declaration += "\tprivate Date " + ts + ";\n";
							parameter += "Date " + ts+",";
							statement += "\t\tthis." + ts + " = " + ts + ";\n";
							function += "\tpublic Date get"+TS+"() {\n"
									+"\t\treturn "+ts+";\n"+"\t}\n"
									+"\tpublic void set"+TS+"(Date "+ts+") {\n"
									+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							break;
						case "CHAR" :
							declaration += "\tprivate Char " + ts + ";\n";
							parameter += "Char " + ts+",";
							statement += "\t\tthis." + ts + " = " + ts + ";\n";
							function += "\tpublic Char get"+TS+"() {\n"
									+"\t\treturn "+ts+";\n"+"\t}\n"
									+"\tpublic void set"+TS+"(Char "+ts+") {\n"
									+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							break;
						case "FLOAT":
							if(flag==true){
								declaration += "\tprivate float " + ts + ";\n";
								parameter += "float " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic float get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(float "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}else{
								declaration += "private float " + ts + ";\n";
								parameter += "float " + ts+",";
								statement += "this." + ts + " = " + ts + ";\n";
								function += "public float get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(float "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}
							break;
						case "DOUBLE":
							if(flag==true){
								declaration += "\tprivate double " + ts + ";\n";
								parameter += "double " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic double get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(double "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}else{
								declaration += "private double " + ts + ";\n";
								parameter += "double " + ts+",";
								statement += "this." + ts + " = " + ts + ";\n";
								function += "public double get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(double "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
							}
							break;
						default :
							break;
					}
					flag=true;
				} else {
					String ts="";
					String TS="";
					String fi = format(fields);
					String[] sArr = fi.split(",");
					
					String pi = format(types);
					String[] pArr = pi.split(",");
					
					for (int j = 0; j < sArr.length; j++) {
						ts = sArr[j];
						int i = ts.indexOf("_", 0);
						if(i!=-1)
							ts = ts.substring(0,i)+ts.substring(i+1,i+2).toUpperCase()+ts.substring(i+2);
						/** 转成首字母大写  */
						TS =ts.substring(0,1).toUpperCase()+ts.substring(1);
						//System.out.println(ts);
						
						/**
						 * TINYINT，SMALLINT，MEDIUMINT，INT 和 BIGINT 
						 * FLOAT、DOUBLE 和 DECIMAL
						 * CHAR VARCHAR TINYBLOB TINYTEXT BLOB TEXT MEDIUMBLOB MEDIUMTEXT LOGNGBLOB LONGTEXT
						 * DATE TIME YEAR DATETIME TIMESTAM
						 */
						switch (pArr[j].toUpperCase()) {
							case "VARCHAR" :case "TINYTEXT":case "TEXT":case "MEDIUMTEXT":case "LONGTEXT":
								if(j>0){
									declaration += "\tprivate String " + ts + ";\n";
									parameter += "String " + ts+",";
									statement += "\t\tthis." + ts + " = " + ts + ";\n";
									function += "\tpublic String get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(String "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}else{
									declaration += "private String " + ts + ";\n";
									parameter += "String " + ts+",";
									statement += "this." + ts + " = " + ts + ";\n";
									function += "public String get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(String "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}
								break;
							case "TINYINT":case "SMALLINT":case "MEDIUMINT":case "INT":
								if(j>0){
									declaration += "\tprivate Integer " + ts + ";\n";
									parameter += "Integer " + ts+",";
									statement += "\t\tthis." + ts + " = " + ts + ";\n";
									function += "\tpublic Integer get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(Integer "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}else{
									declaration += "private Integer " + ts + ";\n";
									parameter += "Integer " + ts+",";
									statement += "this." + ts + " = " + ts + ";\n";
									function += "public Integer get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(Integer "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}
								break;
							case "BIGINT":
								if(j>0){
									declaration += "\tprivate Long " + ts + ";\n";
									parameter += "Long " + ts+",";
									statement += "\t\tthis." + ts + " = " + ts + ";\n";
									function += "\tpublic Long get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(Long "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}else{
									declaration += "private Long " + ts + ";\n";
									parameter += "Long " + ts+",";
									statement += "this." + ts + " = " + ts + ";\n";
									function += "public Long get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(Long "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}
								break;
							case "DATETIME" :case "TIMESTAMP" :case "DATE" :case "TIME" :case "YEAR" :
								declaration += "\tprivate Date " + ts + ";\n";
								parameter += "Date " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic Date get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(Date "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								break;
							case "CHAR" :
								declaration += "\tprivate Char " + ts + ";\n";
								parameter += "Char " + ts+",";
								statement += "\t\tthis." + ts + " = " + ts + ";\n";
								function += "\tpublic Char get"+TS+"() {\n"
										+"\t\treturn "+ts+";\n"+"\t}\n"
										+"\tpublic void set"+TS+"(Char "+ts+") {\n"
										+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								break;
							case "FLOAT":
								if(j>0){
									declaration += "\tprivate float " + ts + ";\n";
									parameter += "float " + ts+",";
									statement += "\t\tthis." + ts + " = " + ts + ";\n";
									function += "\tpublic float get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(float "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}else{
									declaration += "private float " + ts + ";\n";
									parameter += "float " + ts+",";
									statement += "this." + ts + " = " + ts + ";\n";
									function += "public float get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(float "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}
								break;
							case "DOUBLE":
								if(j>0){
									declaration += "\tprivate double " + ts + ";\n";
									parameter += "double " + ts+",";
									statement += "\t\tthis." + ts + " = " + ts + ";\n";
									function += "\tpublic double get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(double "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}else{
									declaration += "private double " + ts + ";\n";
									parameter += "double " + ts+",";
									statement += "this." + ts + " = " + ts + ";\n";
									function += "public double get"+TS+"() {\n"
											+"\t\treturn "+ts+";\n"+"\t}\n"
											+"\tpublic void set"+TS+"(double "+ts+") {\n"
											+"\t\tthis."+ts+" = "+ts+";\n"+"\t}\n\n";
								}
								break;
							default :
								break;
						}
					}
				}

			} else {
				System.out.println("类型或数据库有误!");
			}

			if (declaration.contains("private Date"))
				impackage += "import java.util.Date;";

			parameter= parameter.substring(0, parameter.lastIndexOf(","));
			
			text=text.replaceAll("\\[packagePath]", packagePath)
					.replaceAll("\\[impackage]", impackage)
					.replaceAll("\\[declaration]", declaration)
					.replaceAll("\\[parameter]", parameter)
					.replaceAll("\\[statement]", statement)
					.replaceAll("\\[function]", function);

		} else {
			System.out.println("没有符合要求文本.");
		}
		
		
		return text;
	}

	/**
	 * 根据数据库表中的字段和数据类型生成java实体文件
	 * 
	 * @param map
	 */
	private static void writeToFile(String beanName) {
		
		Map<Integer,List<String>> map = getColumns();
		
		String srcPath = "/src/com/haima/crm/auto/";
		String userDir = System.getProperty("user.dir");

		File file = new File(userDir + srcPath);

		if (file.exists() == false) {
			file.mkdirs();
		}

		try {
			String beanPath = file.getPath() +"/"+ beanName + ".java";
			BufferedReader br = new BufferedReader(
					new FileReader(userDir+"/template/sql.txt"));
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(beanPath)));
			
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line+"\n");
			}
			
			line = sb.toString();
			line = replaceText(map, line).replaceAll("\\[beanName]", beanName);
			//System.out.println(line);
			bw.write(line);

			bw.close();
			br.close();
		} catch (NullPointerException e) {
			System.out.println("空指针异常!》》》"
					+ Thread.currentThread().getStackTrace()[1].getClassName()
					+ "》》》" + Thread.currentThread().getStackTrace()[1]
							.getMethodName());
		} catch (FileNotFoundException e) {
			System.out.println("文件找不到异常!》》》"
					+ Thread.currentThread().getStackTrace()[1].getClassName()
					+ "》》》" + Thread.currentThread().getStackTrace()[1]
							.getMethodName());
		} catch (IOException e) {
			System.out.println("IO异常!》》》"
					+ Thread.currentThread().getStackTrace()[1].getClassName()
					+ "》》》" + Thread.currentThread().getStackTrace()[1]
							.getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取表中字段与其数据类型
	 * 
	 * @return
	 */
	private static Map<Integer,List<String>> getColumns() {
		/** 查询表中的列数’ */
		// String sql1="SELECT COUNT(1) FROM information_schema.COLUMNS WHERE
		// table_name='keke_sns'";
		/** 查询表中的字段名 或者用 ‘desc 表名’ */
		// String sql2 = "SELECT column_name FROM information_schema.columns
		// WHERE table_name='keke_sns'";
		/** 查询表中的数据类型 */
		// String sql3="SELECT data_type FROM information_schema.columns WHERE
		// table_name='keke_sns'";

		Map<Integer,List<String>> map = new HashMap<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载数据库驱动成功");
			String url = "jdbc:mysql://127.0.0.1:3306/moon?useUnicode=true&characterEncoding=utf-8";
			String user = "haima";// 数据库账号
			String pwd = "haima-";// 数据库密码
			// 建立数据库连接，获得连接对象conn
			Connection conn = DriverManager.getConnection(url, user, pwd);

			System.out.println("数据库连接成功");
			String[] sql = new String[]{
				"SELECT column_name FROM information_schema.columns WHERE table_name='h_content'",
				"SELECT data_type FROM information_schema.columns WHERE table_name='h_content'"
			};

			Statement stmt = conn.createStatement();// 创建一个Statement对象
			ResultSet rs = null;// 执行查询，把查询结果赋值给结果集对象

			/** 存储表中字段名 */
			List<String> field = new LinkedList<>();
			/** 存储表中数据类型 */
			List<String> type = new LinkedList<>();

			rs = stmt.executeQuery(sql[0]);
			while (rs.next()) {
				field.add(rs.getString(1));
			}

			// prints(columns);

			rs = stmt.executeQuery(sql[1]);
			while (rs.next()) {
				type.add(rs.getString(1));
			}

			// int,text,varchar,timestamp,varchar,varchar,int
			// prints(types);

			System.out.println();

			conn.close();
			System.out.println("关闭数据库连接对象");

			map.put(1,field);
			map.put(2, type);
			/**
			 * 1.在类的实例中可使用this.getClass().getName();但在static method中不能使用该方法；
			 * 2.在static
			 * method中使用方法:Thread.currentThread().getStackTrace()[1].getClassName();
			 * 获取方法名：Thread.currentThread().getStackTrace()[1].getMethodName();
			 * 获取代码行号：Thread.currentThread().getStackTrace()[1].getLineNumber();
			 */
		} catch (NullPointerException e) {
			System.out.println("空指针异常!》》》"
					+ Thread.currentThread().getStackTrace()[1].getClassName()
					+ "》》》" + Thread.currentThread().getStackTrace()[1]
							.getMethodName());
		} catch (ClassNotFoundException e) {
			System.out.println("类没有找到异常!》》》"
					+ Thread.currentThread().getStackTrace()[1].getClassName()
					+ "》》》" + Thread.currentThread().getStackTrace()[1]
							.getMethodName());
		} catch (SQLException e) {
			System.out.println("SQL异常!》》》"
					+ Thread.currentThread().getStackTrace()[1].getClassName()
					+ "》》》" + Thread.currentThread().getStackTrace()[1]
							.getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	/**
	 * 格式化
	 * @param list
	 */
	private static String format(List<String> list) {
		String s="";
		for (int i = 0; i < list.size(); i++) {
			if (i == 0)
				s+=list.get(i);
			else
				s+="," + list.get(i);
		}
		return s;
	}
}
