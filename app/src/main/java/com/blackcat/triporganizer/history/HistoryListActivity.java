package com.blackcat.triporganizer.history;

import com.blackcat.triporganizer.MainMenuActivity;
import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.dao.DBConfiguration;
import com.blackcat.triporganizer.tracker.dao.TrackerDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class HistoryListActivity extends Activity {

    private TrackerDAO dbHelper;
    private SimpleCursorAdapter dataAdapter;

    ArrayAdapter<String> Database;

    String historyString = "";
    //long idDB;

    long getlastID;
    String getAD;
    String Check;


    public static long TripID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        try {

            dbHelper = new TrackerDAO(this);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        dbHelper.open();

        displayListView();
    }


    private void displayListView() {

        Cursor cursor = dbHelper.fetchAllEntries();

        dbHelper.close();

        // The desired columns to be bound
        String[] columns = new String[]{
                DBConfiguration.KEY_NAME,
                DBConfiguration.KEY_CITY,
                DBConfiguration.KEY_REASON,
                DBConfiguration.KEY_BUDGET
        };
        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.TV_trip_name,
                R.id.TV_city_name,
                R.id.TV_trip_reason,
                R.id.TV_budget,
        };

        //create the adapter using the cursor pointing to the desired data
        //as well as the layout information

        dataAdapter = new SimpleCursorAdapter(this, R.layout.history_list_item, cursor, columns, to, 0);

        ListView listView = (ListView) findViewById(R.id.listView_tripHistory);

        listView.setEmptyView(findViewById(R.id.textView_HIstoryListforEmpty));
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                intentMethod(id);

            }

            private void intentMethod(long ID) {

                TripID = ID + 0;

                HistoryViewerActivity.checkfrom = 1;

                Intent Openactivity = new Intent(HistoryListActivity.this, HistoryViewerActivity.class);
                startActivity(Openactivity);

            }
        });
    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:

                    Intent OpenMyActivity = new Intent(HistoryListActivity.this, MainMenuActivity.class);
                    startActivity(OpenMyActivity);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
