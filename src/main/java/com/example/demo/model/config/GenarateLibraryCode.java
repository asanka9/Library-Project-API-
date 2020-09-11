package com.example.demo.model.config;

import java.util.stream.Stream;

public class GenarateLibraryCode {
	
	public static String libraryCodeGenaratorMethod(String nam,int num) {
		String name = nam;
		String tempArray[] = name.split(" ");
		name = tempArray[0];
		tempArray = name.split("");
		StringBuffer sbf = new StringBuffer("");
		Stream.of(tempArray).filter(x->!(x.startsWith("A")||x.startsWith("a")||x.startsWith("E")||x.startsWith("e")||x.startsWith("I")
				||x.startsWith("i")||x.startsWith("O")||x.startsWith("o")||x.startsWith("U")||x.startsWith("u")
				)).map(x->x.toUpperCase()).forEach(x->sbf.append(x));;
		
		String str=sbf.toString();
		return (str + Integer.toString(num));
	}

}
