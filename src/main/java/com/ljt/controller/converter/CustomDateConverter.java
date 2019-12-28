package com.ljt.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date>{
	
	public Date convert(String s) {
		//实现将日期转成日期类型(格式是yyyy-MM-dd)
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//转成直接返回
			return simpleDateFormat.parse(s);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
