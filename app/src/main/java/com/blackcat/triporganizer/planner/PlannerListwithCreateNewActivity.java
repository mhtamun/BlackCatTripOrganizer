package com.blackcat.triporganizer.planner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.blackcat.triporganizer.MainMenuActivity;
import com.blackcat.triporganizer.R;
import com.blackcat.triporganizer.dao.DBConfiguration;
import com.blackcat.triporganizer.planner.dao.PlannerDAO;

public class PlannerListwithCreateNewActivity extends Activity {


    PlannerDAO DBHelper;
    SimpleCursorAdapter DataAdapter;

    ImageButton AddNew;

    public static long PlannerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner_listwith_create_new);

        AddNew = (ImageButton) findViewById(R.id.imageButton_PlannerListAddNew);
        AddNew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent OpenAddNew = new Intent(PlannerListwithCreateNewActivity.this, AddNewTripActivity.class);
                startActivity(OpenAddNew);

            }
        });

        try {

            DBHelper = new PlannerDAO(this);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        DBHelper.open();

        displayListView();
    }

    private void displayListView() {

        Cursor PlannerCursor = DBHelper.fetchAllPlannerEntries();

        // The desired columns to be bound
        String[] PlannerColumns = new String[]{
                DBConfiguration.KEY_PLANNERNAME,
                DBConfiguration.KEY_PLANNERCITY,
                DBConfiguration.KEY_PLANNERDATE,
                DBConfiguration.KEY_PLANNERTRAVELBY
        };
        // the XML defined views which the data will be bound to

        int[] XML = new int[]{
                R.id.TV_Plan_Name,
                R.id.TV_Plan_City,
                R.id.TV_Plan_date,
                R.id.TV_Plan_TravelBy
        };

        //create the adapter using the PlannerCursor pointing to the desired data

        //as well as the layout information

        DataAdapter = new SimpleCursorAdapter(PlannerListwithCreateNewActivity.this, R.layout.planner_list_item, PlannerCursor, PlannerColumns, XML, 0);

        ListView listView = (ListView) findViewById(R.id.listView_PlannerList);

        listView.setEmptyView(findViewById(R.id.textView_PlannerListforEmpty));


        // Assign adapter to ListView
        listView.setAdapter(DataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                PlannerID = id;

                Intent ViewerActivity = new Intent(PlannerListwithCreateNewActivity.this, PlannerListViewerActivity.class);
                startActivity(ViewerActivity);

            }
        });
    }

    /*

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {

                case KeyEvent.KEYCODE_BACK:
                    Intent BacktoAddNew = new Intent(PlannerListwithCreateNewActivity.this, MainMenuActivity.class);
                    startActivity(BacktoAddNew);

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
