package com.example.action_bar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.capture_math.action_bar.R;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ActionActivity extends SherlockFragmentActivity
{
	protected CheckBox cb1;
	protected CheckBox cb2;
	protected ImageView image;
	protected TextView text;
	protected Button button;
	protected String text1;
	protected String classification;
	String recognizedText;
	ActionBar actionBar;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capt);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		actionBar = getSupportActionBar();
    	actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw_green));
        actionBar.setTitle("Recognized Text");
        image=(ImageView)findViewById(R.id.imageView1);
		text=(TextView)findViewById(R.id.textView1);
		button=(Button)findViewById(R.id.button1);
		String path=this.getIntent().getStringExtra("Path");
        classification=this.getIntent().getStringExtra("Classification");
		BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        text1=this.getIntent().getStringExtra("Recognized_Text");
        image.setImageBitmap(bitmap);
		text.setText(text1);
		text.setVisibility(View.VISIBLE);
		button.setOnClickListener(new ButtonClickHandler());
		if(classification.contentEquals("numeric"))
        {
        	actionBar.setIcon(R.drawable.direct_exp);
        }
		if(classification.contentEquals("chemistry"))
		{
			actionBar.setIcon(R.drawable.chemistry);
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
	}
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
			 final Dialog dialog=new Dialog(ActionActivity.this);
			 dialog.setTitle("About");
			 
			 LinearLayout ll = new LinearLayout(ActionActivity.this);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        ll.setBackgroundColor(Color.WHITE);
	        TextView tv = new TextView(ActionActivity.this);
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
	
	public class ButtonClickHandler implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			final Dialog dialog=new Dialog(ActionActivity.this);
			dialog.setContentView(R.layout.custom_dialog);
			dialog.setTitle("Proceed");
			TextView text=(TextView)dialog.findViewById(R.id.textView1);
			text.setText("Proceed if the text recognized of the image is correct. If not then edit the text");
       	 	Button proceed = (Button) dialog.findViewById(R.id.button1);
       	 	Button edit = (Button) dialog.findViewById(R.id.button2);
	       	proceed.setOnClickListener(new OnClickListener()
	       	 {
	
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 Intent i=new Intent(ActionActivity.this,ProceedActivity.class);
	            	 i.putExtra("Corrected_Text", text1);
	            	 i.putExtra("Classification", classification);
	            	 startActivity(i);
	             
				}
	       		 
	       	 });
	      	 edit.setOnClickListener(new OnClickListener()
	      	 {
	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(ActionActivity.this,EditBoxActivity.class);
					i.putExtra("Corrected_Text", text1);
					i.putExtra("Classification", classification);
	           	 	startActivity(i);
	            	
				}
	      		 
	      	 });
	      	 dialog.show();
		}
	}

       
		    
}
