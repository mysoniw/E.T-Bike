package com.swmaestro.variable;

import com.swmaestro.object.WorkVectors;

import android.os.Environment;

public class Variable {
	public static final String LOG_TAG = "FacebookCon";
	  public static boolean D = true;
	  
	  public static final int FACEBOOK_AUTH_CODE = 32665;
	  
	  public static final String FACEBOOK_APP_ID = "354574991265954";
	  
	  public static final String ET_BIKE_FOLDER = "/ETBike/";
		public static final String SD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
		public static final String ROOT_DIR = SD_DIR + ET_BIKE_FOLDER;
		public static final String MY_PROFILE_DIR  = ROOT_DIR + "myprofile/";

		public static final String KOR_TRADE_TYPE_DELIEVERY = "�ù�";
		public static final String KOR_TRADE_TYPE_DIRECT_DEAL = "���ŷ�";
		
		public static final String KOR_SHARE_TYPE_RENT = "��Ʈ";
		public static final String KOR_SHARE_TYPE_DONATION = "���";
		public static final String KOR_SHARE_TYPE_SELL = "�Ǹ�";		
		
		public static final String KOR_COST_TIME = "�ð��� ���";
		public static final String KOR_COST_DAY = "�ϴ� ���";
		public static final String KOR_COST_WEEK = "�ִ� ���";
		
		public static final String KOR_BIKE_TYPE_MOUNTAIN = "��ǿ�";
		public static final String KOR_BIKE_TYPE_COMMUTE = "��ٿ�";
		public static final String KOR_BIKE_TYPE_PLAYER = "������";
		
		public static final int SHARE_TYPE_NUM = 3;
		
		public static final String KEY_URL_LIST = "urlList";
		public static final String DIAL_TYPE = "dial_type";
		public static final String DIAL_TYPE_BIKE = "dial_type_bike";
		public static final String DIAL_TYPE_TRADE = "dial_type_trade";
		public static final String DIAL_TYPE_SHARE = "dial_type_share";

		public static final String BIKE_TYPE = "my_bike_bike_type";
		public static final int BIKE_TYPE_MOUNTAIN = 0;
		public static final int BIKE_TYPE_COMMUTE = 1;
		public static final int BIKE_TYPE_PLAYER = 2;
		
		

		public static final String TRADE_TYPE = "my_bike_trade_type";
		

		public static final String SHARE_TYPE = "my_bike_share_type";
		

		public static final String DIAL_CONTENT = "dial_content";
		
		public static final String SEARCH_LOCATION = "search_location";
		public static final String SEARCH_LATITUDE = "search_lat";
		public static final String SEARCH_LONGITUDE = "search_lon";
		
		public static final String COST_TIME = "cost_time";
		public static final String COST_DAY = "cost_day";
		public static final String COST_WEEK = "cost_week";
		
		public static final String MDLA_BIKE_VIEW_TYPE = "mdla_bike_view";
		
		public static final String MY_PROFLE_IMG_PATH = "profile_img";
		
		public static final String SERVER_URL = "http://125.209.193.11:8080/etbike/account/addUser";
		
//		public static final String SERVER_BIKE_INFO_URL = "http://125.209.193.11:8080/etbike/boards";
		public static final String SERVER_BIKE_INFO_URL = "http://125.209.193.11:8080/etbike/shareBoard/addBoard/";  
		public static final String SERVER_GET_MY_BIKE_LIST_URL = "http://125.209.193.11:8080/etbike/shareBoard/getMyBikeList/";
		//http://125.209.193.11:8080/etbike/shareBoard/getMyDealList/sex 
		
		public static final String SERVER_IMG_URL = "http://125.209.193.11:8080/etbike/upload/img?CKEditor=contentEditor&CKEditorFuncNum=2&langCode=ko";

}