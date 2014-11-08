package com.example.action_bar;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import com.suredigit.inappfeedback.*;
import com.sherlock.navigationdrawer.compat.SherlockActionBarDrawerToggle;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.capture_math.action_bar.R;
import com.example.action_bar.TabsPagerAdapter;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends SherlockFragmentActivity {

	// store the active tab here
		ActionBar.Tab Tab1,Tab2,Tab3,Tab4,Tab5;
		FeedbackDialog feedBack;
		ActionBar actionBar;
		DrawerLayout mDrawerLayout;	
	    ListView mDrawerList;
	    ViewPager mv;
	    SherlockActionBarDrawerToggle mDrawerToggle;
	    MenuListAdapter mMenuAdapter;
	    String[] title;
	    TabsPagerAdapter mAdapter;
	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
		Fragment fragmentTab1 = new FragmentTab1();
		Fragment fragmentTab2 = new FragmentTab2();
		Fragment fragmentTab3 = new FragmentTab3();
		Fragment fragmentTab4 = new FragmentTab4();
	@Override
	  public void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
	        
	        title = new String[] { "Feedback"};
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	    	
	        mDrawerList = (ListView) findViewById(R.id.listview_drawer);
		    mDrawerList.setBackgroundColor(Color.WHITE);
		    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
	            GravityCompat.START);
		    mMenuAdapter = new MenuListAdapter(MainActivity.this, title);
		    mDrawerList.setAdapter(mMenuAdapter);
		    	     
	        mv=(ViewPager)findViewById(R.id.pager);
	        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
	        mv.setAdapter(mAdapter);
	        
    	actionBar = getSupportActionBar();
    	actionBar.setTitle("Capture Math");
    // Capture listview menu item click
    	mTitle = mDrawerTitle = getTitle();
    	mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	    
    	actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new SherlockActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer_light, R.string.drawer_open,
                R.string.drawer_close) {
 
            public void onDrawerClosed(View view) {
                // TODO Auto-generated method stub
            	getSupportActionBar().setTitle("Capture Math");
            }
 
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                // Set the title on the action when drawer open
          	    getSupportActionBar().setTitle("Capture Math");
           }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        
    	actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.draw_green));    
	    actionBar.setIcon(R.drawable.logo);
        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(true);
        
        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(true);
 
        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Set Tab Icon and Titles
        Tab1 = actionBar.newTab().setText("Arithmetic");
        Tab2 = actionBar.newTab().setText("Equations");
        Tab3 = actionBar.newTab().setText("Matrix");
        Tab4 = actionBar.newTab().setText("Statistics");
        		
        // Set Tab Listeners
        Tab1.setTabListener(new TabListener(fragmentTab1,mv));
        Tab2.setTabListener(new TabListener(fragmentTab2,mv));
        Tab3.setTabListener(new TabListener(fragmentTab3,mv));
        Tab4.setTabListener(new TabListener(fragmentTab4,mv));
        
        // Add tabs to actionbar
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
        actionBar.addTab(Tab3);		    
        actionBar.addTab(Tab4);
     /*   for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }*/
        actionBar.setStackedBackgroundDrawable(getResources().getDrawable(R.drawable.draw_orange));
        mv.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
       	 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
            	actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        feedBack=new FeedbackDialog(MainActivity.this,"AF-1E43C37F8CC5-DD");
	    FeedbackSettings fbs=new FeedbackSettings();
	    fbs.setText("Give us some feedback like if there is a bug or you want to suggest any new idea. Or you can ask any question.");
	    fbs.setIdeaLabel("comments");
	    feedBack.setSettings(fbs);
	  }
	  
	 
	    @Override
	    public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		// TODO Auto-generated method stub
	    	if (item.getItemId() == android.R.id.home) {
	    		 
	            if (mDrawerLayout.isDrawerOpen(mDrawerList)) 
	            {
	            	mDrawerLayout.closeDrawer(mDrawerList);
	            } else {
	            	mDrawerLayout.openDrawer(mDrawerList);
	            }
	        }
	 
		return super.onOptionsItemSelected(item);
	}

		// ListView click listener in the navigation drawer
	    private class DrawerItemClickListener implements
	            ListView.OnItemClickListener {
	    	@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
	    		switch(arg2)
	    		{
	    		case 0:
	    			feedBack.show();
	    			break;
	    		/*case 1:
	    			AppRater.showRateDialog(MainActivity.this, null);
	    			break;
	    		*/}
			//    selectItem(arg2);
		        	
			}
	    }
	 
	    
	 
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	    }
	 
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggles
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
	 
	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        getSupportActionBar().setTitle("Capture Math");
	    }
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
		// First Menu Button
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
				 final Dialog dialog=new Dialog(MainActivity.this);
				 dialog.setTitle("About");
				 
				 LinearLayout ll = new LinearLayout(MainActivity.this);
		        ll.setOrientation(LinearLayout.VERTICAL);
		        ll.setBackgroundColor(Color.WHITE);
		        TextView tv = new TextView(MainActivity.this);
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
	    



}
