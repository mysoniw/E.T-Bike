package com.swmaestro.etbike.utils.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.swmaestro.etbike.activity.listview.object.LocationItem;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.NoCopySpan.Concrete;
import android.util.Log;

public class GeoProvider {

	Context context;
	Geocoder geocoder;
	List<Address> addr = null;

	public GeoProvider(Context context) {

		this.context = context;
		this.geocoder = new Geocoder(context);

	}

	public String getLocationDetail(String lat, String lon) {

		try {
			addr = geocoder.getFromLocation(Double.parseDouble(lat),
					Double.parseDouble(lon), 5);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String detailLocation = composeAddressLine(addr.get(0));
		return detailLocation;

	}

	public String getDetailLocationByLocation(String location) {
		try {
			addr = geocoder.getFromLocationName(location, 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		location = composeAddressLine(addr.get(0));
		return location;

	}

	public ArrayList<LocationItem> getDetailLocationListByLocation(
			String location) {
		// ArrayList<String> al = ArrayList<String>();
		ArrayList<LocationItem> al = new ArrayList<LocationItem>();

		try {
			addr = geocoder.getFromLocationName(location, 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < addr.size(); i++) {
			String loc = composeAddressLine(addr.get(i));
			Double lat = addr.get(i).getLatitude();
			Double lon = addr.get(i).getLongitude();

			al.add(new LocationItem(loc, lat, lon));
		}

		return al;

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

		Log.e("compoaddrline in location worker", sb.toString());
		return sb.toString();
	}

	public GeoPoint getGeoPoint(int lati, int longi) {
		return new GeoPoint(lati, longi);
	}

	public GeoPoint getGeoPoint(String lati, String longi) {

		Double dlati = Double.parseDouble(lati);
		Double dlongi = Double.parseDouble(longi);
		int ilati = (int) (dlati * 1E6);
		int ilongi = (int) (dlongi * 1E6);

		return new GeoPoint(ilati, ilongi);
	}

	public GeoPoint getGeoPoint(Double lati, Double longi) {

		
		int ilati = (int) (lati * 1E6);
		int ilongi = (int) (longi * 1E6);

		return new GeoPoint(ilati, ilongi);
	}

}
