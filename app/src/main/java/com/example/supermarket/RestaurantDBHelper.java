package com.example.supermarket;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RestaurantDBHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_RESTAURANTS =
            "create table restaurants (_id integer primary key autoincrement, "
            + "restaurantname text, streetaddress text, "
            + "city text, state text, zipcode text, "
                    + "liquor_rating float, meat_rating float, "
                    + "produce_rating float, cheese_rating float, checkout_rating float, "
                    + "average_rating float);";


    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS restaurants" );
        onCreate(db);

    }
}
