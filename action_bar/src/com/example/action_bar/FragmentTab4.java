package com.example.action_bar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.capture_math.action_bar.R;

public class FragmentTab4 extends SherlockFragment {
	ListView list;
	String[] arithmetic;
	int[] pics;
	Intent i;
	ListViewAdapter adapter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final View rootView = inflater.inflate(R.layout.fragmenttab4, container, false);
    	list = (ListView) rootView.findViewById(R.id.listView1);
        arithmetic=new String[]{"Mean","Median","Standard Deviation","Sum of Squares","Geometric Mean","Variance"};
        pics=new int[]{R.drawable.mean,R.drawable.median,R.drawable.stand_dev,R.drawable.sosq,R.drawable.gm,R.drawable.variance};
        adapter = new ListViewAdapter(getActivity(),arithmetic,pics);
       list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(list.getItemAtPosition(arg2).toString().contentEquals("0"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "mean");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("2"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "sd");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("1"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "median");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("3"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "sosq");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("4"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "gm");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("5"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "var");
					startActivity(i);
				}
			}
        	
        });
        rootView.setBackgroundColor(Color.WHITE);
        return rootView;
    }


 
}
