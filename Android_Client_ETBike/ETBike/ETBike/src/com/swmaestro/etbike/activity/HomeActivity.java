package com.swmaestro.etbike.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.paran.animation.demo.app.animation.PathButton;
import com.paran.animation.demo.app.animation.PathButtonAnimation;
import com.swmaestro.etbike.network.NetworkManager;
import com.swmaestro.etbike.push.PushService;
import com.swmaestro.object.WorkVectors;
import com.swmaestro.variable.Variable;

public class HomeActivity extends Activity {

	ImageView peopleIV[];
	int imageViewNum = 0;
	


	String TAG = "Path";

	public static final int PEOPLE_IV_NUM = 12;
	Context context;
	WorkVectors wv;
	
	
	NetworkManager nm;
	
	private ArrayList<PathButton> buttons;
	private Button plus_button;
	private ImageView plus;
	PathButtonAnimation pathButtonAnimation;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.home, R.layout.hometitlebar);
		
		plus_button = (Button) findViewById(R.id.plus_button);
		plus = (ImageView) findViewById(R.id.plus);
		buttons = new ArrayList<PathButton>();		

		PathButton button = (PathButton) findViewById(R.id.homeGoToHomePathBtn);		
		buttons.add(button);

		button = (PathButton) findViewById(R.id.homeSceneWithBikePathBtn);		
		buttons.add(button);

		button = (PathButton) findViewById(R.id.homeRideWithMePathBtn);		
		buttons.add(button);
		
		button = (PathButton) findViewById(R.id.homeShareYourBikePathBtn);
		buttons.add(button);
		
		
		pathButtonAnimation = new PathButtonAnimation(buttons, this, plus_button, plus);
		findViewById(R.id.viewGroup).setOnTouchListener(pathButtonAnimation.myTouchListener);
		
		SharedPreferences firstLoginState = getSharedPreferences("firstLoginState", 0);
		boolean firstLoginFlag = firstLoginState.getBoolean("firstLoginState", true);
		
		nm = new NetworkManager(wv, mHandler, -1);
		
		/*
		 * set push service
		 */
		
		Intent serviceIntent = new Intent(this, PushService.class);
		startService(serviceIntent);
		
		
		
		if(firstLoginFlag) {
			SharedPreferences.Editor loginStateEditor = firstLoginState.edit();
			loginStateEditor.putBoolean("firstLoginState", false);
			nm.setCommand(NetworkManager.COMM_SEND_MY_ACCOUNT_INFO);
			nm.start();
		}
		
		
		
		findViewById(R.id.profileBtnhomeTitleBar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(context, MyProfileActivity.class));
				
			}
		});
		
		context = getApplicationContext();
		makeDir(Variable.ROOT_DIR);		

		wv = new WorkVectors();
	
		
		

	}
	
	private SharedPreferences getSharedPreferences(String string, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	

	private void init(int titleBarLayout, int titleBarView) {
		initTitleBar(titleBarLayout, titleBarView);
		initIV();
	}

	private void initTitleBar(int mainLayout, int titleBarLayout) {

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(mainLayout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, titleBarLayout);

	}

	private void initIV() {

		peopleIV = new ImageView[PEOPLE_IV_NUM];
		peopleIV[0] = (ImageView) findViewById(R.id.peopleIV0);
		peopleIV[1] = (ImageView) findViewById(R.id.peopleIV1);
		peopleIV[2] = (ImageView) findViewById(R.id.peopleIV2);
		peopleIV[3] = (ImageView) findViewById(R.id.peopleIV3);
		peopleIV[4] = (ImageView) findViewById(R.id.peopleIV4);
		peopleIV[5] = (ImageView) findViewById(R.id.peopleIV5);
		peopleIV[6] = (ImageView) findViewById(R.id.peopleIV6);
		peopleIV[7] = (ImageView) findViewById(R.id.peopleIV7);
		peopleIV[8] = (ImageView) findViewById(R.id.peopleIV8);
		peopleIV[9] = (ImageView) findViewById(R.id.peopleIV9);
		peopleIV[10] = (ImageView) findViewById(R.id.peopleIV10);
		peopleIV[11] = (ImageView) findViewById(R.id.peopleIV11);

	}

	private void makeDir(String path) {
		File dir = new File(path);
		dir.mkdir();
	}

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == NetworkManager.COMM_DOWN_LOAD_HOME_IMAGE) {
				String path = msg.obj.toString();
				Bitmap bm2 = BitmapFactory.decodeFile(path);
				// image.setImageBitmap(bm2);
				peopleIV[++imageViewNum].setImageBitmap(bm2);

			}

		}
	};

}
