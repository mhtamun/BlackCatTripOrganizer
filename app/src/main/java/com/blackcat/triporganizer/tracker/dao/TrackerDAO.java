package com.blackcat.triporganizer.tracker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.blackcat.triporganizer.dao.DBConfiguration;

public class TrackerDAO {
	
	//DataBase Field numbers here (0 = DBConfiguration.KEY_TRACKERID, 1=...)
	public static final int COL_ROWID = 0;
	
	public static final int COL_NAME = 1;
	public static final int COL_CITY = 2;
	public static final int COL_REASON = 3;
	public static final int COL_SOD = 4;
	public static final int COL_AD = 5;
	public static final int COL_BUDGET  = 6;
	
	public static final int COL_HE = 7;
	public static final int COL_TE = 8;
	public static final int COL_FE = 9;
	public static final int COL_EE = 10;
	public static final int COL_SE = 11;
	public static final int COL_OE = 12;
	public static final int COL_REMAINBUDGET = 13;
	
	public static final String[] ALL_KEYS = new String[]{
		DBConfiguration.KEY_TRACKERID, 
		DBConfiguration.KEY_NAME, 
		DBConfiguration.KEY_CITY, 
		DBConfiguration.KEY_REASON, 
		DBConfiguration.KEY_SOD, 
		DBConfiguration.KEY_AD, 
		DBConfiguration.KEY_BUDGET, 
		DBConfiguration.KEY_HE, 
		DBConfiguration.KEY_TE, 
		DBConfiguration.KEY_FE, 
		DBConfiguration.KEY_EE, 
		DBConfiguration.KEY_SE, 
		DBConfiguration.KEY_OE, 
		DBConfiguration.KEY_REMAINBUDGET };

	
	//Context of application who uses us.
	private DBConfiguration ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	public TrackerDAO(Context c){
		
		this.ourContext = c;
	}

	// Open Method

	public TrackerDAO open() throws SQLException{
	
		ourHelper = new DBConfiguration(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	
	}
	
	//Close Method

	public void close(){
	
		ourHelper.close();
	 
	}

	public long createEntry(String TripName, String TripReason, String cityName, String SD, String AD, String budget, int HE, int TE, int FE, int EE, int SE, int OE, int remainBudget ) {
		
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfiguration.KEY_NAME,TripName);
		cv.put(DBConfiguration.KEY_CITY,cityName);
		cv.put(DBConfiguration.KEY_REASON,TripReason);
		cv.put(DBConfiguration.KEY_SOD,SD);
		cv.put(DBConfiguration.KEY_AD,AD);
		cv.put(DBConfiguration.KEY_BUDGET,budget);
		cv.put(DBConfiguration.KEY_HE,HE);
		cv.put(DBConfiguration.KEY_TE,TE);
		cv.put(DBConfiguration.KEY_FE,FE); 
		cv.put(DBConfiguration.KEY_FE,EE);
		cv.put(DBConfiguration.KEY_SE,SE);
		cv.put(DBConfiguration.KEY_OE,OE);
		cv.put(DBConfiguration.KEY_REMAINBUDGET,remainBudget);
		
		ourDatabase.insert(DBConfiguration.TRACKER_TABLE, null, cv);
		
		return 0;
	}

	public Cursor fetchAllEntries() {
		Cursor Cursor = ourDatabase.query(DBConfiguration.TRACKER_TABLE, new String[] {DBConfiguration.KEY_TRACKERID, 
				DBConfiguration.KEY_NAME, 
				DBConfiguration.KEY_CITY, 
				DBConfiguration.KEY_REASON, 
				DBConfiguration.KEY_BUDGET }, 
			    null, null, null, null, null);
			 
			  if (Cursor != null) {
			   Cursor.moveToFirst();
			  }
			  return Cursor;
	}

	public Cursor getInfo(long id) {
		
		String where = DBConfiguration.KEY_TRACKERID + "=" + id;
		Cursor c = 	ourDatabase.query(true, DBConfiguration.TRACKER_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	public long getMaxID() {
        int id = 0;
        final String MY_QUERY = "SELECT MAX(_id) AS _id FROM tracker_table";
        Cursor mCursor = ourDatabase.rawQuery(MY_QUERY, null);  

              if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                id = mCursor.getInt(mCursor.getColumnIndex(DBConfiguration.KEY_TRACKERID));
              }

    return id;
	}
	
	public String getArivaldateforCheck(long getlastID) {
		
		String ADforCheck = null;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			
			if (c != null){
				c.moveToFirst();
				ADforCheck = c.getString(COL_AD);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return ADforCheck;
	}


	public void updateArrivalDateEntry(long getlastID, String arrivalDate) {
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_AD, arrivalDate);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
	}

	public int getPrevRemainBudget(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_REMAINBUDGET);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updatePrevRemainBudget(long getlastID, int prev_remainbudget) {
		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_REMAINBUDGET, prev_remainbudget);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
	
	}
	public int getAccommodationPrev(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_HE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updateAccommodationEntry(long getlastID, int prev_rent) {

		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_HE, prev_rent);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
		
	}

	public int getTransportPrev(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_TE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updatetransportEntry(long getlastID, int prev_transport) {

		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_TE, prev_transport);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
		
	}

	public int getFoodPrev(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_FE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updatefoodEntry(long getlastID, int prev_food) {
		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_FE, prev_food);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
		
	}

	public int getEntertainmentPrev(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_EE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updateEntertainmentEntry(long getlastID, int prev_entertainment) {
		

		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_EE, prev_entertainment);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
		
	}

	public int getShoppingPrev(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_SE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updateshoppingEntry(long getlastID, int prev_shopping) {

		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_SE, prev_shopping);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
		
	}

	public int getOtherPrev(long getlastID) {
		int prev_ = 0;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				prev_ = c.getInt(COL_OE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return prev_;
	}

	public void updateotherEntry(long getlastID, int prev_other) {
		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_OE, prev_other);
		
		ourDatabase.update(DBConfiguration.TRACKER_TABLE, cvUpdate, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null);
		
	}

	public String getLatestProfileName(long getlastID) {
		String latest_Name = "";
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				latest_Name = c.getString(COL_NAME);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return latest_Name;
	}

	public void deleteEntry(long idDB) {
		
		ourDatabase.delete(DBConfiguration.TRACKER_TABLE, DBConfiguration.KEY_TRACKERID + "=" + idDB, null);
		
	}

	public String getEstimatedBudget(long getlastID) {
		String Estimated_Budget = "";
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.TRACKER_TABLE, ALL_KEYS, DBConfiguration.KEY_TRACKERID + "=" + getlastID, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				Estimated_Budget = c.getString(COL_BUDGET);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Estimated_Budget;
	}

}
