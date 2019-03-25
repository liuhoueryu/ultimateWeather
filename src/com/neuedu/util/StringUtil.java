package com.neuedu.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {

	//数据库中日期时间的格式
	private static final String DB_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	//文件改名	将给定文件的主文件名改为yyyyMMddHHmmssSSSxxx的形式，其中xxx是100到999之间的随机数，文件的扩展名不变
	public static String convertFilename(String filename){ 
		
	    //获取扩展名
		String extension = filename.substring(filename.lastIndexOf("."));
		
		//实例化DateFormat对象
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
	
		//格式化当前时间
		String now = df.format(new Date());
		
		//产生三位的随机数
		int number = new Random().nextInt(900) + 100;
		
		return now + number + extension;
		
		//简版的实现
		//String extension = filename.substring(filename.lastIndexOf("."));
		
		//return System.currentTimeMillis() + extension;
		
	}
	
	//将数据库格式(yyyy-MM-dd HH:mm:ss)的日期时间字符串(datetime)转换为指定格式(pattern)的字符串
	public static String convertDatetime(String datetime, String pattern){
		
		DateFormat source = new SimpleDateFormat(DB_PATTERN);
		DateFormat dest = new SimpleDateFormat(pattern);
		
		try {
			//String --> Date
			Date d = source.parse(datetime);
			
			//Date --> String
			return dest.format(d);
			
		} catch (ParseException e) { 
			e.printStackTrace();
			return null;
		}
		
	}
	
	//将数据库格式(yyyy-MM-dd HH:mm:ss)的日期时间(datetime)转换为指定格式(pattern)的字符串
	public static String convertDatetime(Date datetime, String pattern){
		
		DateFormat df = new SimpleDateFormat(pattern);
		
		return df.format(datetime);
	}
	
	//将数据库格式(yyyy-MM-dd HH:mm:ss)的日期时间(datetime)转换为指定格式的字符串  刚刚注册  xxx分钟前注册  xxx小时前注册  MM月dd日HH时mm分注册 
	public static String convertDatetime(Date datetime){
		
		Date now = new Date();

		long diff = (now.getTime() - datetime.getTime()) / 1000;
		
		if(diff<60){
			return "刚刚注册";
		}else if(diff<60*60){
			return diff/60 + "分钟前注册";
		}else if(diff<60*60*12){
			return diff/60/60 + "小时前注册";
		}else{
			return convertDatetime(datetime, " MM月dd日HH时mm分") + "注册";
		}
	}
		
	//返回文件的大小数字的格式化
	public static String convertFilesize(long size){
		
		NumberFormat nf = NumberFormat.getInstance();
		return nf.format(size / 1024);
		
		//字符串
		//数字  除1000
	}
	
	//转换关键字 加红显示
	public static String convertKeyword(String source, String keyword){
		
		if(!"".equals(keyword)){
			return source.replace(keyword, "<font color='red'><b>" + keyword + "</b></font>");
		}else{
			return source;
		}
	}
	
	public static void main(String[] args) {
		
		//String filename = "123.jpg";
		//System.out.println(convertFilename(filename));
		
		//System.out.println(convertDatetime("2018-09-12 14:56:20", "yyyy年MM月dd日 HH时mm分ss秒"));
		
		System.out.println(convertFilesize(123456789) + "KB");
	}
	
}
