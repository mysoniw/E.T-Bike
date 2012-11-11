package com.swmaestro.etbike.utils.location;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GPSProvider {
	Criteria criteria;
	Location gpsLoc;
	String mProvider;
	LocationManager mLocMan;
	LocationListener mLocListener;
	
	public GPSProvider(LocationManager mLocMan) {
		this.mLocMan = mLocMan; //GPS ���� �޾ƿ��� ���� locationmanager Ŭ������ ������Ʈ�� �ݵ��?�ʿ�
		mLocListener = new LocationListener(){ // ��ġ�� ��õ�?����̽���?�پ��� Event�� �� �����ʸ� �������־��?�Ѵ�.

			public void onLocationChanged(Location loc) {  // �������?��ġ�� ���Ҷ����� �׸� �����س��� �޼ҵ�
				loc.getLatitude(); 
				loc.getLongitude();
			} 
			public void onProviderDisabled(String provider){}
			public void onProviderEnabled(String provider) {}
			public void onStatusChanged(String provider, int status, Bundle extras){}
		};
		
		criteria = new Criteria();
		mProvider = mLocMan.getBestProvider(criteria, true);
		gpsLoc = mLocMan.getLastKnownLocation(mProvider);
	}
	
	public double getDLatitude(){
		mProvider = mLocMan.getBestProvider(criteria, true); // ������ ��ġ ���� �����ڸ� ã�Ƴ���
		mLocMan.requestLocationUpdates(mProvider, 0, 0, mLocListener); // ���� �����ڸ� ���� ��ġ ������Ʈ�� �� ����
		gpsLoc = mLocMan.getLastKnownLocation(mProvider);  // ���� ��ġ ������ �ľ��س���
		return gpsLoc.getLatitude(); //Latitude ���� ����
	}
	
	public double getDLongitude(){
		mProvider = mLocMan.getBestProvider(criteria, true); // ������ ��ġ ���� �����ڸ� ã�Ƴ���
		mLocMan.requestLocationUpdates(mProvider, 0, 0, mLocListener); // ���� �����ڸ� ���� ��ġ ������Ʈ�� �� ����
		gpsLoc = mLocMan.getLastKnownLocation(mProvider);  // ���� ��ġ ������ �ľ��س���
		return gpsLoc.getLongitude();
	}
	
	public String getSLatitude() {
		mProvider = mLocMan.getBestProvider(criteria, true); // ������ ��ġ ���� �����ڸ� ã�Ƴ���
		mLocMan.requestLocationUpdates(mProvider, 0, 0, mLocListener); // ���� �����ڸ� ���� ��ġ ������Ʈ�� �� ����
		gpsLoc = mLocMan.getLastKnownLocation(mProvider);  // ���� ��ġ ������ �ľ��س���
		return gpsLoc.getLatitude() + "";
	}
	
	public String getSLongitude(){
		mProvider = mLocMan.getBestProvider(criteria, true); // ������ ��ġ ���� �����ڸ� ã�Ƴ���
		mLocMan.requestLocationUpdates(mProvider, 0, 0, mLocListener); // ���� �����ڸ� ���� ��ġ ������Ʈ�� �� ����
		gpsLoc = mLocMan.getLastKnownLocation(mProvider);  // ���� ��ġ ������ �ľ��س���
		return gpsLoc.getLongitude() + "";
	}
}
