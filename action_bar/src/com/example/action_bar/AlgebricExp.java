package com.example.action_bar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.capture_math.action_bar.R;

import com.example.action_bar.CaptureActivity.ButtonClickHandle;
import com.example.action_bar.CaptureActivity.ButtonClickHandler;

public class AlgebricExp extends SherlockFragmentActivity{
	ActionBar actionBar; 
	Button ads;
	Button multiply;
	Button divide;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.complex);
    	getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    	actionBar = getSupportActionBar();
        ads=(Button)findViewById(R.id.button1);
        multiply=(Button)findViewById(R.id.button2);
        divide=(Button)findViewById(R.id.button3);
    	
        actionBar.setTitle("Complex Numbers");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw_green));
        actionBar.setIcon(R.drawable.complex_num);
        ads.setOnClickListener(new ButtonClickHandle());
        multiply.setOnClickListener(new ButtonClickHandler());
        divide.setOnClickListener(new ButtonClickHandlers());
	 }
	public class ButtonClickHandle implements View.OnClickListener {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AlgebricExp.this,CaptureActivity.class);
				i.putExtra("Classification", "add/subtract");
				startActivity(i);
			}
     	
     }
	 
     public class ButtonClickHandler implements View.OnClickListener {
             public void onClick(View view) {
            		Intent i=new Intent(AlgebricExp.this,CaptureActivity.class);
    				i.putExtra("Classification", "multiply_complex");
    				startActivity(i);
    		}
     }
     public class ButtonClickHandlers implements View.OnClickListener {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AlgebricExp.this,CaptureActivity.class);
				i.putExtra("Classification", "divide_complex");
				startActivity(i);
			}
  	
  }
             
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 menu.add("Share")
        .setOnMenuItemClickListener(this.ShareButtonClickListener)
        .setIcon(R.drawable.ic_action_share) // Set the menu icon
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	
		 menu.add("About")
		 .setOnMenuItemClickListener(this.AboutButtonClickListener)
		 .setIcon(R.drawable.ic_action_about) // Set the menu icon
		 .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		 return super.onCreateOptionsMenu(menu);
	}
	OnMenuItemClickListener ShareButtonClickListener = new OnMenuItemClickListener() {
	 	@Override
		public boolean onMenuItemClick(
				com.actionbarsherlock.view.MenuItem item) {
			// TODO Auto-generated method stub
			// Toast.makeText(MainActivity.this, "Share Button", Toast.LENGTH_SHORT)
            // .show();
	 		Intent shareIntent = new Intent(Intent.ACTION_SEND);
	 		  shareIntent.setType("text/plain");
	 		 shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.capture_math.action_bar");
	 		  	 		  startActivity(Intent.createChooser(shareIntent, "Share Capture Math"));
	 		return false;
		}
    };

    OnMenuItemClickListener AboutButtonClickListener = new OnMenuItemClickListener() {
		 
    	@Override
		public boolean onMenuItemClick(
				com.actionbarsherlock.view.MenuItem item) {
			// TODO Auto-generated method stub
			 final Dialog dialog=new Dialog(AlgebricExp.this);
			 dialog.setTitle("About");
			 
			 LinearLayout ll = new LinearLayout(AlgebricExp.this);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        ll.setBackgroundColor(Color.WHITE);
	        TextView tv = new TextView(AlgebricExp.this);
	        tv.setText("Developer: The Soft Heights\nCapture Math is based on OCR technology. It recognizes the text" +
			 		" of the expressions or equations of the images captured. This app supports the basic functionalities of matrix, equations," +
			 		" trignometry, logarithms and more. If you like this app then please give us your time to rate it. Or if you wish to give us any suggestions do provide" +
			 		" us feedback. We will try our best to improve your experience in any way we can.");
	        	        tv.setTextColor(Color.BLACK);
	        tv.setWidth(300);
	        tv.setPadding(4, 0, 4, 10);
	        ll.addView(tv);
	        dialog.setContentView(ll);        
	        dialog.show();        
	        return true;
		}
    };

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

	    case android.R.id.home:
	         // Do whatever you want, e.g. finish()
	    	finish();
	         break;

	    default:
	        return super.onOptionsItemSelected(item);
	    }
		return super.onOptionsItemSelected(item);
	}

}
