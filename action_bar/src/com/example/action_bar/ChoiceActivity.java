package com.example.action_bar;


import com.capture_math.action_bar.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ChoiceActivity extends Activity
{
	protected Button maths_button;
	protected Button chem_button;


	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.choice);
		
		maths_button=(Button)findViewById(R.id.button1);
	    chem_button = (Button)findViewById(R.id.button2);
		
		
	    maths_button.setOnClickListener(new ButtonClickHandler());
	    chem_button.setOnClickListener(new ButtonClickHandler());
		
	}

	
	
	public class ButtonClickHandler implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int button_id=v.getId();
			
			if (button_id == R.id.button1)//put id using R
			{
				Intent i=new Intent(ChoiceActivity.this,MainActivity.class);
				//put the desired Activity..
				startActivity(i);
			}
			else 
			{
				Intent i=new Intent(ChoiceActivity.this,CaptureActivity.class);
				i.putExtra("Classification", "chemistry");
				startActivity(i);
				
			}
			
		}
	}	
		@Override
		public void onBackPressed() {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(ChoiceActivity.this);
	        builder.setTitle("Exit Capture Math");
	        builder.setMessage("Are you sure you want to exit?");

	        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                	
	                	Intent intent = new Intent(ChoiceActivity.this, StartActivity.class);
	        	        intent.putExtra("End", true);
	        	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	        startActivity(intent);
	        	    }
	            });

	        builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	            }
	        });
	        AlertDialog alert = builder.create();
	        alert.show();

			}

}
