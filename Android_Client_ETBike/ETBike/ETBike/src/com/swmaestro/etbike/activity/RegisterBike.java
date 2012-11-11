package com.swmaestro.etbike.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.swmaestro.etbike.activity.dialog.DialogManager;
import com.swmaestro.etbike.activity.listview.MyListAdapter;
import com.swmaestro.etbike.activity.listview.MyMapMarker;
import com.swmaestro.etbike.activity.listview.object.RegisterItem;
import com.swmaestro.etbike.network.NetworkManager;
import com.swmaestro.etbike.utils.location.GPSProvider;
import com.swmaestro.etbike.utils.location.GeoProvider;
import com.swmaestro.etbike.utils.location.MyDynamicLocationOverlay;
import com.swmaestro.etbike.utils.location.MyLocationManager;
import com.swmaestro.object.WorkVectors;

public class RegisterBike extends MapActivity {
	Spinner spner;

	final int TAKE_CAMERA = 1;
	final int TAKE_GALLERY = 2;

	ImageView bikePicIV;
	ImageView myPicIV;
	
	String bikeImgPath = null;

	ListView lv;
	ArrayList<RegisterItem> al;
	MyListAdapter mla;

	WorkVectors wv;

	int bikeType = 0;
	int tradeType = 0;
	int shareType = 0;

	Context context;

	AlertDialog.Builder builder;
	AlertDialog ad;

	MapView mv;
	MyDynamicLocationOverlay mdlo;

	TextView mapRegisterTV;
	int FIND_LOCAION_BY_MAP = 18;

	DialogManager dm;

	String TAG = "RegisterBike";
	
	NetworkManager nm;
	
	EditText registerBikeET;
	
	Button facebookToggleBtn;
	boolean faceBooktoggleFlag = false;
	
	String latitude;
	String longitude;
	
	GPSProvider gProvier;
	MyLocationManager lcm;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTitleBar(R.layout.register, R.layout.registertitlebar);
		
		
		wv = new WorkVectors();
		lcm = new MyLocationManager(this);
		
		
		wv.put(WorkVectors.UPLOAD_FACEBOOK_FLAG, faceBooktoggleFlag);
		wv.put(WorkVectors.BIKE_LATITUDE, lcm.getMyDLatitude());
		wv.put(WorkVectors.BIKE_LONGITUDE, lcm.getMyDLongitude());
		
		
		myPicIV = (ImageView) findViewById(R.id.myPicIVRegister);
		bikePicIV = (ImageView) findViewById(R.id.registerBikeImageTVRegister);
		
		lv = (ListView)findViewById(R.id.registerFeatureLVRegister);
		registerBikeET = (EditText)findViewById(R.id.registerBikeDetailETRegister);
		
		//network
		
		
		this.context = this;
		nm = new NetworkManager(wv, mHandler,NetworkManager.COMM_DOWN_LOAD_MY_PROFILE_IMAGE);
		nm.start();
//		initMyImage();
		 

		al = new ArrayList<RegisterItem>();

		al.add(new RegisterItem("자전거 타입",""));
		al.add(new RegisterItem("거래 타입",""));
		al.add(new RegisterItem("공유  타입",""));
		
		mla = new MyListAdapter(this, R.layout.registerfeatureitem, al);
		lv.setAdapter(mla);
		

