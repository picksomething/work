package com.picksomething.caizi;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ToolMethods {
	
	public int[] getIn(int numOfNum){
		int[] RandomNum = new int[numOfNum];
		int j=0;
		//result = "";
		String[] a = getNumber(numOfNum);
		do{
			a = getNumber(numOfNum);
		}while(!(repeatOrNot(a)));
		for(String str:a){
			//result += str;
			RandomNum[j++] = Integer.parseInt(str);
		}
		return RandomNum;
		//number.setText(result.toString());
	}
	
	public String[] getNumber(int numOfNum){
		String[] a = new String[numOfNum];
		for(int i=0; i<numOfNum; i++){
			int number = (int)(Math.random()*10);
			a[i]=String.valueOf(number);
		}
		return a;
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
	
	public void showDialog(Context context,String message, int milliseconds, boolean useSmileImage, boolean useCoolImage)
	{
		// 提示信息
		Toast dialog = Toast.makeText(context,message,Toast.LENGTH_LONG);
		dialog.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout dialogView = (LinearLayout) dialog.getView();
		ImageView coolImage = new ImageView(context);
		if (useSmileImage)
		{
			coolImage.setImageResource(R.drawable.smile);
		}
		else if (useCoolImage)
		{
			coolImage.setImageResource(R.drawable.cool);
		}
		else
		{
			coolImage.setImageResource(R.drawable.sad);
		}
		dialogView.addView(coolImage, 0);
		dialog.setDuration(milliseconds);
		dialog.show();
	}

}
