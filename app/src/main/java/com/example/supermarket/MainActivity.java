package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Restaurant currentRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        currentRestaurant = new Restaurant();

        initRateButton();
        initTextChangedEvents();
    }

    private void initRateButton() {
        Button rateButton = findViewById(R.id.rateBtn);
        rateButton.setOnClickListener(v -> {
            boolean didSucceed;
            RestaurantDataSource ds = new RestaurantDataSource(MainActivity.this);
            try {
                ds.open();

                if (currentRestaurant.getRestaurantId() == -1) {
                    didSucceed = ds.insertRestaurant(currentRestaurant);
                    if (didSucceed) {
                        int newId = ds.getLastRestaurantId();
                        currentRestaurant.setRestaurantId(newId);
                    }
                }
                else {
                    ds.updateRestaurantRatings(currentRestaurant);
                }
                ds.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                ds.close();
            }
            Intent intent = new Intent(MainActivity.this, RatingActivity.class);
            intent.putExtra("_id", currentRestaurant.getRestaurantId());
            startActivity(intent);
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.marketNameEditText);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipcode.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
    }

    private void initTextChangedEvents() {
        final EditText etRestaurantName = findViewById(R.id.marketNameEditText);
        etRestaurantName.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setName(etRestaurantName.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        final EditText etStreetAddress = findViewById(R.id.editAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setStreetAddress(etStreetAddress.getText().toString());

            }
        });

        final EditText etCity = findViewById(R.id.editCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setCity(etCity.getText().toString());

            }
        });

        final EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setState(etState.getText().toString());

            }
        });

        final EditText etZipcode = findViewById(R.id.editZipcode);
        etZipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setZipcode(etZipcode.getText().toString());

            }
        });

    }
}