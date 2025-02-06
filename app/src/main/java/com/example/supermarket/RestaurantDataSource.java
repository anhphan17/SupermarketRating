package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantDataSource {

    private SQLiteDatabase database;
    private RestaurantDBHelper dbHelper;

    public RestaurantDataSource(Context context) {
        dbHelper = new RestaurantDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertRestaurant(Restaurant r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("restaurantname", r.getName());
            initialValues.put("streetaddress", r.getStreetAddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zipcode", r.getZipcode());

            didSucceed = database.insert("restaurants", null, initialValues) > 0;
        }
        catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateRestaurantRatings(Restaurant r) {
        boolean didSucceed = false;
        try {
            long rowId = (long) r.getRestaurantId();
            ContentValues updateValues = new ContentValues();

            updateValues.put("liquor_rating", r.getLiquorRating());
            updateValues.put("meat_rating", r.getMeatRating());
            updateValues.put("produce_rating", r.getProduceRating());
            updateValues.put("cheese_rating", r.getCheeseRating());
            updateValues.put("checkout_rating", r.getCheckoutRating());
            updateValues.put("average_rating", r.getAverageRating());

            didSucceed = database.update("restaurants", updateValues, "_id="
                    + rowId, null) > 0;
        }
        catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastRestaurantId() {
        int lastId;
        try {
            String query = "SELECT MAX(_id) FROM restaurants";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

}
