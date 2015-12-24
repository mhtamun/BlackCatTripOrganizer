package com.blackcat.triporganizer.tracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.R.id;
import com.blackcat.triporganizer.R.layout;
import com.blackcat.triporganizer.tracker.dao.CheckPlaceDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class PlaceTrackerCheckOutActivity extends Activity {

    EditText EntrySpendings;

    ImageButton DoneButtonForCheckOut;

    int SpendingsINT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_tracker_check_out);

		/*EntrySpendings = (EditText) findViewById(R.id.editText_PlaceCheckOut);
		EntrySpendings.setInputType(InputType.TYPE_CLASS_NUMBER);*/

        DoneButtonForCheckOut = (ImageButton) findViewById(R.id.imageButton_PlaceCheckOut_Done);
        DoneButtonForCheckOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SpendingsINT = EnterExpensesActivity.SpendingOnCheckOut;

                Calendar c = Calendar.getInstance();

                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd.MMM.yyyy - HH:mm");

                String CheckOutTime = df.format(c.getTime());

                CheckPlaceDAO GetCheckPlace = new CheckPlaceDAO(PlaceTrackerCheckOutActivity.this);

                GetCheckPlace.open();

                long GetNewEntryID = GetCheckPlace.GetMaxIdFromCheckDAO();

                GetCheckPlace.UpdateCheckOut(GetNewEntryID, SpendingsINT, CheckOutTime);

                GetCheckPlace.close();


                Intent OpenTrackerMenu = new Intent(PlaceTrackerCheckOutActivity.this, TrackerMenuActivity.class);

                startActivity(OpenTrackerMenu);

            }
        });
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

                    Intent OpenMyActivity = new Intent(PlaceTrackerCheckOutActivity.this, TrackerMenuActivity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
