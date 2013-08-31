package com.picksomething.caizi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class HomeActivity extends Activity{
	Button b1,b2,b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		b1 = (Button)findViewById(R.id.easy);
		b2 = (Button)findViewById(R.id.common);
		b3 = (Button)findViewById(R.id.hard);
	}
	
	public void doClick(View v){
		if(v == b1){
			Bundle bd = new Bundle();
			bd.putInt("num", 3);
			bd.putInt("count", 6);
			Intent intent = new Intent(HomeActivity.this, MainActivity.class);
			intent.putExtras(bd);
			startActivity(intent);
		}
		else if(v == b2){
			Bundle bd = new Bundle();
			bd.putInt("num", 4);
			bd.putInt("count", 8);
			Intent intent = new Intent(HomeActivity.this, MainActivity.class);
			intent.putExtras(bd);
			startActivity(intent);
		}
		else if(v == b3){
			Bundle bd = new Bundle();
			bd.putInt("num", 5);
			bd.putInt("count", 12);
			Intent intent = new Intent(HomeActivity.this, MainActivity.class);
			intent.putExtras(bd);
			startActivity(intent);
		}
	}

}
