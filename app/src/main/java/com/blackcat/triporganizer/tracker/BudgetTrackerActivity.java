package com.blackcat.triporganizer.tracker;

import com.blackcat.triporganizer.MainMenuActivity;
import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class BudgetTrackerActivity extends Activity implements OnClickListener {

    ImageButton Rents, Fares, Costs, Spendings, Expenditure, Expenses;

    public static int checkfortracking = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker);

        Rents = (ImageButton) findViewById(R.id.imageButton_accomodationrentsbutton);
        Rents.setOnClickListener(this);

        Fares = (ImageButton) findViewById(R.id.imageButton_tranportationfaresbutton);
        Fares.setOnClickListener(this);

        Costs = (ImageButton) findViewById(R.id.imageButton_foodcostsbutton);
        Costs.setOnClickListener(this);

        Spendings = (ImageButton) findViewById(R.id.imageButton_entertainmentspendingsbuttons);
        Spendings.setOnClickListener(this);

        Expenditure = (ImageButton) findViewById(R.id.imageButton_shoppingexpenditurebutton);
        Expenditure.setOnClickListener(this);

        Expenses = (ImageButton) findViewById(R.id.imageButton_otherexpensesbutton);
        Expenses.setOnClickListener(this);

        //BudgetCheckAlert();
    }

    private void BudgetCheckAlert() {


        TrackerDAO gettingInfo = new TrackerDAO(BudgetTrackerActivity.this);

        gettingInfo.open();

        long getlastID = gettingInfo.getMaxID();

        int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

        String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

        int getEsBudgetINT = Integer.parseInt(getEsBudget);

        int Check_Budget = (getEsBudgetINT * 10) / 100;

        if (Check_Budget < prev_remainbudget) {

            AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(BudgetTrackerActivity.this);

            alertDialog3.setTitle("Bugdet Alert!"); // Setting Dialog Title

            alertDialog3.setMessage("You have almost reached your budget!" + "\n" +
                    "Your Estimated Budget: " + getEsBudget + "\n" +
                    "Your Remaining Budget: " + prev_remainbudget); // Setting Dialog Message

            alertDialog3.setIcon(R.drawable.alerticon); // Setting Icon to Dialog

            // Setting "Yes" Button
            alertDialog3.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();

                }
            });

            alertDialog3.show(); // Showing Alert Dialog
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageButton_accomodationrentsbutton:

                checkfortracking = 1;

                intentMethod();

                break;
            case R.id.imageButton_tranportationfaresbutton:

                checkfortracking = 2;

                intentMethod();

                break;
            case R.id.imageButton_foodcostsbutton:

                checkfortracking = 3;

                intentMethod();

                break;
            case R.id.imageButton_entertainmentspendingsbuttons:

                checkfortracking = 4;

                intentMethod();

                break;
            case R.id.imageButton_shoppingexpenditurebutton:

                checkfortracking = 5;

                intentMethod();

                break;
            case R.id.imageButton_otherexpensesbutton:

                checkfortracking = 6;

                intentMethod();

                break;

        }

    }

    private void intentMethod() {

        Intent OpenBudgetExpenses = new Intent(BudgetTrackerActivity.this, EnterExpensesActivity.class);
        startActivity(OpenBudgetExpenses);

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

        //pager.setOffscreenPageLimit(4);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        finish();
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

                    Intent OpenMyActivity = new Intent(BudgetTrackerActivity.this, TrackerMenuActivity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
