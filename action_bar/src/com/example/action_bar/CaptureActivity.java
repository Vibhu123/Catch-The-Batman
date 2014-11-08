package com.example.action_bar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.capture_math.action_bar.R;
import com.googlecode.tesseract.android.TessBaseAPI;


public class CaptureActivity extends SherlockFragmentActivity {
    

	ActionBar actionBar;
    public static final String DATA_PATH = Environment
                    .getExternalStorageDirectory().toString() + "/CaptureMath/";
    
    public static final String lang = "eng";

    private static final String TAG = "Imagetext.java";

    protected Button _button;
    String recognizedText;
	protected Button _enter;
    protected ImageView im;
    protected String _path;
    protected boolean _taken;
    TextView text_disp;
    protected String text;
    protected static final String PHOTO_TAKEN = "photo_taken";
    LoadTask l=new LoadTask(this);
    Void params;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.capture);
    	getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    	actionBar = getSupportActionBar();
 		text=this.getIntent().getStringExtra("Classification");
 		text_disp=(TextView)findViewById(R.id.textView1);
 		Log.v("Action Bar",""+actionBar);
 		actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw_green));
        if(text.contentEquals("numeric"))
        {
        	actionBar.setTitle("Direct Exp");
        	actionBar.setIcon(R.drawable.direct_exp);
        	text_disp.setText("* Valid for Expressions containing: - + * / ^ % \n" +
        			"* For multiplication format is like (a+b)*(c-d).\n " +
        			"* For exponents format is  exp(num).\n* For square root its sqrt(num).\n* For cube root it is cbrt(num)\n");
        
        	
        
        }
        if(text.contentEquals("chemistry"))
        {
        	actionBar.setTitle("Balance Equations");
        	actionBar.setIcon(R.drawable.chemistry);
        	text_disp.setText("Capture or Enter the equations you want to balance like:\n"+
        				"A+B-->C. You can replace --> with = or ->");
        }
        if(text.contentEquals("add/subtract")||text.contentEquals("multiply_complex")||text.contentEquals("divide_complex"))
        {
        	actionBar.setTitle("Complex Numbers");
        	actionBar.setIcon(R.drawable.complex_num);
        	if(text.contentEquals("add/subtract"))
        		text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +
        				"* Format to enter is like a+bi+c-di. If a,b,c,d are 0 ,replace it with 0\n");
        	if(text.contentEquals("multiply_complex"))
        		text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +
        					"* Format to enter is like(a+bi)(c-di). If a,b,c,d are 0 , replace it with 0");
        	if(text.contentEquals("divide_complex"))
        		text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +
        				"* Format is like a+bi/c-di\n.* If a,b,c,d are 0 then do replace it with 0");	
        }
        if(text.contentEquals("mean"))
        {
        	actionBar.setTitle("Mean");
        	actionBar.setIcon(R.drawable.mean);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Enter the set of values as shown below:\n" +
            		"1 2 3 4 5 (space separated)\n1,2,3,4,5 (comma separated)");
        }
        if(text.contentEquals("trignometric"))
        {
        	actionBar.setTitle("Trignometric Exp");
        	actionBar.setIcon(R.drawable.trig);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" + "* Format to enter is like-\n"+
            		"sin30^2+cos60^3*2. "+"\n* Do not use parenthesis.\n* Supported functions -sin,cos,tan,sec,cosec,cot");
        }
        if(text.contentEquals("log"))
        {
        	actionBar.setTitle("Logarithmic Exp");
        	actionBar.setIcon(R.drawable.log);
        	text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +
        			"* Format is like (log3+log5^2)*7.\n* Here log is the natural log.\n* To use log to some other base use loga/logb.\n* Use * in between two" +
        			" pairs of parenthesis");
        }
        if(text.contentEquals("factorial"))
        {
        	actionBar.setTitle("Factorial");
        	actionBar.setIcon(R.drawable.fact_exp);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Format is like: " +
            		"7!-6!+5!.\n* Here do not use parenthesis");
        }
        if(text.contentEquals("median"))
        {
        	actionBar.setTitle("Median");
        	actionBar.setIcon(R.drawable.median);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Enter the set of values as shown below:\n" +
            		"1 2 3 4 5 (space separated)\n1,2,3,4,5 (comma separated)");
            
        }
        if(text.contentEquals("var"))
        {
        	actionBar.setTitle("Variance");
        	actionBar.setIcon(R.drawable.variance);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Enter the set of values as shown below:\n" +
            		"1 2 3 4 5 (space separated)\n1,2,3,4,5 (comma separated)");
            
        }
        if(text.contentEquals("sd"))
        {
        	actionBar.setTitle("Standard Deviation");
        	actionBar.setIcon(R.drawable.stand_dev);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Enter the set of values as shown below:\n" +
            		"1 2 3 4 5 (space separated)\n1,2,3,4,5 (comma separated)");
            
        }
        if(text.contentEquals("sosq"))
        {
        	actionBar.setTitle("Sum of Squares");
        	actionBar.setIcon(R.drawable.sosq);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Enter the set of values as shown below:\n" +
            		"1 2 3 4 5 (space separated)\n1,2,3,4,5 (comma separated)");
            
        }
        if(text.contentEquals("gm"))
        {
        	actionBar.setTitle("Geometric Mean");
        	actionBar.setIcon(R.drawable.gm);
            text_disp.setText("IMPORTANT INSTRUCTIONS:\n" +"* Enter the set of values as shown below:\n" +
            		"1 2 3 4 5 (space separated)\n1,2,3,4,5 (comma separated)");
            
        }
        if(text.contentEquals("two_var"))
        {
        	actionBar.setTitle("Lin Eqns 2 Vars");
        	actionBar.setIcon(R.drawable.two_var);
            text_disp.setText("* Capture or Enter the equations as shown below:\n" +
            		"x+y=5\n3x-5y=25");
        }
        if(text.contentEquals("three_var"))
        {
        	actionBar.setTitle("Lin Eqns 3 Vars");
        	actionBar.setIcon(R.drawable.three_var);
            text_disp.setText("* Capture or Enter the equations as shown below:\n" +
            		"x+y+z=25\n5x-3y=32\nx-z=11");
                
        }
        if(text.contentEquals("quad"))
        {
        	setContentView(R.layout.equns);
        	actionBar.setTitle("Quad Eqns");
        	actionBar.setIcon(R.drawable.quad);
        }
        if(text.contentEquals("cubic"))
        {
        	setContentView(R.layout.equns);
        	ImageView im=(ImageView)findViewById(R.id.imageView1);
        	im.setImageResource(R.drawable.cube_eq);
    		actionBar.setTitle("Cubic Eqns");
        	actionBar.setIcon(R.drawable.cube);
        }
        if(text.contentEquals("inverse"))
        {
        	actionBar.setTitle("Inverse");
        	actionBar.setIcon(R.drawable.inverse);
            text_disp.setText("* Capture or Enter the square matrix of the form:\n" +
            		"1 2 3\n4 5 6\n7 8 9");
        }
        if(text.contentEquals("transpose"))
        {
        	actionBar.setTitle("Transpose");
        	actionBar.setIcon(R.drawable.transpose);
            text_disp.setText("* Capture or Enter the square matrix of the form:\n" +
            		"1 2 3\n4 5 6\n7 8 9");
            
        }
        if(text.contentEquals("determinant"))
        {
        	actionBar.setTitle("Determinant");
        	actionBar.setIcon(R.drawable.deter);
            text_disp.setText("* Capture or Enter the square matrix of the form:\n" +
            		"1 2 3\n4 5 6\n7 8 9");
            
        }
        if(text.contentEquals("cofactor"))
        {
        	actionBar.setTitle("Cofactor");
        	actionBar.setIcon(R.drawable.cofactor);
            text_disp.setText("* Capture or Enter the square matrix of the form:\n" +
            		"1 2 3\n4 5 6\n7 8 9");
            
        }
        
        String[] paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };

            for (String path : paths) {
                    File dir = new File(path);
                    if (!dir.exists()) {
                            if (!dir.mkdirs()) {
                                    Log.v(TAG, "ERROR: Creation of directory " + path + " on sdcard failed");
                                    return;
                            } else {
                                    Log.v(TAG, "Created directory " + path + " on sdcard");
                            }
                    }

            }
            
            if (!(new File(DATA_PATH + "tessdata/" + lang + ".traineddata")).exists()) {
                    try {

                            AssetManager assetManager = getAssets();
                            InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
                            OutputStream out = new FileOutputStream(DATA_PATH
                                            + "tessdata/" + lang + ".traineddata");

                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = in.read(buf)) > 0) {
                                    out.write(buf, 0, len);
                            }
                            in.close();
                            out.close();
                            
                            Log.v(TAG, "Copied " + lang + " traineddata");
                    } catch (IOException e) {
                            Log.e(TAG, "Was unable to copy " + lang + " traineddata " + e.toString());
                    }
            }

            
            _button = (Button) findViewById(R.id.button1);
            _enter= (Button)findViewById(R.id.button2);
            _enter.setOnClickListener(new ButtonClickHandle());
            _button.setOnClickListener(new ButtonClickHandler());
            
            _path = DATA_PATH + "/ocr.jpg";
    }
    public class ButtonClickHandle implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.v("Here","here");
			Intent i=new Intent(CaptureActivity.this,EnterActivity.class);
			i.putExtra("Classification", text);
			startActivity(i);
		}
    	
    }
    public class ButtonClickHandler implements View.OnClickListener {
        public void onClick(View view) {
                Log.v(TAG, "Starting Camera app");
                startCameraActivity();
        }
    }
    @Override
	public ActionBar getSupportActionBar() {
		// TODO Auto-generated method stub
		return super.getSupportActionBar();
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
    
    

    protected void startCameraActivity() {
    		Log.v("CameraAct","Ca");
        	File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, 0);
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
			 final Dialog dialog=new Dialog(CaptureActivity.this);
			 dialog.setTitle("About");
			 
			 LinearLayout ll = new LinearLayout(CaptureActivity.this);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        ll.setBackgroundColor(Color.WHITE);
	        TextView tv = new TextView(CaptureActivity.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            Log.i(TAG, "resultCode: " + resultCode);

            if (resultCode == -1) {
            	Log.v("Here","Capture");
                new LoadTask(CaptureActivity.this).execute(params);
            } else {
                    Log.v(TAG, "User cancelled");
            }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
            outState.putBoolean(CaptureActivity.PHOTO_TAKEN, _taken);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
            Log.i(TAG, "onRestoreInstanceState()");
            if (savedInstanceState.getBoolean(CaptureActivity.PHOTO_TAKEN)) {
            	new LoadTask(CaptureActivity.this).execute(params);
            }
    }
    OnCancelListener onc=new OnCancelListener()
    {

		@Override
		public void onCancel(DialogInterface arg0) {
			// TODO Auto-generated method stub
			android.os.Process.killProcess(android.os.Process.myPid());
		}
    	
    };
    private class LoadTask extends AsyncTask<Void,Void,Void>
    {
    	ProgressDialog dialog;
    	Context context;
		
    	public LoadTask(Context context)
    	{
    		this.context=context;
    	}
    	
    	protected void onPreExecute()
    	{
    		dialog=new ProgressDialog(context);
    		dialog.show(context, "Loading....", "Editing photo please wait",true,
                    true,
                    new DialogInterface.OnCancelListener(){
                        @Override
                        public void onCancel(DialogInterface dialog) {
                        	LoadTask.this.cancel(true);
                        	dialog.dismiss();
                        }
                    }
            );
    		     
    	}
    	
    	@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
    		onPhotoTaken();
			return null;
		}
    	
    	protected void onPostExecute()
    	{
    		dialog.dismiss();
    	}
    }
    protected void onPhotoTaken() {
            _taken = true;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;

            Bitmap bitmap = BitmapFactory.decodeFile(_path, options);

            try {
                    ExifInterface exif = new ExifInterface(_path);
                    int exifOrientation = exif.getAttributeInt(
                                    ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_NORMAL);

                    Log.v(TAG, "Orient: " + exifOrientation);

                    int rotate = 0;

                    switch (exifOrientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                            rotate = 90;
                            break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                            rotate = 180;
                            break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                            rotate = 270;
                            break;
                    }

                    Log.v(TAG, "Rotation: " + rotate);

                    if (rotate != 0) {

                            // Getting width & height of the given image.
                            int w = bitmap.getWidth();
                            int h = bitmap.getHeight();

                            // Setting pre rotate
                            Matrix mtx = new Matrix();
                            mtx.preRotate(rotate);

                            // Rotating Bitmap
                            bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
                    }

                    bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

            } catch (IOException e) {
                    Log.e(TAG, "Couldn't correct orientation: " + e.toString());
            }

            Log.v(TAG, "Before baseApi");

            TessBaseAPI baseApi = new TessBaseAPI();
            baseApi.setDebug(true);
            baseApi.init(DATA_PATH, lang);
            baseApi.setImage(bitmap);
            
            recognizedText = baseApi.getUTF8Text();
            correctText();
            baseApi.end();
            
            Log.v(TAG, "OCRED TEXT: " + recognizedText);
            Intent i=new Intent(CaptureActivity.this,ActionActivity.class);
            i.putExtra("Path",_path);
            i.putExtra("Recognized_Text", recognizedText);
            Log.v("Classification",text);
            i.putExtra("Classification", text);
            startActivity(i);
    }
    private void correctText()
	{
    	recognizedText=recognizedText.replaceAll("(?m)^[ \t]*\r?\n", "");
    	if(text.contentEquals("inverse")||text.contentEquals("transpose")||text.contentEquals("determinant")
    			||text.contentEquals("cofactor"))
    	{
    		recognizedText=recognizedText.replaceAll("[^0-9\\n ]", "");
    	}
    	else if(text.contentEquals("numeric"))
    	{
    		recognizedText=recognizedText.replaceAll("[^0-9-+=()^*/\\n]","");
       	}
    	else if(text.contentEquals("factorial"))
    	{
    		recognizedText=recognizedText.replaceAll("[^0-9!-+/\\n]","");
       	}
    	else if(text.contentEquals("chemistry"))
    	{
    		recognizedText=recognizedText.replaceAll("[^A-Za-z0-9+^=()\\n]","");
       	 	
    	}
    	else if(text.contentEquals("mean")||text.contentEquals("median")||text.contentEquals("sd")||
    			text.contentEquals("sosq")||text.contentEquals("gm")||text.contentEquals("var"))
    	{
    		recognizedText=recognizedText.replaceAll("[^0-9,\\n ]","");
       	}
    	else
    	{
    		if(text.contentEquals("log"))
    		{
    			recognizedText=recognizedText.replaceAll("10g","log");
    			recognizedText=recognizedText.replaceAll("1og", "log");
    			recognizedText=recognizedText.replaceAll("Iog", "log");
    		}
    		recognizedText=recognizedText.replaceAll("[^A-Za-z0-9-+^=()*/\\n]","");
       	 	recognizedText=recognizedText.replaceAll("l|I", "1");
       	 	recognizedText=recognizedText.replaceAll("o|O", "0");
       	 	recognizedText=recognizedText.replaceAll("[==]+","=");
       	 	recognizedText=recognizedText.replaceAll(":", "=");
       	 	recognizedText=recognizedText.replaceAll("[ _,]", "");
       	}
    }
}      
