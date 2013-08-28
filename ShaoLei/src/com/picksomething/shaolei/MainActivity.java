package com.picksomething.shaolei;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.Toast;
import com.picksomething.shaolei.CreateBoom;

public class MainActivity extends Activity {
	CreateBoom cb;
	private Chronometer ch;
	private GridLayout grid;
	private int[] rand = new int[10];
	private Button[][] bt= new Button[8][8];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grid = (GridLayout) findViewById(R.id.root);
		ch = (Chronometer)findViewById(R.id.time);
		ch.setOnChronometerTickListener(ct);
		ch.setBase(SystemClock.elapsedRealtime());
		ch.start();
		//ib = (Button)findViewById(R.id.ok);
		for ( int i=0; i < 8; i++) {
			for(int j=0; j < 8; j++){
				Button bn = new Button(this);
				bt[i][j] = bn;
				bn.setOnClickListener(btListener) ;
				//定位每一个按钮的行和列
				GridLayout.Spec rowSpec = GridLayout.spec(i );
				GridLayout.Spec columnSpec = GridLayout.spec(j);
				GridLayout.LayoutParams params = new LayoutParams(rowSpec,columnSpec);
				params.setGravity(Gravity.CENTER|Gravity.TOP);
				grid.addView(bn, params);
			}
		}
		
	}
	
	private OnClickListener btListener = new OnClickListener() {
		
		@Override
		public void onClick(final View v) {
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					if(v == bt[i] [j]){
						AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
						builder.setTitle("提示").setMessage("选择你想执行的动作");
						builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								cb = new CreateBoom();
								rand = cb.getBoom();
								String result = "";
								for(int varial : rand){
									result =+ varial+"";
								}
								System.out.println(result);
							}
						});
						builder.setNegativeButton("标记", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							v.setBackgroundResource(R.drawable.ic_launcher);
						}
						});
						builder.create().show();
					}
				}
			}
		}
	};
		private OnChronometerTickListener ct = new OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer ch) {
				// TODO Auto-generated method stub
				if(SystemClock.elapsedRealtime()-ch.getBase() > 20*1000){
					ch.stop();
				}
			}
		};
		public void doClick(View v){
				ch.stop();
				Toast.makeText(this, "你一共用时"+((SystemClock.elapsedRealtime()-ch.getBase())/1000), Toast.LENGTH_SHORT).show();
		}

}
