package com.blackcat.triporganizer.planner;

import java.util.Calendar;

import com.blackcat.triporganizer.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewTripActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    TextView TextView_StartDate;

    EditText tripName, destinationName, StartDate;

    Spinner travelBySpinner;

    ImageButton addNewpref;

    View backgroundimage;
    Drawable background;

    public static String PlannerTripName;
    public static String PlannerDestinationname;
    public static String PlannerStartdate;
    public static String PlannerTravelBy;

    String TravelBy;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);

        backgroundimage = (View) findViewById(R.id.LinearLayout_AddPlannerBackground);
        background = backgroundimage.getBackground();
        background.setAlpha(70);

        tripName = (EditText) findViewById(R.id.editText_AddNewTrip_TripName);
        tripName.setInputType(InputType.TYPE_CLASS_TEXT);

        destinationName = (EditText) findViewById(R.id.editText_AddNewTrip_Destination);
        destinationName.setInputType(InputType.TYPE_CLASS_TEXT);

        StartDate = (EditText) findViewById(R.id.editText_AddNewTrip_StartDate);
        StartDate.setInputType(InputType.TYPE_CLASS_DATETIME);

        TextView_StartDate = (TextView) findViewById(R.id.textView_StartDate);
        TextView_StartDate.setOnClickListener(this);

        travelBySpinner = (Spinner) findViewById(R.id.spinner_AddNewTrip_TravelBy);
        travelBySpinner.setOnItemSelectedListener(this);


        addNewpref = (ImageButton) findViewById(R.id.imageButton_AddNewTrip_AddButton);
        addNewpref.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long item) {

        if (item == 1) {

            TravelBy = "Aeroplane";

        }

        if (item == 2) {

            TravelBy = "Bus";

        }

        if (item == 3) {

            TravelBy = "Car";

        }

        if (item == 4) {

            TravelBy = "Rail";

        }

        if (item == 5) {

            TravelBy = "Ship";

        }

        if (item == 6) {

            TravelBy = "Other Transportation";

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

	/*@Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
		
		switch (arg1) {
		
			case R.id.radio_AddNewTrip_Plane:
				
				TravelBy = "Plane";
				
				break;
				
			case R.id.radio_AddNewTrip_Bus:
				
				TravelBy = "Bus";
				
				break;
				
			case R.id.radio_AddNewTrip_Rail:
				
				TravelBy = "Rail";
				
				break;
				
			case R.id.radio_AddNewTrip_Ship:
				
				TravelBy = "Ship";
				
				break;
				
			case R.id.radio__AddNewTrip_OtherTransport:
				
				TravelBy = "Other";
				
				break;
				
		}
		
	}*/


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.textView_StartDate:

                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                showDialog(DATE_DIALOG_ID);

                break;

            case R.id.imageButton_AddNewTrip_AddButton:

                if (tripName.equals("") || destinationName.equals("") || StartDate.equals("")) {

                    Toast.makeText(AddNewTripActivity.this, "Please enter all the field", Toast.LENGTH_SHORT).show();

                } else {

                    PlannerTripName = tripName.getText().toString();
                    PlannerDestinationname = destinationName.getText().toString();
                    PlannerStartdate = StartDate.getText().toString();
                    PlannerTravelBy = TravelBy;

                    Intent OpenAddPreference = new Intent(AddNewTripActivity.this, AddAditionalPreferencesActivity.class);
                    startActivity(OpenAddPreference);
                }

                break;

        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            StartDate.setText(new StringBuilder().append(day)
                    .append("/").append(month + 1).append("/").append(year)
                    .append(" "));


        }
    };

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
	    if(event.getAction() == KeyEvent.ACTION_DOWN){
	        switch(keyCode){
	        
	        case KeyEvent.KEYCODE_BACK:
	        	Intent BacktoAddNew = new Intent(AddNewTripActivity.this, PlannerListwithCreateNewActivity.class);
	        	startActivity(BacktoAddNew);
	
	        }
	
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
		
		
	}
	*/
}
