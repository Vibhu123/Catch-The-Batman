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
 
public class FragmentTab3 extends SherlockFragment {
	ListView list;
	String[] arithmetic;
	ListViewAdapter adapter;
	int[] pics;
	Intent i;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final View rootView = inflater.inflate(R.layout.fragmenttab3, container, false);
    	list = (ListView) rootView.findViewById(R.id.listView1);
        arithmetic=new String[]{"Inverse","Determinant","Cofactor","Transpose"};
        pics=new int[]{R.drawable.inverse,R.drawable.deter,R.drawable.cofactor,R.drawable.transpose};
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
					i.putExtra("Classification", "inverse");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("1"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "determinant");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("2"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "cofactor");
					startActivity(i);
				}
				if(list.getItemAtPosition(arg2).toString().contentEquals("3"))
				{
					i=new Intent(getActivity(),CaptureActivity.class);
					i.putExtra("Classification", "transpose");
					startActivity(i);
				}
			}
        	
        });
        rootView.setBackgroundColor(Color.WHITE);
        return rootView;
    }

	
  
}