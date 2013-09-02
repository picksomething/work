package com.picksomething.caizi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//private String result;
	private int[] RandomNum = new int[5];
	private int[] UserInputNum = new int[5];
	private int t;
	private int count;
	private EditText as;
	private Button ok,create;
	
	int numOfNum;
	int numOfCount;
	
	ToolMethods tm = new ToolMethods();
	//private TextView number;
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
		Bundle bd = new Bundle();
		Intent intent  = getIntent();
		bd = intent.getExtras();
		numOfNum = bd.getInt("num");
		numOfCount = bd.getInt("count");
		create = (Button)findViewById(R.id.creatnum);
		create.setText("点击生成一个"+numOfNum+"位数");
		create.setTextSize(30);
		for(int i=0; i<10; i++){
			bt[i] = (Button)findViewById(id[i]);
		}
		as = (EditText)findViewById(R.id.answer);
		//number = (TextView)findViewById(R.id.number);
		info = (TextView)findViewById(R.id.info);
		ok = (Button)findViewById(R.id.ok);
	}
	public void doOk(View v){
		int Anum = 0;
		int Bnum = 0;
		if(v == ok){
			if(as.getText().toString().length() != numOfNum)
				Toast.makeText(this, "你确定正确填写"+numOfNum+"位数了吗？", Toast.LENGTH_SHORT).show();
			else{
				count++;
				for(int i=0; i<numOfNum; i++){
					for(int j=0; j<numOfNum; j++){
						if(UserInputNum[j] == RandomNum[i]){
							if(i == j){
								Anum++;
							}else{
								Bnum++;
							}
						}
					}
				}
				if(numOfCount == count){
					if(numOfNum == Anum){
						tm.showDialog(getApplicationContext(),"好险啊，最后一次猜正确了。", 3000, false, true);
						StringBuffer re = new StringBuffer(info.getText());
						re.append('\n'+as.getText().toString()+"------->"+Anum+"A"+Bnum+"B");
						info.setText(re);
						ok.setEnabled(false);
					}
					else{
						AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示")
						.setMessage("很遗憾，你已经用完"+numOfCount+"次机会了，再接再厉！").setIcon(R.drawable.sad)
						.setPositiveButton("确定", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
							}
						});
						builder.create().show();
						ok.setEnabled(false);
					}
				}
				else if(numOfNum == Anum){
					if(count == 1){
						tm.showDialog(getApplicationContext(),"哇，好厉害啊，一次就猜对了。", 3000, false, true);
						StringBuffer re = new StringBuffer(info.getText());
						re.append('\n'+as.getText().toString()+"------->"+Anum+"A"+Bnum+"B");
						info.setText(re);
						ok.setEnabled(false);
					}
					else{
						tm.showDialog(getApplicationContext(),"恭喜你，猜对了！", 3000, true, false);
						StringBuffer re = new StringBuffer(info.getText());
						re.append('\n'+as.getText().toString()+"------->"+Anum+"A"+Bnum+"B");
						info.setText(re);
						ok.setEnabled(false);
					}
				}
				else{
					tm.showDialog(getApplicationContext(),"额，没猜对，继续加油吧！", 3000, false, false);
					StringBuffer re = new StringBuffer(info.getText());
					re.append('\n'+as.getText().toString()+"------->"+Anum+"A"+Bnum+"B");
					info.getPaint().setFakeBoldText(true);
					info.getPaint().setAntiAlias(true);
					info.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
					info.setText(re);
				}
			}
		}
		//必须要重新初始化一下，否则会导致doClick中的数组越界
		t=0;
		for(Button a:bt){
			a.setEnabled(true);
		}
		if(as.getText().length() == numOfNum)
			as.setText("");
	}
	
	public void create(View v){
		RandomNum = tm.getIn(numOfNum);
		if(!(info.getText().toString().equals(""))){
			info.setText("");
		}
		tm.showDialog(getApplicationContext(),"成功生成，可以开始猜数字了", 3000, false, true);
		ok.setEnabled(true);
		count = 0;
	}
	
	public void doClick(View v){
		for(int i=0; i<10; i++){
			if(v == bt[i]){	
				UserInputNum[t++] = Integer.parseInt(bt[i].getText().toString());
				//Log.d("button", bt[i].getText().toString());
				as.setText(as.getText().toString()+bt[i].getText());
				if(as.getText().toString().length() == numOfNum){
					for(Button a:bt){
						a.setEnabled(false);
					}
				}
				break;
			}
		}
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
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("游戏帮助").setMessage(R.string.help)
			.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			builder.create().show();
			break;
		case 2:
			String an = "";
			for(int a:RandomNum){
				an += a;
			}
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this).setTitle("答案");
			if(an.equals("0000")){
				builder2.setMessage("你还没有生成一个"+numOfNum+"位数哦！")
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
				builder2.create().show();
			}
			else{
				builder2.setMessage("答案是："+an+",猜对了吗？")
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