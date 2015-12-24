package com.blackcat.triporganizer.tracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateProfile_2Activity extends Activity implements OnClickListener {

    View backgroundimage;
    Drawable background;

    ImageButton getdateButton;
    ImageButton createNewButton, backButton;

    EditText editText_cityactivity,
            editText_startingdate,
            editText_startingtime,
            editText_budget;

    String TripName, TripReason, cityName = null, SD, AD, budget = null;
    int RemainBudget, HE, TE, FE, EE, SE, OE;

    ProgressDialog d;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_2);

        backgroundimage = (View) findViewById(R.id.linearlayout_creteProfile2);
        background = backgroundimage.getBackground();
        background.setAlpha(70);


        editText_cityactivity = (EditText) findViewById(R.id.editText_cityactivity);
        editText_cityactivity.setInputType(InputType.TYPE_CLASS_TEXT);

        editText_startingdate = (EditText) findViewById(R.id.editText_startingdate);
        editText_startingdate.setInputType(InputType.TYPE_CLASS_DATETIME);
        editText_startingdate.setOnClickListener(this);

        editText_startingtime = (EditText) findViewById(R.id.editText_startingtime);
        editText_startingtime.setInputType(InputType.TYPE_CLASS_DATETIME);
        editText_startingtime.setOnClickListener(this);

        editText_budget = (EditText) findViewById(R.id.editText_budget);
        editText_budget.setInputType(InputType.TYPE_CLASS_NUMBER);

        getdateButton = (ImageButton) findViewById(R.id.button_getdate);
        getdateButton.setOnClickListener(this);

        createNewButton = (ImageButton) findViewById(R.id.imageButton_createNew);
        createNewButton.setOnClickListener(this);


        Bundle gotBasket_tripNreason = getIntent().getExtras();
        TripName = gotBasket_tripNreason.getString("key_name");
        TripReason = gotBasket_tripNreason.getString("key_reason");
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {

            case R.id.editText_startingdate:

                editText_startingdate.setText(" ");
                editText_startingtime.setText(" ");

                break;

            case R.id.button_getdate:

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);
                SimpleDateFormat df = new SimpleDateFormat("dd. M. yyyy");
                SimpleDateFormat tf = new SimpleDateFormat("HH : mm");
                String formattedDate = df.format(c.getTime());
                String formattedTime = tf.format(c.getTime());

                editText_startingdate.setText(formattedDate, TextView.BufferType.EDITABLE);
                editText_startingtime.setText(formattedTime, TextView.BufferType.EDITABLE);

                showDialog(DATE_DIALOG_ID);

                break;

            case R.id.imageButton_createNew:

                cityName = editText_cityactivity.getText().toString();

                String Date = editText_startingdate.getText().toString();
                String Time = editText_startingtime.getText().toString();
                SD = Date + " " + Time;
                AD = "Profile on Running";
                budget = editText_budget.getText().toString();
                HE = TE = FE = EE = SE = OE = 0;


                if (cityName.isEmpty() || budget.isEmpty() || SD.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Required Field Not Fullfilled", Toast.LENGTH_LONG).show();

                    break;

                } else {

                    RemainBudget = Integer.parseInt(budget);

                    CreateEntryMethod();


                }

                break;


        }

    }

    private void CreateEntryMethod() {

        boolean didItWork = true;

        try {

            TrackerDAO entry = new TrackerDAO(CreateProfile_2Activity.this);
            entry.open();
            entry.createEntry(TripName, TripReason, cityName, SD, AD, budget, HE, TE, FE, EE, SE, OE, RemainBudget);
            entry.close();

        } catch (Exception e) {
            didItWork = false;
            String error = e.toString();
            Dialog d = new Dialog(this);
            d.setTitle("Require Field Not Fullfilled");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        } finally {

            if (didItWork) {

                d = new ProgressDialog(this);
                ProgressDialog.show(CreateProfile_2Activity.this, "New Profile Creating!", "Please Wait...");

                Intent openMainActivity_CNA2 = new Intent(CreateProfile_2Activity.this, TrackerMenuActivity.class);
                startActivity(openMainActivity_CNA2);
            }

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
            editText_startingdate.setText(new StringBuilder().append(day)
                    .append(". ").append(month + 1).append(". ").append(year)
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
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:

                    Intent OpenMyActivity = new Intent(CreateProfile_2Activity.this, CreateProfile_1Activity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
