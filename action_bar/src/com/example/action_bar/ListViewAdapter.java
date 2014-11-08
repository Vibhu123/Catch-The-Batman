package com.example.action_bar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.capture_math.action_bar.R;
 
public class ListViewAdapter extends BaseAdapter {
	 
    // Declare Variables
    Context context;
    String[] rank;
    int[] flag;
    LayoutInflater inflater;
 
    public ListViewAdapter(Context context, String[] rank,int[] flag) {
        this.context = context;
        this.rank = rank;
        this.flag=flag;
    }
 
    public int getCount() {
        return rank.length;
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return 0;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
 
        // Declare Variables
    	ImageView imgflag;
        TextView txtrank;
 
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflater.inflate(R.layout.listviewitem, parent, false);
 
        // Locate the TextViews in listview_item.xml
        txtrank = (TextView) itemView.findViewById(R.id.rank);
        imgflag= (ImageView)itemView.findViewById(R.id.flag);
 
        // Capture position and set to the TextViews
        txtrank.setText(rank[position]);
        // Capture position and set to the ImageView
        imgflag.setImageResource(flag[position]);
        return itemView;
    }
}
