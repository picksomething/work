package com.picksomething.shaolei;

import java.util.HashSet;
import java.util.Set;

public class CreateBoom {
	private boolean[] flag = new boolean[64];
	private int[] randomNum = new int[10] ;
	
	public int[] getBoom(){
		int j=0;
		String[] temp = randomNumber();
		do{
			temp = randomNumber();
		}while(!(repeatOrNot(temp)));
		for(String str:temp){
			randomNum[j++] = Integer.parseInt(str);
		}
		return randomNum;
	}
	
	public String[] randomNumber(){
		String[] boom = new String[10];
		for(int i=0; i<10; i++){
			int number = (int)(Math.random()*64);
			boom[i]=String.valueOf(number);
		}
		return boom;
	}
	
	public boolean repeatOrNot(String[] array){
		Set<String> set = new HashSet<String>();
		for(String str:array){
			set.add(str);
		}
		if(set.size() != array.length)
			return false;
		else
			return true;
	}
}
