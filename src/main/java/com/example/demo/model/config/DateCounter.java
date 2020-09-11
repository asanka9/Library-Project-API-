package com.example.demo.model.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateCounter {
	
	public static int DateCounterMethod(String date) {
		//"2019-08-09
		int days =0;
		ZoneId zone = ZoneId.of("Asia/Colombo");
		LocalDate today = LocalDate.now(zone);
		String currentDate = today.toString();
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String firstDate = date;
		String sefcondDate = currentDate;
		try {
			java.util.Date date01 =  myFormat.parse(firstDate);
			java.util.Date date02 =  myFormat.parse(sefcondDate);
			long dif = date02.getTime()-date01.getTime();
			days = (int) (dif/(1000*60*60*24));
		} catch (Exception e) {
		}
		return days;
	}

}
