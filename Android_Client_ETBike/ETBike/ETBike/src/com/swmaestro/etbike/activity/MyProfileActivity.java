package com.swmaestro.etbike.activity;


import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.swemaestro.etbike.dao.DBManager;
import com.swmaestro.etbike.activity.listview.object.MyBikeItem;
import com.swmaestro.etbike.network.NetworkManager;
import com.swmaestro.object.WorkVectors;

public class MyProfileActivity extends TabActivity {
	
	TabHost mTab;
	
	ListView myBikeListLV;
	ListView dealListLV;
	ListView myJoinListLV;
	
	ArrayList<MyBikeItem> myBikeListAL;
	
	Context context;
	
	public static final int VIEW_TYPE_SEPARATOR = 0;
	public static final int VIEW_TYPE_MY_BIKE = 1;
	
	DBManager dbm;
	
	NetworkManager nm;
	
	WorkVectors wv;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		mTab = getTabHost();
		LayoutInflater inflater = LayoutInflater.from(this);
		
		inflater.inflate(R.layout.myprofile, mTab.getTabContentView(), true);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.myprofiletitlebar);
		
		
		mTab.addTab(mTab.newTabSpec("myBikeList").setIndicator("내자전거").setContent(R.id.myBikeListLL));
		mTab.addTab(mTab.newTabSpec("dealList").setIndicator("거래내역").setContent(R.id.dealListLL));
		mTab.addTab(mTab.newTabSpec("myJoinList").setIndicator("같이타요").setContent(R.id.myJoinListLL));
		
		dbm = new DBManager(context);
		wv = new WorkVectors();
		nm = new NetworkManager(wv, mHandler, NetworkManager.COMM_GET_MY_BIKE_LIST_INFOS);
		
		mTab.setOnTabChangedListener(new OnTabChangeListener() {  

			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				nm.start();
				
			}
		});
//		dbm.setALByMyBike(myBikeListAL);
		
//		myBikeListLV.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//				// TODO Auto-generated method stub
//				
//				
//				
//			}
//		});
//		
		context = this;
		
		
		findViewById(R.id.addBikeBtnMyProfile).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(context, RegisterBike.class));
			}
		});
		
		findViewById(R.id.filterBikeBtnMyProfile).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(context, RegisterBike.class));
			}
		});
		
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			
			
		}
	};
	


}
