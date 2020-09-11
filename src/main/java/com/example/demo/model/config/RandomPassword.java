package com.example.demo.model.config;

import java.util.Random;

public class RandomPassword {
	
	public static String RandomPasswordMethod() {
		Random dice = new Random();
		String password = Integer.toString(dice.nextInt(10000));
		return password;
	}

}
