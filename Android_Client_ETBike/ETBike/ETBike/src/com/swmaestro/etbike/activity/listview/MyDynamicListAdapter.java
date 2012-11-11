package com.swmaestro.etbike.activity.listview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.maps.MapView;
import com.swmaestro.etbike.activity.MyProfileActivity;
import com.swmaestro.etbike.activity.R;
import com.swmaestro.etbike.activity.listview.object.MyBikeItem;
import com.swmaestro.object.WorkVectors;

public class MyDynamicListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater li;
	ArrayList<MyBikeItem> al;
	MapView mv;
	boolean mapScrollFlag;
	String exQuery;
	String TAG = "MyDynamicListAdapter";

	public MyDynamicListAdapter(Context context, ArrayList<MyBikeItem> al) {
		this.context = context;
		this.al = al;
		this.li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return al.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public int getItemViewType(int position) {
		int viewType = al.get(position).viewType;
		return viewType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.BaseAdapter#getViewTypeCount() �ٲ����
	 */
	public int getViewTypeCount() {
		return 2;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		String imgPath = al.get(position).bikeImagePath;
		Bitmap imbBM = al.get(position).bitmap;
		String tradeType = al.get(position).tradeType;
		String detail = al.get(position).content;
		int viewType = al.get(position).viewType;
		
		if (convertView == null) {
			Log.e(TAG, "convertview null");

			int res = 0;
			if (viewType == MyProfileActivity.VIEW_TYPE_SEPARATOR) {
				res = R.layout.mdlaseparatoritem;
			} else if (viewType == MyProfileActivity.VIEW_TYPE_MY_BIKE) {
				res = R.layout.mdlamybikeitem;
			}
			convertView = li.inflate(res, parent, false);
		}
		
		if(viewType == MyProfileActivity.VIEW_TYPE_MY_BIKE) {
			
			ImageView iv = (ImageView)convertView.findViewById(R.id.myBikeImgIVMyBikeItem);
			TextView tv = (TextView)convertView.findViewById(R.id.tradeTypeTVMyBikeItem);
			TextView tv1 = (TextView)convertView.findViewById(R.id.detailTVMyBikeItem);
			/*
			 * Bitmap bm = BitmapFactory.decodeFile(imageURL);
				myPicIV.setImageBitmap(bm);
			 */
			Bitmap bm = BitmapFactory.decodeFile(imgPath);
			iv.setImageBitmap(bm);
			
			tv.setText(tradeType);
			tv1.setText(detail);
			
		}else if(viewType == MyProfileActivity.VIEW_TYPE_SEPARATOR) {
			
			TextView tv = (TextView)convertView.findViewById(R.id.separatorTVMDLAview);
			
			tv.setText(tradeType);
			
		}
		
		return convertView;
	}

}