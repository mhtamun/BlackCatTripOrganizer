package com.blackcat.triporganizer.tracker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.blackcat.triporganizer.dao.DBConfiguration;

public class CheckPlaceDAO {
	
	private DBConfiguration ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	public CheckPlaceDAO(Context c){
		
		this.ourContext = c;
	}

	// Open Method

	public CheckPlaceDAO open() throws SQLException{
	
		ourHelper = new DBConfiguration(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	
	}
	
	//Close Method

	public void close(){
	
		ourHelper.close();
	 
	}

	public long CreateCheckIn(String placeName, String checkInTime,
			String checkOutTime, String cetegory, String latestProfileName,
			int cost) {
		
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfiguration.KEY_PLACENAME,placeName);
		cv.put(DBConfiguration.KEY_IN,checkInTime);
		cv.put(DBConfiguration.KEY_OUT,checkOutTime);
		cv.put(DBConfiguration.KEY_CATEGORY,cetegory);
		cv.put(DBConfiguration.KEY_TRIPNAME,latestProfileName);
		cv.put(DBConfiguration.KEY_SPENDINGS,cost);
		
		ourDatabase.insert(DBConfiguration.CHECK_TABLE, null, cv);
		
		return 0;
		
	}

	public Cursor GetAllCheckPlaceEntries(String tripNameforDBConnect) {
		Cursor Cursor = ourDatabase.query(DBConfiguration.CHECK_TABLE, null, DBConfiguration.KEY_TRIPNAME + " = ?", new String[] {
                "" + tripNameforDBConnect
            }, null, null, null);
			 
			  if (Cursor != null) {
			   Cursor.moveToFirst();
			  }
			  return Cursor;
	}

	public Cursor GetCheckPlacedetails(long id) {
		
		String where = DBConfiguration.KEY_CHECKID + "=" + id;
		
		Cursor c = 	ourDatabase.query(true, DBConfiguration.CHECK_TABLE, null, where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public long GetMaxIdFromCheckDAO() {

        int id = 0;
        final String MY_QUERY = "SELECT MAX(_id) AS _id FROM checkin_table";
        Cursor mCursor = ourDatabase.rawQuery(MY_QUERY, null);  

              if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                id = mCursor.getInt(mCursor.getColumnIndex(DBConfiguration.KEY_CHECKID));
              }

    return id;
	}

	public String getCheckOutData(long getNewEntryID) {
		
		String CheckOutCheck = null;
		
		try{
			Cursor c = ourDatabase.query(DBConfiguration.CHECK_TABLE, null, DBConfiguration.KEY_CHECKID + "=" + getNewEntryID, null, null, null, null);
			
			if (c != null){
				c.moveToFirst();
				CheckOutCheck = c.getString(3);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return CheckOutCheck;
	}

	public void UpdateCheckOut(long getNewEntryID, int spendingsINT, String checkOutTime) {
		
		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_OUT, checkOutTime);
		cvUpdate.put(DBConfiguration.KEY_SPENDINGS, spendingsINT);
		
		ourDatabase.update(DBConfiguration.CHECK_TABLE, cvUpdate, DBConfiguration.KEY_CHECKID + "=" + getNewEntryID, null);
		
	}

}