		mv = (MapView) findViewById(R.id.mapview);
		
		
		findViewById(R.id.facebookToggleBtn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//			    if(faceBooktoggleOn =)
				
				if(faceBooktoggleFlag == true) {
					faceBooktoggleFlag = false;
					Toast.makeText(context, "toggle button off", Toast.LENGTH_LONG).show();
				}else {
					faceBooktoggleFlag = true;
					Toast.makeText(context, "toggle button on", Toast.LENGTH_LONG).show();
				}
				wv.put(WorkVectors.UPLOAD_FACEBOOK_FLAG, faceBooktoggleFlag);
				
				
			}
		});
		mdlo = new MyDynamicLocationOverlay(this, mv);
		
		mdlo.enableCompass();
		mdlo.enableMyLocation();

		mdlo.runOnFirstFix(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				mv.getController().animateTo(mdlo.getMyLocation());

			}
		});

		mv.getOverlays().add(mdlo);

		mapRegisterTV = (TextView) findViewById(R.id.mapRegisterTV);
		mapRegisterTV.setText("현재위치 : " + mdlo.getLocationDetail());
		
		dm = new DialogManager(this);



		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,	int postion, long arg3) {
				// TODO Auto-generated method stub
				if (postion == 0) {
					wv.put(WorkVectors.DIAL_TYPE, WorkVectors.DIAL_TYPE_BIKE);

				} else if (postion == 1) {
					wv.put(WorkVectors.DIAL_TYPE, WorkVectors.DIAL_TYPE_TRADE);

				} else if (postion == 2) {
					wv.put(WorkVectors.DIAL_TYPE, WorkVectors.DIAL_TYPE_SHARE);

				}
				dm.getRegisterDialog(wv,al,mla).show();
				mla.notifyDataSetChanged();
				

			}

		});
		
		findViewById(R.id.mapRegisterTV).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivityForResult(new Intent(context, FindLocationActivity.class),FIND_LOCAION_BY_MAP );
				
			}
		});

		findViewById(R.id.registerBtnTitleBar).setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String detail = registerBikeET.getText().toString();
						if(detail.equals("")) {
							Toast.makeText(context, "detail을 입력해주세요.", Toast.LENGTH_LONG).show();
							return;
							
						}
						if(bikeImgPath == null) {
							Toast.makeText(context, "이미지를 등록하세요.", Toast.LENGTH_LONG).show();
							return;
							
						}
						Toast.makeText(context, "등록완료 되었습니다.", Toast.LENGTH_LONG).show();
						wv.put(WorkVectors.BIKE_INFO_CONTENT,detail);
						
						updateMyBike();


					}
				});
		
		
		findViewById(R.id.registerBikeImageTVRegister).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						getCameraDialog().show();

					}
				});
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			
			if(msg.what == NetworkManager.COMM_DOWN_LOAD_MY_PROFILE_IMAGE) {
				String imageURL = wv.getData(WorkVectors.MY_PROFLE_IMG_PATH) + "";
				Bitmap bm = BitmapFactory.decodeFile(imageURL);
				myPicIV.setImageBitmap(bm);
				
			}
			
		}
	};
	
	private boolean updateMyBike() {
		Log.e(TAG ,"update my bike " + wv.getData(WorkVectors.BIKE_IMG_PATH) + "");
		nm = new NetworkManager(wv, mHandler, NetworkManager.COMM_UPLOAD_MY_BIKE_INFOS);
		nm.start();
		return true;
	}



	private void initTitleBar(int mainLayout, int titleBarLayout) {

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(mainLayout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, titleBarLayout);

	}

	private AlertDialog getCameraDialog() {

		builder = new AlertDialog.Builder(context);

		final LinearLayout ll = (LinearLayout) View.inflate(context,
				R.layout.listviewdialog, null);

		ListView lv = (ListView) ll.findViewById(R.id.listViewLV);
		final ArrayList<String> al = new ArrayList<String>();

		al.add("사진찾기");
		al.add("카메라");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, al);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				if (position == 0) {
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_GET_CONTENT);
					intent.setType("image/*");
					startActivityForResult(intent, TAKE_GALLERY);

				} else if (position == 1) {
					Intent intent = new Intent();
					intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, TAKE_CAMERA);

				}
				ad.dismiss();

			}

		});

		builder.setTitle("세부정보를 등록해주세요.").setView(ll);
		ad = builder.create();
		return ad;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Uri currImageURI = null;
		Log.e(TAG,"result code == " + requestCode);
		
		if (resultCode == RESULT_OK) {
			if (requestCode == FIND_LOCAION_BY_MAP) {
				GeoProvider gp = new GeoProvider(context);
				String lat = data.getStringExtra("latitude");
				String lon = data.getStringExtra("longitude");
				String loc = data.getStringExtra("location");
				
				wv.put(WorkVectors.BIKE_LATITUDE, lat);
				wv.put(WorkVectors.BIKE_LONGITUDE, lon);
				
				Log.e(TAG + "onactvitituresult", "result ok");
				Log.e(TAG + "onactvitituresult", "location ==  " + loc);
				Log.e(TAG + "onactvitituresult", "latitude ==  " + lat);

				mapRegisterTV.setText("현재 위치  : " + loc);
				int iLat = (int) (Double.parseDouble(lat) * 1E6);
				int iLon = (int) (Double.parseDouble(lon) * 1E6);
				
				
				
				addOverlay(iLat, iLon);

			} else {
				if (requestCode == TAKE_CAMERA) {
					currImageURI = data.getData();

					// tv.setText("CAMERA : " +
					// getRealPathFromURI(currImageURI));
					// bikePicIV.setb

				} else if (requestCode == TAKE_GALLERY) {
					currImageURI = data.getData();
					// tv.setText("GALLERY : " +
					// getRealPathFromURI(currImageURI));
				}
				bikeImgPath = getRealPathFromURI(currImageURI);
				wv.put(WorkVectors.BIKE_IMG_PATH, bikeImgPath);
				Bitmap bm = BitmapFactory.decodeFile(bikeImgPath);
				bikePicIV.setImageBitmap(bm);

			}

		}
	}

	private void addOverlay(int lati, int longi) {
		MyMapMarker mapMarker = null;

		int ilati = (int) (lati * 1E6);
		int ilongi = (int) (longi * 1E6);
		GeoPoint gp = new GeoPoint(ilati, ilongi);

		Drawable stDraw = context.getResources().getDrawable(
				R.drawable.map_departure_icon);
		stDraw.setBounds(0, 0, stDraw.getIntrinsicWidth(),
				stDraw.getIntrinsicHeight());

		mapMarker = new MyMapMarker(stDraw, context);
		mapMarker.addOverlay(new OverlayItem(gp, "", ""), stDraw, Color.BLACK);

	}

	private String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);

	}

	private void animateLocation(GeoPoint gp) {
		mv.getController().setZoom(17);
		mv.getController().animateTo(gp);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onPause() {
		super.onPause();
		mdlo.disableCompass();
		mdlo.enableCompass();
	}

}

// public void onPause() {
// super.onPauss();
