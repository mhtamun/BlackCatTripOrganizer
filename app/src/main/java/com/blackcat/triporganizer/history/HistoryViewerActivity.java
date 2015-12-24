package com.blackcat.triporganizer.history;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.tracker.TrackerMenuActivity;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryViewerActivity extends Activity implements OnClickListener{
	
	TextView TripName, TripReason, cityName, SD, AD, budget, HE, TE, FE, EE, SE, OE, RemainBudget;
	
	ImageButton CheckedIn, Delete;
	
	long idDB;

	String name ;
	
	String city;
	String reason;
	String startingdate;
	String arrivaldate;
	String tbudget;
	
	int hexp;
	int texp;
	int fexp;
	int eexp;
	int sexp;
	int oexp;
	
	int rbudget;
	
	TrackerDAO dbHelper;
	
	public static int checkfrom;
	public static String TripNameforDBConnect;
	
	ProgressDialog d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_viewer);
		
		TripName = (TextView) findViewById(R.id.textView_History_TripName);
		
		cityName = (TextView) findViewById(R.id.textView_History_CityName);
		TripReason = (TextView) findViewById(R.id.textView_History_TripReason);
		SD = (TextView) findViewById(R.id.textView_History_SD);
		AD = (TextView) findViewById(R.id.textView__History_AD);
		budget = (TextView) findViewById(R.id.textView_History_Budget);
		
		HE = (TextView) findViewById(R.id.textView_History_Hotel);
		TE = (TextView) findViewById(R.id.textView_History_Transport);
		FE = (TextView) findViewById(R.id.textView__History_Food);
		EE = (TextView) findViewById(R.id.textView_History_Entertainment);
		SE = (TextView) findViewById(R.id.textView_History_Shopping);
		OE = (TextView) findViewById(R.id.textView_History_Other);
		RemainBudget = (TextView) findViewById(R.id.textView_History_RemainingBudget);
		
		CheckedIn = (ImageButton) findViewById(R.id.imageButton_CheckedIN);
		CheckedIn.setOnClickListener(this);
		Delete = (ImageButton) findViewById(R.id.imageButton_DeleteButton);
		Delete.setOnClickListener(this);
		
		idDB = HistoryListActivity.TripID;
		
		
		
		displayshowHistory(idDB);
		
		TripName.setText(name);
		cityName.setText(city);
		TripReason.setText(reason);
		SD.setText(startingdate);
		AD.setText(arrivaldate);
		budget.setText(tbudget);
		HE.setText(String.valueOf(hexp));
		TE.setText(String.valueOf(texp));
		FE.setText(String.valueOf(fexp));
		EE.setText(String.valueOf(eexp));
		SE.setText(String.valueOf(sexp));
		OE.setText(String.valueOf(oexp));
		RemainBudget.setText(String.valueOf(rbudget));

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton_CheckedIN:
			
			TripNameforDBConnect = name;
			
			Intent OpenCheckPlaceList = new Intent(this, CheckPlaceViewerActivity.class);
			startActivity(OpenCheckPlaceList);
			
			
			break;
			
		case R.id.imageButton_DeleteButton:
			
			deleteFunction();
			
			break;

		}
		
	}
	
	private void displayshowHistory(long id) {
		
		try{
			
		dbHelper = new TrackerDAO(this);
		
		}catch(SQLException e){
			
			e.printStackTrace();
		}
			
		dbHelper.open();
			
		Cursor cursor = dbHelper.getInfo(id);
			
		if (cursor.moveToFirst()) {
			name = cursor.getString(TrackerDAO.COL_NAME);
			city = cursor.getString(TrackerDAO.COL_CITY);
			reason = cursor.getString(TrackerDAO.COL_REASON);
			startingdate = cursor.getString(TrackerDAO.COL_SOD);
			arrivaldate = cursor.getString(TrackerDAO.COL_AD);
			tbudget = cursor.getString(TrackerDAO.COL_BUDGET);
				
			hexp = cursor.getInt(TrackerDAO.COL_HE);
			texp = cursor.getInt(TrackerDAO.COL_TE);
			fexp = cursor.getInt(TrackerDAO.COL_FE);
			eexp = cursor.getInt(TrackerDAO.COL_EE);
			sexp = cursor.getInt(TrackerDAO.COL_SE);
			oexp = cursor.getInt(TrackerDAO.COL_OE);
			rbudget = cursor.getInt(TrackerDAO.COL_REMAINBUDGET);

		}
			
			dbHelper.close(); 	
	}

	
	private void deleteFunction() {
		
		if (checkfrom == 2){
    		
    		Toast.makeText(getApplicationContext(), "This Profile Is Running, Close it then Delete", Toast.LENGTH_LONG).show();

    		
    	}else if (checkfrom == 1){
		
		 AlertDialog.Builder alertDialog = new AlertDialog.Builder(HistoryViewerActivity.this);
		     
		    // Setting Dialog Title
		 alertDialog.setTitle("Confirm Delete...");
		     
		    // Setting Dialog Message
		 alertDialog.setMessage("Are you sure you want delete this Profile? Click Yes!");
		     
		    // Setting Icon to Dialog
		 alertDialog.setIcon(R.drawable.deleteicon);
		     
		    // Setting Positive "Yes" Button
		 alertDialog.setPositiveButton("YES",
				 new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
	
			                dbHelper.open();
			        		String getAD = dbHelper.getArivaldateforCheck(idDB);
			        		dbHelper.close();
	
			        		if (getAD.contentEquals("Profile on Running")) {
			        				
			        			Toast.makeText(getApplicationContext(), "This Profile Is Running, Close it then Delete", Toast.LENGTH_LONG).show();
			        				
			        		}else{
			        				
			        			dbHelper.open();
				    			dbHelper.deleteEntry(idDB);
				    			dbHelper.close();
				    			
								d = new ProgressDialog(HistoryViewerActivity.this);
								ProgressDialog.show(HistoryViewerActivity.this, "Trip Profile Deleting!", "Please Wait...");
				    				
				    			Intent sameActivity = new Intent(HistoryViewerActivity.this, HistoryListActivity.class);
				    			startActivity(sameActivity);	
			        				
			        		}
	               
		    			}
		            });
		    // Setting Negative "NO" Button
		    alertDialog.setNegativeButton("NO",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
		                    dialog.cancel();
		                }
		            });
		    alertDialog.show();
    	}
	}

	protected void onPause() {
		super.onPause();
		finish();
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode){
            
            case KeyEvent.KEYCODE_BACK:
            	
            	if (checkfrom == 1){

	            	Intent OpenMyActivity = new Intent (HistoryViewerActivity.this, HistoryListActivity.class);
	            	startActivity(OpenMyActivity);
            	
            	}else if (checkfrom == 2) {
            		
                	Intent OpenMyActivity = new Intent (HistoryViewerActivity.this, TrackerMenuActivity.class);
                	startActivity(OpenMyActivity);
				}
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
