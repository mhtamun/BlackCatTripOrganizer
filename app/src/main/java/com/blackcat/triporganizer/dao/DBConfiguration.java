package com.blackcat.triporganizer.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBConfiguration extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBConfiguration";

    //DB information: It's name, and the table we are using.
    private static final String DATABASE_NAME = "tourOrganizer_Database";
    private static final int DATABASE_VERSION = 8;

    //Table information: The table we are using.
    public static final String TRACKER_TABLE = "tracker_table";
    public static final String CHECK_TABLE = "checkin_table";
    public static final String PLANNER_TABLE = "planner_table";

    //Tracker Table Fields here:
    public static final String KEY_TRACKERID = "_id";
    public static final String KEY_NAME = "tripName";
    public static final String KEY_REASON = "tripReason";
    public static final String KEY_CITY = "city";
    public static final String KEY_SOD = "startingDate";
    public static final String KEY_AD = "arrivalDate";
    public static final String KEY_BUDGET = "tBudget";
    public static final String KEY_HE = "hotelExp";
    public static final String KEY_TE = "transportExp";
    public static final String KEY_FE = "foodExp";
    public static final String KEY_EE = "entertainmentExp";
    public static final String KEY_SE = "shoppingExp";
    public static final String KEY_OE = "othersExp";
    public static final String KEY_REMAINBUDGET = "rBudget";

    //Check Table Fields here:
    public static final String KEY_CHECKID = "_id";
    public static final String KEY_PLACENAME = "check_PlaceName";
    public static final String KEY_IN = "check_in";
    public static final String KEY_OUT = "check_out";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_TRIPNAME = "trip_name";
    public static final String KEY_SPENDINGS = "spendings";

    //Planner Table Fields Here:
    public static final String KEY_PLANNERID = "_id";
    public static final String KEY_PLANNERNAME = "plannertrip_name";
    public static final String KEY_PLANNERCITY = "planner_city";
    public static final String KEY_PLANNERDATE = "planner_date";
    public static final String KEY_PLANNERTRAVELBY = "travel_by";
    public static final String KEY_PLANNERPLACESSTAY = "placesto_stay";
    public static final String KEY_PLANNERTHINGSTODO = "thingsto_do";
    public static final String KEY_PLANNERSTATUS = "planner_status";


    public DBConfiguration(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TRACKER_TABLE + " (" +
                KEY_TRACKERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT NOT NULL," +
                KEY_REASON + " TEXT NOT NULL," +
                KEY_CITY + " TEXT," +
                KEY_SOD + " TEXT," +
                KEY_AD + " TEXT," +
                KEY_BUDGET + " TEXT NOT NULL," +
                KEY_HE + " INTEGER," +
                KEY_TE + " INTEGER," +
                KEY_FE + " INTEGER," +
                KEY_EE + " INTEGER," +
                KEY_SE + " INTEGER," +
                KEY_OE + " INTEGER," +
                KEY_REMAINBUDGET + " INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE " + CHECK_TABLE + " (" +
                KEY_CHECKID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_PLACENAME + " TEXT NOT NULL," +
                KEY_IN + " TEXT NOT NULL," +
                KEY_OUT + " TEXT NOT NULL," +
                KEY_CATEGORY + " TEXT," +
                KEY_TRIPNAME + " TEXT NOT NULL," +
                KEY_SPENDINGS + " INTEGER);");

        db.execSQL("CREATE TABLE " + PLANNER_TABLE + " (" +
                KEY_PLANNERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_PLANNERNAME + " TEXT," +
                KEY_PLANNERCITY + " TEXT," +
                KEY_PLANNERDATE + " TEXT," +
                KEY_PLANNERTRAVELBY + " TEXT," +
                KEY_PLANNERPLACESSTAY + " TEXT," +
                KEY_PLANNERTHINGSTODO + " TEXT," +
                KEY_PLANNERSTATUS + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ",which will destroy all old data");
        // KILL PREVIOUS TABLE IF UPGRADED
        db.execSQL("DROP TABLE IF EXISTS " + TRACKER_TABLE);

        db.execSQL("DROP TABLE IF EXISTS " + CHECK_TABLE);

        db.execSQL("DROP TABLE IF EXISTS " + PLANNER_TABLE);
        // CREATE NEW INSTANCE OF TABLE
        onCreate(db);
    }
}
