package com.swmaestro.etbike.utils.location;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MyDynamicLocationOverlay extends MyLocationOverlay {

	Context context;
	String TAG = "MyDynamicLocationOverlay";
	GPSProvider gProvider;
	Geocoder geocoder;

	public MyDynamicLocationOverlay(Context context, MapView mapView) {
		super(context, mapView);
		this.context = context;
		LocationManager mLocMan = (LocationManager) context
				.getSystemService(context.LOCATION_SERVICE);
		gProvider = new GPSProvider(mLocMan);
		geocoder = new Geocoder(context);
	}

	protected boolean dispatchTap() {
		Toast.makeText(context, "여기가 현재 위치 입니다.", Toast.LENGTH_LONG).show();
		return false;
	}

	public String getLocationDetail() {

		Double lat = gProvider.getDLatitude();
		Double lon = gProvider.getDLongitude();

		List<Address> addr;
		try {
			addr = geocoder.getFromLocation(lat, lon, 5);

			String detailLocation = composeAddressLine(addr.get(0));
			return detailLocation;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String composeAddressLine(Address addr) {
		StringBuilder sb = new StringBuilder();

		if (addr.getLocality() != null)
			sb.append(addr.getLocality() + " ");
		if (addr.getSubLocality() != null)
			sb.append(addr.getSubLocality() + " ");
		if (addr.getThoroughfare() != null)
			sb.append(addr.getThoroughfare() + " ");
		// if(addr.getLocality() != null)
		// sb.append(addr.getSubThoroughfare());
		Log.e(TAG, sb.toString());
		return sb.toString();
	}
	
	
	

}
