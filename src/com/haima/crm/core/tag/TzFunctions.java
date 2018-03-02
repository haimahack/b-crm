package com.haima.crm.core.tag;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.haima.crm.util.TmStringUtils;
import com.haima.crm.util.ip.TmIpUtil;


public class TzFunctions {

	
	/**
	 * 格式化日期
	 * 方法名：formatDate
	 */
	public static String formatDate(Date date,String pattern){
		return TmStringUtils.formatDate(date, pattern);
	}
	
	/**
	 * 获取一个集合的长度
	 * 方法名：getLenght
	 */
	public static int getLength(Collection<?> collection){
		if(collection!=null){
			return collection.size();
		}else{
			return 0;
		}
	}
	
	public static String getTimeFormat(Date startTime){
		try{
			long startTimeMills = startTime.getTime();
			long endTimeMills = System.currentTimeMillis();
			long diff = (endTimeMills - startTimeMills)/1000;//秒
			long day_diff  = (long) Math.floor(diff/86400);//天
			StringBuffer buffer = new StringBuffer();
			if(day_diff<0){
				return "[error],时间越界...";
			}else{
				if(day_diff==0 && diff<60){
					if(diff==0)diff=1;
					buffer.append(diff+"秒前");
				}else if(day_diff==0 && diff<120){
					buffer.append("1 分钟前");
				}else if(day_diff==0 && diff<3600){
					buffer.append(Math.round(Math.floor(diff/60))+"分钟前");
				}else if(day_diff==0 && diff<7200){
					buffer.append("1小时前");
				}else if(day_diff==0 && diff<86400){
					buffer.append(Math.round(Math.floor(diff/3600))+"小时前");
				}else if(day_diff==1){
					buffer.append("1天前");
				}else if(day_diff<7){
					buffer.append(day_diff+"天前");
				}else if(day_diff <30){
					buffer.append(Math.round(Math.ceil( day_diff / 7 )) + " 星期前");
				}else if(day_diff >=30 && day_diff<=179 ){
					buffer.append(Math.round(Math.ceil( day_diff / 30 )) + "月前");
				}else if(day_diff >=180 && day_diff<365){
					buffer.append("半年前");
				}else if(day_diff>=365){
					buffer.append(Math.round(Math.ceil( day_diff /30/12))+"年前");
				}
			}
			return buffer.toString();
		}catch(Exception ex){
			return "";
		}
	}
	
	/**
	 * 根据数字获取索引
	 * 方法名：getCharacter
	 */
	public static String getCharacter(int num){
		return String.valueOf((char)(64+num));
	}
	
	
	/**
	 * 将数字转换成对应的中文
	 * 方法名：intToChnNumConverter<BR>
	 */
	public static String chinesCharacter(int num){
	    String resultNumber = null;
	    if(num > 10000 || num < 0){
	        return "";
	    }
	    HashMap<Integer, String> chnNumbers = new HashMap<Integer, String>();
	    chnNumbers.put(0, "零");
	    chnNumbers.put(1, "一");
	    chnNumbers.put(2, "二");
	    chnNumbers.put(3, "三");
	    chnNumbers.put(4, "四");
	    chnNumbers.put(5, "五");
	    chnNumbers.put(6, "六");
	    chnNumbers.put(7, "七");
	    chnNumbers.put(8, "八");
	    chnNumbers.put(9, "九");
	 
	    HashMap<Integer, String> unitMap = new HashMap<Integer, String>();
	    unitMap.put(1, "");
	    unitMap.put(10, "十");
	    unitMap.put(100, "百");
	    unitMap.put(1000, "千");
	    int[] unitArray = {1000, 100, 10, 1};
	 
	    StringBuilder result = new StringBuilder();
	    int i = 0;
	    while(num > 0){
	        int n1 = num / unitArray[i];
	        if(n1 > 0){
	            result.append(chnNumbers.get(n1)).append(unitMap.get(unitArray[i]));
	        }
	        if(n1 == 0){
	            if(result.lastIndexOf("零") != result.length()-1){
	                result.append("零");
	            }
	        }
	        num = num % unitArray[i++];
	        if(num == 0){
	            break;
	        }
	    }
	    resultNumber = result.toString();
	    if(resultNumber.startsWith("零")){
	        resultNumber = resultNumber.substring(1);
	    }
	    if(resultNumber.startsWith("一十")){
	        resultNumber = resultNumber.substring(1);
	    }
	    return resultNumber;
	}
	
	public static String getMoney(Double money){
		Trans2RMB t2r = new Trans2RMB();
		String s = t2r.cleanZero(t2r.splitNum(t2r.roundString(String.valueOf(money))));
		return s;
	}
	
	/**
	 * indexOf
	 * @param content
	 * @param search
	 * @return
	 */
	public static int indexOf(String content,String search){
		return content.indexOf(search);
	}
	
	/**
	 * 获取IP对应的城市信息
	 * 方法名：ipLocation
	 */
	public static String ipLocation(String ip){
		if(TmStringUtils.isEmpty(ip)){
			return "匿名";
		}
		return TmIpUtil.ipLocation(ip);
	}
	
	
	public static void main(String[] args) {
		//char A 65
		//B 66
		//C 67
		//D 68
//		for(int i=1;i<=26;i++){
//			System.out.println(getCharacter(i));
//		}
		
		System.out.println(getMoney(1275.48787d));
	}
}
