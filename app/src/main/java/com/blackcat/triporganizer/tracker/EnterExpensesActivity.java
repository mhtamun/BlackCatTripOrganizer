package com.blackcat.triporganizer.tracker;

import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EnterExpensesActivity extends Activity {

    EditText EnterValue;
    ImageButton doneButton;

    int CheckRemainBudget;

    public static int SpendingOnCheckOut;

    int getEsBudgetINT;
    int CheckValueInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_expenses);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.background_EnterExpenses);

        if (BudgetTrackerActivity.checkfortracking == 1) {

            linearLayout.setBackgroundResource(R.drawable.accomodationbackground);

        } else if (BudgetTrackerActivity.checkfortracking == 2) {

            linearLayout.setBackgroundResource(R.drawable.transportbackground);

        } else if (BudgetTrackerActivity.checkfortracking == 3) {

            linearLayout.setBackgroundResource(R.drawable.foodbackground);

        } else if (BudgetTrackerActivity.checkfortracking == 4) {

            linearLayout.setBackgroundResource(R.drawable.entertainmentbackground);

        } else if (BudgetTrackerActivity.checkfortracking == 5) {

            linearLayout.setBackgroundResource(R.drawable.shoppingbackground);

        } else if (BudgetTrackerActivity.checkfortracking == 6) {

            linearLayout.setBackgroundResource(R.drawable.otherbackground);

        }


        EnterValue = (EditText) findViewById(R.id.editText_EnterExpenses);
        EnterValue.setInputType(InputType.TYPE_CLASS_NUMBER);

        doneButton = (ImageButton) findViewById(R.id.imageButton_EnterExpenses);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String CheckValueforEmptyET = EnterValue.getText().toString();


                if (TrackerMenuActivity.check == 1) {

                    String CheckValue = EnterValue.getText().toString();

                    CheckValueInt = Integer.parseInt(CheckValue);

                    SpendingOnCheckOut = SpendingOnCheckOut + CheckValueInt;
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (CheckValueforEmptyET.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Required Field Not Fullfilled", Toast.LENGTH_LONG).show();


                    } else {


                        if (BudgetTrackerActivity.checkfortracking == 1) {

                            boolean didItWork = true;

                            TrackerDAO gettingInfo = new TrackerDAO(EnterExpensesActivity.this);

                            gettingInfo.open();

                            long getlastID = gettingInfo.getMaxID();

                            int prev_rent = gettingInfo.getAccommodationPrev(getlastID);


                            String getAmountString = EnterValue.getText().toString();

                            int getAmountInt = Integer.parseInt(getAmountString);

                            prev_rent = prev_rent + getAmountInt;

                            int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

                            CheckRemainBudget = prev_remainbudget;

                            prev_remainbudget = prev_remainbudget - getAmountInt;

                            String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

                            getEsBudgetINT = Integer.parseInt(getEsBudget);

                            if (CheckRemainBudget < getAmountInt) {

                                AlertForRemainBudget();

                            } else {

                                try {

                                    gettingInfo.updatePrevRemainBudget(getlastID, prev_remainbudget);

                                    gettingInfo.updateAccommodationEntry(getlastID, prev_rent);

                                    gettingInfo.close();

                                } catch (Exception e) {
                                    didItWork = false;
                                    String error = e.toString();
                                    Dialog d = new Dialog(EnterExpensesActivity.this);
                                    d.setTitle("Dang it!");
                                    TextView tv = new TextView(EnterExpensesActivity.this);
                                    tv.setText(error);
                                    d.setContentView(tv);
                                    d.show();
                                } finally {
                                    if (didItWork) {

                                        Intent openActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                                        startActivity(openActivity);

                                    }


                                }


                            }


                        } else if (BudgetTrackerActivity.checkfortracking == 2) {

                            boolean didItWork = true;

                            TrackerDAO gettingInfo = new TrackerDAO(EnterExpensesActivity.this);

                            gettingInfo.open();

                            long getlastID = gettingInfo.getMaxID();

                            int prev_transport = gettingInfo.getTransportPrev(getlastID);

                            String getAmountString = EnterValue.getText().toString();

                            int getAmountInt = Integer.parseInt(getAmountString);

                            prev_transport = prev_transport + getAmountInt;

                            int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

                            CheckRemainBudget = prev_remainbudget;

                            prev_remainbudget = prev_remainbudget - getAmountInt;


                            String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

                            getEsBudgetINT = Integer.parseInt(getEsBudget);

                            if (CheckRemainBudget < getAmountInt) {

                                AlertForRemainBudget();

                            } else {

                                try {

                                    gettingInfo.updatePrevRemainBudget(getlastID, prev_remainbudget);

                                    gettingInfo.updatetransportEntry(getlastID, prev_transport);

                                    gettingInfo.close();

                                } catch (Exception e) {
                                    didItWork = false;
                                    String error = e.toString();
                                    Dialog d = new Dialog(EnterExpensesActivity.this);
                                    d.setTitle("Not Updated");
                                    TextView tv = new TextView(EnterExpensesActivity.this);
                                    tv.setText(error);
                                    d.setContentView(tv);
                                    d.show();
                                } finally {

                                    if (didItWork) {
                                        Intent openonGoingActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                                        startActivity(openonGoingActivity);
                                    }

                                }

                            }

                        } else if (BudgetTrackerActivity.checkfortracking == 3) {

                            boolean didItWork = true;

                            TrackerDAO gettingInfo = new TrackerDAO(EnterExpensesActivity.this);

                            gettingInfo.open();

                            long getlastID = gettingInfo.getMaxID();

                            int prev_food = gettingInfo.getFoodPrev(getlastID);

                            String getAmountString = EnterValue.getText().toString();

                            int getAmountInt = Integer.parseInt(getAmountString);

                            prev_food = prev_food + getAmountInt;

                            int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

                            CheckRemainBudget = prev_remainbudget;

                            prev_remainbudget = prev_remainbudget - getAmountInt;

                            String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

                            getEsBudgetINT = Integer.parseInt(getEsBudget);

                            if (CheckRemainBudget < getAmountInt) {

                                AlertForRemainBudget();

                            } else {

                                try {

                                    gettingInfo.updatePrevRemainBudget(getlastID, prev_remainbudget);

                                    gettingInfo.updatefoodEntry(getlastID, prev_food);

                                    gettingInfo.close();

                                } catch (Exception e) {
                                    didItWork = false;
                                    String error = e.toString();
                                    Dialog d = new Dialog(EnterExpensesActivity.this);
                                    d.setTitle("Not Updated");
                                    TextView tv = new TextView(EnterExpensesActivity.this);
                                    tv.setText(error);
                                    d.setContentView(tv);
                                    d.show();
                                } finally {
                                    if (didItWork) {
                                        Intent openonGoingActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                                        startActivity(openonGoingActivity);
                                    }
                                }
                            }

                        } else if (BudgetTrackerActivity.checkfortracking == 4) {

                            boolean didItWork = true;

                            TrackerDAO gettingInfo = new TrackerDAO(EnterExpensesActivity.this);

                            gettingInfo.open();

                            long getlastID = gettingInfo.getMaxID();

                            int prev_entertainment = gettingInfo.getEntertainmentPrev(getlastID);

                            String getAmountString = EnterValue.getText().toString();

                            int getAmountInt = Integer.parseInt(getAmountString);

                            prev_entertainment = prev_entertainment + getAmountInt;

                            int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

                            CheckRemainBudget = prev_remainbudget;

                            prev_remainbudget = prev_remainbudget - getAmountInt;


                            String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

                            getEsBudgetINT = Integer.parseInt(getEsBudget);

                            if (CheckRemainBudget < getAmountInt) {

                                AlertForRemainBudget();

                            } else {

                                try {

                                    gettingInfo.updatePrevRemainBudget(getlastID, prev_remainbudget);

                                    gettingInfo.updateEntertainmentEntry(getlastID, prev_entertainment);

                                    gettingInfo.close();

                                } catch (Exception e) {
                                    didItWork = false;
                                    String error = e.toString();
                                    Dialog d = new Dialog(EnterExpensesActivity.this);
                                    d.setTitle("Dang it!");
                                    TextView tv = new TextView(EnterExpensesActivity.this);
                                    tv.setText(error);
                                    d.setContentView(tv);
                                    d.show();
                                } finally {
                                    if (didItWork) {
                                        Intent openActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                                        startActivity(openActivity);
                                    }

                                }

                            }

                        } else if (BudgetTrackerActivity.checkfortracking == 5) {

                            boolean didItWork = true;

                            TrackerDAO gettingInfo = new TrackerDAO(EnterExpensesActivity.this);

                            gettingInfo.open();

                            long getlastID = gettingInfo.getMaxID();

                            int prev_shopping = gettingInfo.getShoppingPrev(getlastID);

                            String getAmountString = EnterValue.getText().toString();

                            int getAmountInt = Integer.parseInt(getAmountString);

                            prev_shopping = prev_shopping + getAmountInt;

                            int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

                            CheckRemainBudget = prev_remainbudget;

                            prev_remainbudget = prev_remainbudget - getAmountInt;

                            String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

                            getEsBudgetINT = Integer.parseInt(getEsBudget);

                            if (CheckRemainBudget < getAmountInt) {

                                AlertForRemainBudget();

                            } else {

                                try {

                                    gettingInfo.updatePrevRemainBudget(getlastID, prev_remainbudget);

                                    gettingInfo.updateshoppingEntry(getlastID, prev_shopping);

                                    gettingInfo.close();

                                } catch (Exception e) {
                                    didItWork = false;
                                    String error = e.toString();
                                    Dialog d = new Dialog(EnterExpensesActivity.this);
                                    d.setTitle("Not Updated");
                                    TextView tv = new TextView(EnterExpensesActivity.this);
                                    tv.setText(error);
                                    d.setContentView(tv);
                                    d.show();
                                } finally {
                                    if (didItWork) {
                                        Intent openonGoingActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                                        startActivity(openonGoingActivity);
                                    }
                                }
                            }

                        } else if (BudgetTrackerActivity.checkfortracking == 6) {

                            boolean didItWork = true;

                            TrackerDAO gettingInfo = new TrackerDAO(EnterExpensesActivity.this);

                            gettingInfo.open();

                            long getlastID = gettingInfo.getMaxID();

                            int prev_other = gettingInfo.getOtherPrev(getlastID);

                            String getAmountString = EnterValue.getText().toString();

                            int getAmountInt = Integer.parseInt(getAmountString);

                            prev_other = prev_other + getAmountInt;

                            int prev_remainbudget = gettingInfo.getPrevRemainBudget(getlastID);

                            CheckRemainBudget = prev_remainbudget;

                            prev_remainbudget = prev_remainbudget - getAmountInt;

                            String getEsBudget = gettingInfo.getEstimatedBudget(getlastID);

                            getEsBudgetINT = Integer.parseInt(getEsBudget);

                            if (CheckRemainBudget < getAmountInt) {

                                AlertForRemainBudget();

                            } else {

                                try {

                                    gettingInfo.updatePrevRemainBudget(getlastID, prev_remainbudget);

                                    gettingInfo.updateotherEntry(getlastID, prev_other);

                                    gettingInfo.close();

                                } catch (Exception e) {
                                    didItWork = false;
                                    String error = e.toString();
                                    Dialog d = new Dialog(EnterExpensesActivity.this);
                                    d.setTitle("Not Updated");
                                    TextView tv = new TextView(EnterExpensesActivity.this);
                                    tv.setText(error);
                                    d.setContentView(tv);
                                    d.show();
                                } finally {
                                    if (didItWork) {
                                        Intent openonGoingActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                                        startActivity(openonGoingActivity);
                                    }
                                }
                            }
                        }
                    }
                }
            }


        });

    }

    protected void AlertForRemainBudget() {

        AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(EnterExpensesActivity.this);

        alertDialog3.setTitle("Bugdet Alert!"); // Setting Dialog Title

        alertDialog3.setMessage("You have reached your budget!" + "\n" +
                "Your Estimated Budget: " + getEsBudgetINT + "\n" +
                "Your Remaining Budget: " + CheckRemainBudget); // Setting Dialog Message

        alertDialog3.setIcon(R.drawable.alerticon); // Setting Icon to Dialog

        // Setting "Yes" Button
        alertDialog3.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        alertDialog3.show(); // Showing Alert Dialog

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

                    Intent OpenMyActivity = new Intent(EnterExpensesActivity.this, BudgetTrackerActivity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
