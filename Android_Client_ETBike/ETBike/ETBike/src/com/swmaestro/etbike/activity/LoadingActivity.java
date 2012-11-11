package com.swmaestro.etbike.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadingActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		Handler mHandler = new Handler() {
			public void handleMessage(Message msg) {
				finish();
			}
		};
		mHandler.sendEmptyMessageDelayed(0, 4000);
	}
}
