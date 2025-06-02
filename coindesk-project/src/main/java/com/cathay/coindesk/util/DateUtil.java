package com.cathay.coindesk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

public class DateUtil {
	
	public static String TIME_ZONE = "Asia/Taipei";
	
	public static String DEFAULT_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * 字串轉成日期
	 * @param dateIn
	 * @return
	 */
	public static Date toDate(String dateIn) {
		return toDate(dateIn, DEFAULT_FORMAT);
	}
	
	/**
	 * 字串轉成日期
	 * @param dateIn
	 * @param format
	 * @return 解析失敗時返回 null 
	 */
	public static Date toDate(String dateIn, String format) {
		if (StringUtils.isEmpty(dateIn)) {
			return null;
		}
		
		if (StringUtils.isEmpty(format)) {
			format = DEFAULT_FORMAT;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
			return sdf.parse(dateIn);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * 日期轉成字串
	 * @param dateIn
	 * @return
	 */
	public static String toString(Date dateIn) {
		return toString(dateIn, DEFAULT_FORMAT);
	}
	
	/**
	 * 日期轉成字串
	 * @param dateIn
	 * @param format
	 * @return 解析失敗時返回 null 
	 */
	public static String toString(Date dateIn, String format) {
		if (StringUtils.isEmpty(dateIn)) {
			return null;
		}
		
		if (StringUtils.isEmpty(format)) {
			format = DEFAULT_FORMAT;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		return sdf.format(dateIn);
	}
	
	/**
	 * 將 ISO 格式的時間字符串轉換為指定格式
	 * @param isoTime ISO 格式的時間字符串,格式為 "2024-09-02T07:07:20+00:00"
	 * @return 格式化後的時間字符串(yyyy/MM/dd HH:mm:ss),解析失敗時返回 null 
	 */
	public static String formatIsoTime(String isoTime) {
		if (StringUtils.isEmpty(isoTime)) {
			return null;
		}
		
		// 格式為 "2024-09-02T07:07:20+00:00"
		// 使用 SimpleDateFormat 解析 ISO 8601 格式
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		isoFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		
		//更新時間（時間格式範例：1990/01/01 00:00:00）。 
		SimpleDateFormat targetFormat = new SimpleDateFormat(DEFAULT_FORMAT);
		targetFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		
		try {
			Date date = isoFormat.parse(isoTime);
			return targetFormat.format(date);
		} catch (ParseException e) {
			return null; // 解析失敗時返回 null 
		}
	}
	
}
