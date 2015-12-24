package com.blackcat.triporganizer.planner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.planner.dao.PlannerDAO;
import com.blackcat.triporganizer.tracker.CreateProfile_2Activity;

public class PlannerListViewerActivity extends Activity implements OnClickListener {

    PlannerDAO DBHelper = new PlannerDAO(PlannerListViewerActivity.this);

    TextView TripName, TripDestination, StartingDate, TravelBy, PlacesToStay, ThingsToDo, Status;

    ImageButton ActiveInactive, CreateTracker, Delete;

    public static String name = null;

    String city = null, startingdate = null, travelBy = null, placestoStay = null, thingstoDo = null, tripStatus = null;

    public static String EmailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner_list_viewer);

        TripName = (TextView) findViewById(R.id.textView_Planner_TripName);
        TripDestination = (TextView) findViewById(R.id.textView_PlannerViewer_CityName);
        StartingDate = (TextView) findViewById(R.id.textView_PlannerViewer_StartingDate);
        TravelBy = (TextView) findViewById(R.id.textView_PlannerViewer_TravleBy);
        PlacesToStay = (TextView) findViewById(R.id.textView_PlannerViewer_PlacesToStay);
        ThingsToDo = (TextView) findViewById(R.id.textView_PlannerViewer_ThingsToDo);

        CreateTracker = (ImageButton) findViewById(R.id.imageButton_PlannerViwer_CreateTracker);
        CreateTracker.setOnClickListener(this);

        Delete = (ImageButton) findViewById(R.id.imageButton_Planner_DeleteButton);
        Delete.setOnClickListener(this);

        DisplayInfoMethod(PlannerListwithCreateNewActivity.PlannerID);

        EmailMessage = "Hi, I'm going on a trip to " + city + "on " + startingdate + "." + "\n" +
                "Most Probably I'm going to travel by " + travelBy + "." + "\n" +
                "I am planning to stay at " + placestoStay + " and hopefully do " + thingstoDo + " all these." +
                "If you want to join with me, respond ASAP \n" +
                "Yours -  \n" + " ";

        TripName.setText(name);
        TripDestination.setText(city);
        StartingDate.setText(startingdate);
        TravelBy.setText(travelBy);
        PlacesToStay.setText(placestoStay);
        ThingsToDo.setText(thingstoDo);

    }

    private void DisplayInfoMethod(long id) {

        DBHelper.open();

        Cursor cursor = DBHelper.getPlannerInfo(id);

        if (cursor.moveToFirst()) {
            name = cursor.getString(1);
            city = cursor.getString(2);
            startingdate = cursor.getString(3);
            travelBy = cursor.getString(4);
            placestoStay = cursor.getString(5);
            thingstoDo = cursor.getString(6);
            tripStatus = cursor.getString(7);
        }

        DBHelper.close();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageButton_PlannerViwer_CreateTracker:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlannerListViewerActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Share!");

                // Setting Dialog Message
                alertDialog.setMessage("Share via Email or Facebook");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.deleteicon);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("Via Email", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent OpenEmailActivity = new Intent(PlannerListViewerActivity.this, ViaEmailShareActivity.class);
                        startActivity(OpenEmailActivity);
                    }
                });

                alertDialog.show();


                break;

            case R.id.imageButton_Planner_DeleteButton:

				/*if (tripStatus.contentEquals("ACTIVE")){
		    		
		    		Toast.makeText(getApplicationContext(), "This Profile Is Active, Cannot delete right now.", Toast.LENGTH_LONG).show();
		    		
		    		
		    	}else if (tripStatus.contentEquals("INACTIVE")){*/

                AlertDialog.Builder alertDialogDel = new AlertDialog.Builder(PlannerListViewerActivity.this);

                // Setting Dialog Title
                alertDialogDel.setTitle("Confirm Delete...");

                // Setting Dialog Message
                alertDialogDel.setMessage("Are you sure you want delete this Profile? Click Yes!");

                // Setting Icon to Dialog
                alertDialogDel.setIcon(R.drawable.deleteicon);

                // Setting Positive "Yes" Button
                alertDialogDel.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        DBHelper.open();
                        DBHelper.deletePlannerEntry(PlannerListwithCreateNewActivity.PlannerID);
                        DBHelper.close();

                        ProgressDialog d = new ProgressDialog(PlannerListViewerActivity.this);
                        ProgressDialog.show(PlannerListViewerActivity.this, "Trip Plan Deleting!", "Please Wait...");

                        Intent sameActivity = new Intent(PlannerListViewerActivity.this, PlannerListwithCreateNewActivity.class);
                        startActivity(sameActivity);

                    }
                });
                // Setting Negative "NO" Button
                alertDialogDel.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialogDel.show();

                //}

                break;
        }
    }

	/*
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	if(event.getAction() == KeyEvent.ACTION_DOWN){
		switch(keyCode){
	        
	    	case KeyEvent.KEYCODE_BACK:
	    		Intent BacktoAddNew = new Intent(PlannerListViewerActivity.this, PlannerListwithCreateNewActivity.class);
		        startActivity(BacktoAddNew);
	
	       }
	
		}
	
	return super.onKeyDown(keyCode, event);
	}
	*/
}
