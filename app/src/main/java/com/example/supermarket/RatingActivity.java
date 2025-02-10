package com.example.supermarket;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RatingActivity extends AppCompatActivity {

    private RatingBar liquorRating, produceRating, meatRating, cheeseRating, checkoutRating;
    private Restaurant currentRating;
    private int RestaurantId;
    private Restaurant currentRestaurant;

    private float averageRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rating);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RestaurantId = getIntent().getIntExtra("_id", -1);
        currentRating = new Restaurant();
        initSaveButton();
        initRatingsBars();

        initBackButton();
    }

    private void initRatingsBars() {
        liquorRating = findViewById(R.id.liquorRatingBar);
        produceRating = findViewById(R.id.produceRatingBar);
        meatRating = findViewById(R.id.meatRatingBar);
        cheeseRating = findViewById(R.id.cheeseRatingBar);
        checkoutRating = findViewById(R.id.checkoutRatingBar);
    }
    private void initBackButton() {
        Button backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(v -> {
            currentRating.setLiquorRating(liquorRating.getRating());
            currentRating.setProduceRating(produceRating.getRating());
            currentRating.setMeatRating(meatRating.getRating());
            currentRating.setCheeseRating(cheeseRating.getRating());
            currentRating.setCheckoutRating(checkoutRating.getRating());

            TextView averageText = findViewById(R.id.averageNumTextView);

            float avg = (liquorRating.getRating() + produceRating.getRating() + meatRating.getRating()
                    + cheeseRating.getRating() + checkoutRating.getRating()) / 5;

            currentRating.setAverageRating(avg);

            Resources res = getResources();
            String avgStr = String.format("%.2f", avg);
            String avgRating = res.getString(R.string.avg_rating, avgStr);
            averageText.setText(avgRating);

            currentRating.setRestaurantId(RestaurantId);

            RestaurantDataSource ds = new RestaurantDataSource(this);
            try {
                ds.open();
                if (currentRating.getRestaurantId() == -1) {
                    boolean wasInserted = ds.insertRestaurant(currentRating);
                    if(wasInserted) {
                        int newId = ds.getLastRestaurantId();
                        currentRating.setRestaurantId(newId);
                    }
                }
                else {
                    ds.updateRestaurantRatings(currentRating);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                ds.close();
            }

        });

    }
}