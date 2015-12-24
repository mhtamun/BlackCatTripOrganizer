package com.blackcat.triporganizer.history;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.dao.DBConfiguration;
import com.blackcat.triporganizer.tracker.dao.CheckPlaceDAO;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;

public class CheckPlaceViewerActivity extends Activity {

    private CheckPlaceDAO ConnectDB;
    private SimpleCursorAdapter DataBaseAdapter;

    private String InDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_place_viwer);


        try {

            ConnectDB = new CheckPlaceDAO(this);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        displayCheckPlaceListView();
    }

    private void displayCheckPlaceListView() {

        ConnectDB.open();

        Cursor CursorCheckPlaces = ConnectDB.GetAllCheckPlaceEntries(HistoryViewerActivity.TripNameforDBConnect);

        ConnectDB.close();

        // The desired columns to be bound
        String[] columns = new String[]{
                DBConfiguration.KEY_PLACENAME,
                DBConfiguration.KEY_IN,
                DBConfiguration.KEY_OUT,
                DBConfiguration.KEY_CATEGORY,
                DBConfiguration.KEY_SPENDINGS
        };
        int[] to = new int[]{
                R.id.TV_PlaceName,
                R.id.TV_CheckInTime,
                R.id.TV_CheckOutTime,
                R.id.TV_PlaceCategory,
                R.id.TV_Spendings
        };

        DataBaseAdapter = new SimpleCursorAdapter(this, R.layout.checkin_list_item, CursorCheckPlaces, columns, to, 0);

        ListView listView = (ListView) findViewById(R.id.listView_CheckInViewer);

        listView.setEmptyView(findViewById(R.id.textView_CheckInListforEmpty));
        // Assign adapter to ListView
        listView.setAdapter(DataBaseAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                DisplayInDetails(id);

            }

        });

    }

    protected void DisplayInDetails(long id) {


        ConnectDB.open();

        Cursor CursorInDetails = ConnectDB.GetCheckPlacedetails(id);

        if (CursorInDetails.moveToFirst()) {

            String placeName = CursorInDetails.getString(1);
            String checkIN = CursorInDetails.getString(2);
            String checkOUT = CursorInDetails.getString(3);
            String placeCategory = CursorInDetails.getString(4);
            int placeSpendings = CursorInDetails.getInt(6);

            InDetails = "Trip Name: " + placeName + "\n"
                    + "Check-In: " + checkIN + "\n"
                    + "Check-Out: " + checkOUT + "\n"
                    + "Place Category: " + placeCategory + "\n"
                    + "Spendings: " + placeSpendings;
        }

        ConnectDB.close();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CheckPlaceViewerActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Your Trip Information");

        // Setting Dialog Message
        alertDialog.setMessage(InDetails);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.travelicon);

        // Setting Positive "Back" Button
        alertDialog.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Setting Negative "Delete" Button
        /*alertDialog.setNegativeButton("Delete",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				
			    dialog.cancel();
			    
			    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(CheckPlaceViewerActivity.this);
			     
			    // Setting Dialog Title
			    alertDialog2.setTitle("Confirm Delete...");
			     
			    // Setting Dialog Message
			    alertDialog2.setMessage("Are you sure you want delete this Profile?");
			     
			    // Setting Icon to Dialog
			    alertDialog2.setIcon(R.drawable.alerticon);
			     
			    // Setting Positive "Yes" Button
			    alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			    	public void onClick(DialogInterface dialog2, int which) {
			    		
			    		
			    		//Deleting Code will Be Here
			    		//
			    		//
			    		//--------------------------
			                	
			        }
			   	});
			    // Setting Negative "NO" Button
			    alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			    	public void onClick(DialogInterface dialog2, int which) {
			                    dialog2.cancel();
			        }
			    });
			     
			    // Showing Alert Dialog
			    alertDialog2.show();
			}
				});*/

        // Showing Alert Dialog
        alertDialog.show();

    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:


                    Intent OpenMyActivity = new Intent(CheckPlaceViewerActivity.this, HistoryViewerActivity.class);
                    startActivity(OpenMyActivity);


            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
