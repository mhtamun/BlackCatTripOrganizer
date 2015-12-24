package com.blackcat.triporganizer;

import com.blackcat.triporganizer.history.HistoryListActivity;
import com.blackcat.triporganizer.planner.PlannerListwithCreateNewActivity;
import com.blackcat.triporganizer.tracker.CreateProfile_1Activity;
import com.blackcat.triporganizer.tracker.TrackerMenuActivity;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainMenuActivity extends Activity implements OnClickListener {

    ImageButton Tracker, History, Planner, Settings, AboutUs;

    TrackerDAO getArivalDate;

    long getlastID;
    String getAD;
    String Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Tracker = (ImageButton) findViewById(R.id.imageButton_triptrackerbutton);
        Tracker.setOnClickListener(this);

        Animation TrackerAnime = AnimationUtils.loadAnimation(this, R.anim.righttolefttranslate);
        Tracker.startAnimation(TrackerAnime);

        History = (ImageButton) findViewById(R.id.imageButton_triphistorybutton);
        History.setOnClickListener(this);

        Animation HistoryAnime = AnimationUtils.loadAnimation(this, R.anim.lefttorighttranslate);
        History.startAnimation(HistoryAnime);

        Planner = (ImageButton) findViewById(R.id.imageButton_tripplannerbutton);
        Planner.setOnClickListener(this);

        Animation PlannerAnime = AnimationUtils.loadAnimation(this, R.anim.righttolefttranslate);
        Planner.startAnimation(PlannerAnime);

        //Settings = (ImageButton) findViewById(R.id.imageButton_settings);
        //Settings.setOnClickListener(this);

        //Animation SettingAnime = AnimationUtils.loadAnimation(this, R.anim.lefttorighttranslate);
        //Settings.startAnimation(SettingAnime);

        AboutUs = (ImageButton) findViewById(R.id.imageButton_aboutus);
        AboutUs.setOnClickListener(this);

        Animation AboutUsAnime = AnimationUtils.loadAnimation(this, R.anim.frombottomtranslate);
        AboutUs.startAnimation(AboutUsAnime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton_triptrackerbutton:

                checkForNowGoingTour();

                break;

            case R.id.imageButton_triphistorybutton:

                Intent OpenTripHistory = new Intent(MainMenuActivity.this, HistoryListActivity.class);
                startActivity(OpenTripHistory);

                break;
            case R.id.imageButton_tripplannerbutton:

                Intent OpenTripPLanner = new Intent(MainMenuActivity.this, PlannerListwithCreateNewActivity.class);
                startActivity(OpenTripPLanner);

                break;
            //case R.id.imageButton_settings:

            //break;
            case R.id.imageButton_aboutus:

                Intent OpenAboutUs = new Intent(MainMenuActivity.this, AboutUsActivity.class);
                startActivity(OpenAboutUs);

                break;
        }

    }

    private void checkForNowGoingTour() {

        try {
            getArivalDate = new TrackerDAO(MainMenuActivity.this);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        getArivalDate.open();
        getlastID = getArivalDate.getMaxID();
        getAD = getArivalDate.getArivaldateforCheck(getlastID);
        getArivalDate.close();

        Check = getAD + " ifDatabaseIsEmpty"; //When Database is empty a value [ ifDatabaseIsEmpty] is needed to check contentEquals

        if (Check.contentEquals("Profile on Running ifDatabaseIsEmpty")) {

            Intent OpenTrackerActivity = new Intent(MainMenuActivity.this, TrackerMenuActivity.class);
            startActivity(OpenTrackerActivity);

        } else {

            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MainMenuActivity.this);

            alertDialog2.setTitle("Profile not created yet!"); // Setting Dialog Title

            alertDialog2.setMessage("No profile is running, are you want to create New Profile?"); // Setting Dialog Message

            alertDialog2.setIcon(R.drawable.alerticon); // Setting Icon to Dialog

            // Setting "Yes" Button
            alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent OpenCreateActivity = new Intent(MainMenuActivity.this, CreateProfile_1Activity.class);
                    startActivity(OpenCreateActivity);

                }
            });

            // Setting "NO" Button
            alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog2.show(); // Showing Alert Dialog
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

        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:

                    AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(MainMenuActivity.this);

                    alertDialog3.setTitle("Are you Sure?"); // Setting Dialog Title

                    alertDialog3.setMessage("Click yes to exit!"); // Setting Dialog Message

                    alertDialog3.setIcon(R.drawable.alerticon); // Setting Icon to Dialog

                    // Setting "Yes" Button
                    alertDialog3.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    // Setting "NO" Button
                    alertDialog3.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    alertDialog3.show(); // Showing Alert Dialog
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
