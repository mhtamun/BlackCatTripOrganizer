package com.blackcat.triporganizer.tracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.R.layout;
import com.blackcat.triporganizer.tracker.dao.CheckPlaceDAO;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PlaceTrackerCheckInActivity extends Activity implements OnClickListener {

    EditText PlaceName_EditText;

    ImageButton DoneButton, EnterMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_tracker_check_in);

        PlaceName_EditText = (EditText) findViewById(R.id.editText_PlaceTracker);
        PlaceName_EditText.setInputType(InputType.TYPE_CLASS_TEXT);

        DoneButton = (ImageButton) findViewById(R.id.imageButton_PlaceTracker_Done);
        DoneButton.setOnClickListener(this);

        //EnterMap = (ImageButton) findViewById(R.id.imageButton_PlaceTracker_EnterGoogleMap);
        //EnterMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageButton_PlaceTracker_Done:

                TrackerDAO gettingInfo = new TrackerDAO(PlaceTrackerCheckInActivity.this);

                gettingInfo.open();

                long getlastID = gettingInfo.getMaxID();

                String LatestProfileName = gettingInfo.getLatestProfileName(getlastID);

                gettingInfo.close();

                String PlaceName = PlaceName_EditText.getText().toString();

                Calendar c = Calendar.getInstance();

                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd.MMM.yyyy - HH:mm");

                String CheckInTime = df.format(c.getTime());

                String CheckOutTime = "Check-In Active";

                String Cetegory = "Place";

                int Cost = 0;

                CheckPlaceDAO GetCheckPlace = new CheckPlaceDAO(PlaceTrackerCheckInActivity.this);

                GetCheckPlace.open();

                GetCheckPlace.CreateCheckIn(PlaceName, CheckInTime, CheckOutTime, Cetegory, LatestProfileName, Cost);

                GetCheckPlace.close();

                if (PlaceName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Required Field Not Fullfilled", Toast.LENGTH_LONG).show();

                } else {

                    Intent OpenTrackerMenu = new Intent(PlaceTrackerCheckInActivity.this, TrackerMenuActivity.class);

                    startActivity(OpenTrackerMenu);


                }


                break;

            //case R.id.imageButton_PlaceTracker_EnterGoogleMap:

            //break;
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

        finish();
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

                    Intent OpenMyActivity = new Intent(PlaceTrackerCheckInActivity.this, TrackerMenuActivity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
