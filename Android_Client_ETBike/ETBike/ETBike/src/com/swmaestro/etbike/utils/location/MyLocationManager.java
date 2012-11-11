package com.swmaestro.etbike.utils.location;



import java.util.ArrayList;

import com.google.android.maps.GeoPoint;
import com.swmaestro.etbike.activity.listview.object.LocationItem;

import android.content.Context;
import android.location.LocationManager;
import android.location.LocationProvider;


public class MyLocationManager {
	
	Context context;
	LocationManager lm;
	GPSProvider gpsProvider;
	GeoProvider geoPrivider;
	
	public MyLocationManager(Context context) {
		
		lm = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
		gpsProvider = new GPSProvider(lm);
		geoPrivider = new GeoProvider(context);
		
	}
	
	public double getMyDLatitude() {
		return gpsProvider.getDLatitude();
	}
	
	public double getMyDLongitude() {
		return gpsProvider.getDLongitude();
	}
	
	public String getMySLatitude() {
		return gpsProvider.getSLatitude();
	}
	
	public String getMySLongitude() {
		return gpsProvider.getSLongitude();
	}
	
	public String getDetailLocationByLocation(String location) {
		return geoPrivider.getDetailLocationByLocation(location);
	}
	
	public ArrayList<LocationItem> getDetailLocationListByLocation(String location) {
		return geoPrivider.getDetailLocationListByLocation(location);
	}
	
	public GeoPoint getGeoPoint(int lati, int longi) {
		return geoPrivider.getGeoPoint(lati, longi);
	}
	
	public GeoPoint getGeoPoint(String lati, String longi) {
		return geoPrivider.getGeoPoint(lati, longi);
	}
	
	public GeoPoint getGeoPoint(Double lati, Double longi) {
		return geoPrivider.getGeoPoint(lati, longi);
	}
	
	

}
