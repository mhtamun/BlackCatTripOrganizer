package com.blackcat.triporganizer.planner;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.planner.dao.PlannerDAO;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddAditionalPreferencesActivity extends Activity {

    CheckBox BnB, Camp, HnM, CnC, RnL, Speciality, AnC, LocalFood, NightLife, EnA, OutdoorFun, Shopping;

    ImageButton CretenewTrip;

    String PlacesToStay = "", ThingsToDo = "";

    View backgroundimage;
    Drawable background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aditional_preferences);


        backgroundimage = (View) findViewById(R.id.LinearLayout_AddPreferences);
        background = backgroundimage.getBackground();
        background.setAlpha(70);

        BnB = (CheckBox) findViewById(R.id.checkBox_AddPreference_BnB);
        Camp = (CheckBox) findViewById(R.id.checkBox_AddPreference_Camp);
        HnM = (CheckBox) findViewById(R.id.checkBox_AddPreference_HnM);
        CnC = (CheckBox) findViewById(R.id.checkBox_AddPreference_CnC);
        RnL = (CheckBox) findViewById(R.id.checkBox_AddPreference_RnL);
        Speciality = (CheckBox) findViewById(R.id.checkBox_AddPreference_Specialty);

        AnC = (CheckBox) findViewById(R.id.checkBox_AddPreference_AnC);
        LocalFood = (CheckBox) findViewById(R.id.checkBox_AddPreference_LocalFood);
        NightLife = (CheckBox) findViewById(R.id.checkBox_AddPreference_NightLife);
        EnA = (CheckBox) findViewById(R.id.checkBox_AddPreference_EnA);
        OutdoorFun = (CheckBox) findViewById(R.id.checkBox_AddPreference_Outdoorfun);
        Shopping = (CheckBox) findViewById(R.id.checkBox_AddPreference_Shopping);

        CretenewTrip = (ImageButton) findViewById(R.id.imageButton_AddPreference_CreateNEw);
        CretenewTrip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (BnB.isChecked()) {
                    PlacesToStay = PlacesToStay + "Bed & Breakfasts\n";
                }

                if (Camp.isChecked()) {
                    PlacesToStay = PlacesToStay + "Campgrounds\n";
                }

                if (HnM.isChecked()) {
                    PlacesToStay = PlacesToStay + "Hotels & Motels\n";
                }

                if (CnC.isChecked()) {
                    PlacesToStay = PlacesToStay + "Cabins & Cottages\n";
                }

                if (RnL.isChecked()) {
                    PlacesToStay = PlacesToStay + "Resorts & Lodges\n";
                }

                if (Speciality.isChecked()) {
                    PlacesToStay = PlacesToStay + "Speciality";
                }

                if (AnC.isChecked()) {
                    ThingsToDo = ThingsToDo + "Arts and Culture\n";
                }

                if (LocalFood.isChecked()) {
                    ThingsToDo = ThingsToDo + "Local Food\n";
                }

                if (NightLife.isChecked()) {
                    ThingsToDo = ThingsToDo + "Night Life\n";
                }

                if (EnA.isChecked()) {
                    ThingsToDo = ThingsToDo + "Entertainment & Attractions\n";
                }

                if (OutdoorFun.isChecked()) {
                    ThingsToDo = ThingsToDo + "Outdoor Fun\n";
                }

                if (Shopping.isChecked()) {
                    ThingsToDo = ThingsToDo + "Shopping";
                }

                Boolean didItWork = true;
                try {

                    PlannerDAO EntryInfo = new PlannerDAO(AddAditionalPreferencesActivity.this);
                    EntryInfo.open();
                    EntryInfo.CreatePlannerEntry(AddNewTripActivity.PlannerTripName,
                            AddNewTripActivity.PlannerDestinationname,
                            AddNewTripActivity.PlannerStartdate,
                            AddNewTripActivity.PlannerTravelBy,
                            PlacesToStay,
                            ThingsToDo);
                    EntryInfo.close();

                } catch (Exception e) {
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(AddAditionalPreferencesActivity.this);
                    d.setTitle("Require Field Not Fullfilled");
                    TextView tv = new TextView(AddAditionalPreferencesActivity.this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {

                    if (didItWork) {
                        ProgressDialog d = new ProgressDialog(AddAditionalPreferencesActivity.this);
                        ProgressDialog.show(AddAditionalPreferencesActivity.this, "New Trip Plan Creating!", "Please Wait...");

                        Intent OpenPLannerList = new Intent(AddAditionalPreferencesActivity.this, PlannerListwithCreateNewActivity.class);
                        startActivity(OpenPLannerList);
                    }

                }

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

                    Intent BacktoAddNew = new Intent(AddAditionalPreferencesActivity.this, AddNewTripActivity.class);
                    startActivity(BacktoAddNew);

            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {


    }
    */
}
