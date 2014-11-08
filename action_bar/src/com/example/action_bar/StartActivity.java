package com.example.action_bar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.capture_math.action_bar.R;

public class StartActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		handleIntent();
		
	}
	
	private void handleIntent()
	{
		boolean end=getIntent().getBooleanExtra("End", false);
		if(end)
		{
			StartActivity.this.finish();
		}
		else
		{
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		    //Remove notification bar
		    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.activity_start);
			new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {

	                Intent i=new Intent(StartActivity.this,ChoiceActivity.class);
	                startActivity(i);
	            }
	        }, 3000);
		}
	}
	

}
