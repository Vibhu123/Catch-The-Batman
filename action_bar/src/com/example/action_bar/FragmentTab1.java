package com.example.action_bar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.capture_math.action_bar.R;
 
public class FragmentTab1 extends SherlockFragment {
	ListView list;
	String[] arithmetic;
	int[] pics;
	Intent i;
	ListViewAdapter adapter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);
    	list = (ListView) rootView.findViewById(R.id.listView1);
        arithmetic=new String[]{"Direct Expressions","Factorial Expressions","Complex Numbers","Trignometric Expressions","Logarithmic Expressions"};
        pics=new int[]{R.drawable.direct_exp,R.drawable.fact_exp,R.drawable.complex_num,R.drawable.trig,R.drawable.log};
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
					i.putExtra("Classification", "numeric");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("1"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "factorial");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("2"))
				{
					i=new Intent(getActivity(),AlgebricExp.class);
					i.putExtra("Classification", "complex");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("3"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "trignometric");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("4"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "log");
					startActivity(i);
				}
			}
        	
        });
        rootView.setBackgroundColor(Color.WHITE);
        return rootView;
    }


 
}
