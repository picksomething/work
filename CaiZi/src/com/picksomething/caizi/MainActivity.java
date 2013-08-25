package com.picksomething.caizi;

import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String result;
	private int[] RandomNum = new int[4];
	private int[] UserInputNum = new int[4];
	private int t;
	private int count;
	private EditText as;
	private Button ok;
	private TextView number;
	private TextView info;
	private Button[] bt = new Button[10];
	private int[] id= {R.id.zero,R.id.one,R.id.two,R.id.three,
			R.id.four,R.id.five,R.id.six,
			R.id.seven,R.id.eight,R.id.nine};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		for(int i=0; i<10; i++){
			bt[i] = (Button)findViewById(id[i]);
		}
		as = (EditText)findViewById(R.id.answer);
		number = (TextView)findViewById(R.id.number);
		info = (TextView)findViewById(R.id.info);
		ok = (Button)findViewById(R.id.ok);
		
	}
	public void doOk(View v){
		int Anum = 0;
		int Bnum = 0;
		if(v == ok){
			if(as.getText().toString().length() != 4)
				Toast.makeText(this, "你确定正确填写四位数了吗？", Toast.LENGTH_SHORT).show();

			else{
				count++;
				for(int i=0; i<4; i++){
					for(int j=0; j<4; j++){
						if(UserInputNum[j] == RandomNum[i]){
							if(i == j){
								Anum++;
							}else{
								Bnum++;
							}
						}
					}
				}
				if(8 == count){
					AlertDialog.Builder builder = new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("你已经用完八次机会了，再接再厉！")
					.setPositiveButton("确定", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					});
					builder.create().show();
					ok.setEnabled(false);
				}
				if(4 == Anum){
					Toast.makeText(this, "you win！", Toast.LENGTH_SHORT).show();
					StringBuffer re = new StringBuffer(info.getText());
					re.append('\n'+as.getText().toString()+"---->"+Anum+"A"+Bnum+"B");
					info.setText(re);
					ok.setEnabled(false);
				}
				else{
					Toast.makeText(this, "wrong，please continue！", Toast.LENGTH_SHORT).show();
					StringBuffer re = new StringBuffer(info.getText());
					re.append('\n'+as.getText().toString()+"---->"+Anum+"A"+Bnum+"B");
					info.setText(re);
				}
			}
		}
		//必须要重新初始化一下，否则会导致doClick中的数组越界
		t=0;
		for(Button a:bt){
			a.setEnabled(true);
		}
		if(as.getText().length() == 4)
			as.setText("");
	}
	
	public void create(View v){
		getIn();
		if(!(info.getText().toString().equals(""))){
			info.setText("");
		}
		Toast.makeText(this, "成功生成，可以开始猜数字了。", Toast.LENGTH_SHORT).show();
		ok.setEnabled(true);
	}
	
	public void doClick(View v){
		for(int i=0; i<10; i++){
			if(v == bt[i]){	
				UserInputNum[t++] = Integer.parseInt(bt[i].getText().toString());
				Log.d("button", bt[i].getText().toString());
				as.setText(as.getText().toString()+bt[i].getText());
				if(as.getText().toString().length() == 4){
					for(Button a:bt){
						a.setEnabled(false);
					}
				}
				break;
			}
		}
	}
	
	public void getIn(){
		int j=0;
		result = "";
		String[] a = getNumber();
		do{
			a = getNumber();
		}while(!(repeatOrNot(a)));
		for(String str:a){
			result += str;
			RandomNum[j++] = Integer.parseInt(str);
		}
		number.setText(result.toString());
	}
	
	public String[] getNumber(){
		String[] a = new String[4];
		for(int i=0; i<4; i++){
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(1, 1, 1, "游戏帮助");
		menu.add(1, 2, 2, "查看答案");
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case 1:
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
			.setTitle("游戏帮助")
			.setMessage(R.string.help)
			.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			builder.create().show();
			break;
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
			.setTitle("答案");
			if(number.getText().toString().equals("")){
				builder2.setMessage("你还没有生成一个四位数哦！")
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
				builder2.create().show();
			}
			else{
				builder2.setMessage("答案是："+number.getText().toString()+",猜对了吗？")
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
				builder2.create().show();
			}
		}
		return false;
		
	}

}