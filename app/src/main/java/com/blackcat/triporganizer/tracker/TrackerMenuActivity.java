package com.blackcat.triporganizer.tracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blackcat.triporganizer.MainMenuActivity;
import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.history.HistoryListActivity;
import com.blackcat.triporganizer.history.HistoryViewerActivity;
import com.blackcat.triporganizer.tracker.dao.CheckPlaceDAO;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TrackerMenuActivity extends Activity implements OnClickListener {

    ImageButton budget, transit, place, showBudget, closeProfile;

    long getlastID;

    TrackerDAO gettingInfo;

    ProgressDialog d;

    public static int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_menu);

        budget = (ImageButton) findViewById(R.id.imageButton_budget);
        budget.setOnClickListener(this);

        //transit = (ImageButton) findViewById(R.id.imageButton_transit);
        //transit.setOnClickListener(this);

        place = (ImageButton) findViewById(R.id.imageButton_place);
        place.setOnClickListener(this);

        showBudget = (ImageButton) findViewById(R.id.imageButton_showBudget);
        showBudget.setOnClickListener(this);

        closeProfile = (ImageButton) findViewById(R.id.imageButton_closeProfile);
        closeProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageButton_budget:

                Intent OpenBudgetTracker = new Intent(TrackerMenuActivity.this, BudgetTrackerActivity.class);
                startActivity(OpenBudgetTracker);

                break;
            //case R.id.imageButton_transit:

            //break;
            case R.id.imageButton_place:

                CheckPlaceDAO gettingInfoFromCheckDAO = new CheckPlaceDAO(this);

                gettingInfoFromCheckDAO.open();

                long GetNewEntryID = gettingInfoFromCheckDAO.GetMaxIdFromCheckDAO();

                String GetCheckOut = gettingInfoFromCheckDAO.getCheckOutData(GetNewEntryID);

                String forCheck = GetCheckOut + " IfDataBaseEmpty";

                if (forCheck.contentEquals("Check-In Active IfDataBaseEmpty")) {

                    Intent OpenPlaceTracker = new Intent(TrackerMenuActivity.this, PlaceTrackerCheckOutActivity.class);
                    startActivity(OpenPlaceTracker);


                } else {

                    check = 1;
                    EnterExpensesActivity.SpendingOnCheckOut = 0;

                    Intent OpenPlaceTracker = new Intent(TrackerMenuActivity.this, PlaceTrackerCheckInActivity.class);
                    startActivity(OpenPlaceTracker);

                }
                break;

            case R.id.imageButton_showBudget:

                HistoryViewerActivity.checkfrom = 2;

                gettingInfo = new TrackerDAO(TrackerMenuActivity.this);

                gettingInfo.open();

                getlastID = gettingInfo.getMaxID();

                HistoryListActivity.TripID = getlastID;

                Intent OpenBudgetShow = new Intent(TrackerMenuActivity.this, HistoryViewerActivity.class);
                startActivity(OpenBudgetShow);

                break;

            case R.id.imageButton_closeProfile:

                gettingInfo = new TrackerDAO(TrackerMenuActivity.this);

                gettingInfo.open();

                getlastID = gettingInfo.getMaxID();

                String getProfileName = gettingInfo.getLatestProfileName(getlastID);

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(TrackerMenuActivity.this);

                alertDialog2.setTitle("Are you sure want to close profile?"); // Setting Dialog Title

                alertDialog2.setMessage(getProfileName + " is running, are you want to close this Profile? Click yes!"); // Setting Dialog Message

                alertDialog2.setIcon(R.drawable.alerticon); // Setting Icon to Dialog

                // Setting "Yes" Button
                alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        Calendar c = Calendar.getInstance();
                        System.out.println("Current time => " + c.getTime());
                        SimpleDateFormat df = new SimpleDateFormat("dd.MMM.yyyy HH:mm");
                        String formattedDate = df.format(c.getTime());

                        Boolean didItWork = true;

                        String arrivalDate = formattedDate;

                        try {

                            gettingInfo.updateArrivalDateEntry(getlastID, arrivalDate);

                            gettingInfo.close();

                        } catch (Exception e) {
                            didItWork = false;
                            String error = e.toString();
                            Dialog d = new Dialog(TrackerMenuActivity.this);
                            d.setTitle("Not Updated");
                            TextView tv = new TextView(TrackerMenuActivity.this);
                            tv.setText(error);
                            d.setContentView(tv);
                            d.show();
                        } finally {
                            if (didItWork) {

                                d = new ProgressDialog(TrackerMenuActivity.this);
                                ProgressDialog.show(TrackerMenuActivity.this, "Profile Closing", "Please Wait...");

                                Intent openMainActivity = new Intent(TrackerMenuActivity.this, MainMenuActivity.class);
                                startActivity(openMainActivity);
                            }
                        }
                    }
                });

                // Setting "NO" Button
                alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog2.show(); // Showing Alert Dialog

                break;

        }

    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:

                    Intent OpenMyActivity = new Intent(TrackerMenuActivity.this, MainMenuActivity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
