package com.swemaestro.etbike.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
	// public static final String ROOT = Other.SD_DIR + Other.FOLDER;
	Context context;
	public static int DB_VERSION = 19;
	String TAG = "DBHelper";

	public DBHelper(Context context) {
		super(context, "mybike.db", null, DB_VERSION);
		this.context = context;
	}

	@Override
	// day weekday slot POI action app
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		db.execSQL("CREATE TABLE myprofile (line TEXT, id int, name TEXT, lati TEXT ,longi TEXT);");
		db.execSQL("CREATE TABLE mybike (myimagepath TEXT, bikeimagepath TEXT, biketype TEXT, tradetype TEXT ,sharetype TEXT, lati TEXT, " +
				"longi TEXT, costpertime TEXT, costperday TEXT, costperweek TEXT, detail TEXT);");
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS mybike");
		onCreate(db);
	}

}
