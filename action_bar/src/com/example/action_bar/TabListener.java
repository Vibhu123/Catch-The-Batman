package com.example.action_bar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.capture_math.action_bar.R;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
 
public class TabListener implements ActionBar.TabListener {
	 
    Fragment fragment;
    ViewPager mv;
    public TabListener(Fragment fragment,ViewPager mv) {
        // TODO Auto-generated constructor stub
        this.fragment = fragment;
        this.mv=mv;
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    	mv.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    	//mv.setCurrentItem(tab.getPosition());
    	//ft.remove(fragment);
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    }
    
}