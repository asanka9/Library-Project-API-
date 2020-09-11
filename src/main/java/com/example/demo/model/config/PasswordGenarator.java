package com.example.demo.model.config;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PasswordGenarator {

	public static String passwordGenaratorMethod(String pass) {
		String password = pass;
		System.out.println("This is password : "+password);
		String passwordArray[] = password.split(" ");
		if (passwordArray.length>1) {
			Date date = new Date();
			int para=0;
			switch (passwordArray[1]) {
			case "HH":
				SimpleDateFormat  HH = new SimpleDateFormat("HH");
				para =Integer.parseInt(HH.format(date));
				break;

			case "mm":
				SimpleDateFormat  mm = new SimpleDateFormat("mm");
				para =Integer.parseInt(mm.format(date));
				break;
			case "m1":
				SimpleDateFormat  m1 = new SimpleDateFormat("mm");
				para =Integer.parseInt((m1.format(date)).substring(0, 1));
				break;
			case "m2":
				SimpleDateFormat  m2 = new SimpleDateFormat("mm");
				para =Integer.parseInt(m2.format(date).substring(1, 2));
				break;
				
			}
			int para02=Integer.parseInt(passwordArray[3]);
			switch (passwordArray[2]) {
			case "*":
				para02 = para*para02;
				break;
			case "+":
				para02 = para+para02;
				break;
			case "-":
				para02 = para-para02;
				break;

			default:
				para02 = para/para02;
				break;
			}
			password = passwordArray[0]+Integer.toString(para02);
			return password;
		} else {
			return pass;
		}
		
	}
}
