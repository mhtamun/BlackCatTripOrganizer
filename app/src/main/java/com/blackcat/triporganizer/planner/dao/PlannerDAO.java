package com.blackcat.triporganizer.planner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.blackcat.triporganizer.dao.DBConfiguration;

public class PlannerDAO {
	
	//Context of application who uses us.
	private DBConfiguration ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	public PlannerDAO(Context c){
		
		this.ourContext = c;
	}

	// Open Method

	public PlannerDAO open() throws SQLException{
	
		ourHelper = new DBConfiguration(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	
	}
	
	//Close Method

	public void close(){
	
		ourHelper.close();
	 
	}

	public long CreatePlannerEntry(String plannerTripName,
			String plannerDestinationname, String plannerStartdate,
			String plannerTravelBy, String placesToStay, String thingsToDo) {
		
		ContentValues v = new ContentValues();
		
		v.put(DBConfiguration.KEY_PLANNERNAME, plannerTripName);
		v.put(DBConfiguration.KEY_PLANNERCITY, plannerDestinationname);
		v.put(DBConfiguration.KEY_PLANNERDATE, plannerStartdate);
		v.put(DBConfiguration.KEY_PLANNERTRAVELBY, plannerTravelBy);
		v.put(DBConfiguration.KEY_PLANNERPLACESSTAY, placesToStay);
		v.put(DBConfiguration.KEY_PLANNERTHINGSTODO, thingsToDo);
		v.put(DBConfiguration.KEY_PLANNERSTATUS, "ACTIVE");
		
		ourDatabase.insert(DBConfiguration.PLANNER_TABLE, null, v);
		
		return 0;

	}

	public Cursor fetchAllPlannerEntries() {
		Cursor Cursor = ourDatabase.query(DBConfiguration.PLANNER_TABLE, new String[] {
				DBConfiguration.KEY_PLANNERID, 
				DBConfiguration.KEY_PLANNERNAME, 
				DBConfiguration.KEY_PLANNERCITY, 
				DBConfiguration.KEY_PLANNERDATE, 
				DBConfiguration.KEY_PLANNERTRAVELBY }, 
			    null, null, null, null, null);
			 
			 if (Cursor != null) {
			   Cursor.moveToFirst();
			 }
			 return Cursor;
	}

	public Cursor getPlannerInfo(long id) {
		
		String where = DBConfiguration.KEY_PLANNERID + "=" + id;
		String[] ALL_KEYS = new String[]{
				DBConfiguration.KEY_PLANNERID,
				DBConfiguration.KEY_PLANNERNAME,
				DBConfiguration.KEY_PLANNERCITY,
				DBConfiguration.KEY_PLANNERDATE,
				DBConfiguration.KEY_PLANNERTRAVELBY,
				DBConfiguration.KEY_PLANNERPLACESSTAY,
				DBConfiguration.KEY_PLANNERTHINGSTODO,
				DBConfiguration.KEY_PLANNERSTATUS
				
		};
		
		Cursor c = 	ourDatabase.query(true, DBConfiguration.PLANNER_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public void UpdateStatus(long id, String status) {

		ContentValues cvUpdate = new ContentValues();
		
		cvUpdate.put(DBConfiguration.KEY_PLANNERSTATUS, status);
		
		ourDatabase.update(DBConfiguration.PLANNER_TABLE, cvUpdate, DBConfiguration.KEY_PLANNERID + "=" + id, null);
		
	}



	public String getStatus(long id) {
		String Status = "";
		String[] ALL_KEYS = new String[]{
				DBConfiguration.KEY_PLANNERID,
				DBConfiguration.KEY_PLANNERNAME,
				DBConfiguration.KEY_PLANNERCITY,
				DBConfiguration.KEY_PLANNERDATE,
				DBConfiguration.KEY_PLANNERTRAVELBY,
				DBConfiguration.KEY_PLANNERPLACESSTAY,
				DBConfiguration.KEY_PLANNERTHINGSTODO,
				DBConfiguration.KEY_PLANNERSTATUS
				
		};
		try{
			Cursor c = ourDatabase.query(DBConfiguration.PLANNER_TABLE, ALL_KEYS, DBConfiguration.KEY_PLANNERID + "=" + id, null, null, null, null);
			if (c != null){
				c.moveToFirst();
				Status = c.getString(7);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Status;
	}

	public void deletePlannerEntry(long plannerID) {
		
		ourDatabase.delete(DBConfiguration.PLANNER_TABLE, DBConfiguration.KEY_PLANNERID + "=" + plannerID, null);
		
	}

}
