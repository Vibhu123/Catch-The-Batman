package com.example.action_bar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.capture_math.action_bar.R;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class EnterActivity extends SherlockFragmentActivity{
	
	protected EditText et1;
	protected Button solve;
	String classification;
	ActionBar actionBar;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.enter);
    	getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    	actionBar = getSupportActionBar();
    	actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw_green));
        actionBar.setTitle("Enter Your Expression");
    	solve=(Button)findViewById(R.id.button1);
    	classification=this.getIntent().getStringExtra("Classification");
    	et1=(EditText)findViewById(R.id.editText1);
    	solve.setOnClickListener(new ButtonClickHandle());
    	if(classification.contentEquals("numeric"))
	        {
	        	actionBar.setIcon(R.drawable.direct_exp);
	        }
	        if(classification.contentEquals("add/subtract")||classification.contentEquals("multiply_complex")||classification.contentEquals("divide_complex"))
	        {
	        	actionBar.setIcon(R.drawable.complex_num);
	        }
	        if(classification.contentEquals("mean"))
	        {
	        	actionBar.setIcon(R.drawable.mean);
	        }
	        if(classification.contentEquals("trignometric"))
	        {
	        	actionBar.setIcon(R.drawable.trig);
	        }
	        if(classification.contentEquals("log"))
	        {
	        	actionBar.setIcon(R.drawable.log);
	        }
	        if(classification.contentEquals("factorial"))
	        {
	        	actionBar.setIcon(R.drawable.fact_exp);
	        }
	        if(classification.contentEquals("median"))
	        {
	        	actionBar.setIcon(R.drawable.median);
	        }
	        if(classification.contentEquals("var"))
	        {
	        	actionBar.setIcon(R.drawable.variance);
	        }
	        if(classification.contentEquals("sd"))
	        {
	        	actionBar.setIcon(R.drawable.stand_dev);
	        }
	        if(classification.contentEquals("sosq"))
	        {
	        	actionBar.setIcon(R.drawable.sosq);
	        }
	        if(classification.contentEquals("gm"))
	        {
	        	actionBar.setIcon(R.drawable.gm);
	        }
	        if(classification.contentEquals("two_var"))
	        {
	        	actionBar.setIcon(R.drawable.two_var);
	        }
	        if(classification.contentEquals("three_var"))
	        {
	        	actionBar.setIcon(R.drawable.three_var);
	        }
	        if(classification.contentEquals("quad"))
	        {
	        	actionBar.setIcon(R.drawable.quad);
	        }
	        if(classification.contentEquals("cubic"))
	        {
	        	actionBar.setIcon(R.drawable.cube);
	        }
	        if(classification.contentEquals("inverse"))
	        {
	        	actionBar.setIcon(R.drawable.inverse);
	        }
	        if(classification.contentEquals("transpose"))
	        {
	        	actionBar.setIcon(R.drawable.transpose);
	        }
	        if(classification.contentEquals("determinant"))
	        {
	        	actionBar.setIcon(R.drawable.deter);
	        }
	        if(classification.contentEquals("cofactor"))
	        {
	        	actionBar.setIcon(R.drawable.cofactor);
	        }
	        if(classification.contentEquals("chemistry"))
	        {
	        	actionBar.setIcon(R.drawable.chemistry);
	        }
	
	}
	public class ButtonClickHandle implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.v("Corrected_text", et1.getText().toString());
			Intent i=new Intent(EnterActivity.this,ProceedActivity.class);
			i.putExtra("Classification", classification);
			i.putExtra("Corrected_Text", et1.getText().toString());
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
			 final Dialog dialog=new Dialog(EnterActivity.this);
			 dialog.setTitle("About");
			 
			 LinearLayout ll = new LinearLayout(EnterActivity.this);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        ll.setBackgroundColor(Color.WHITE);
	        TextView tv = new TextView(EnterActivity.this);
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