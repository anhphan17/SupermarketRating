package com.example.supermarket;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private float liquorRating;
    private float produceRating;
    private float meatRating;
    private float cheeseRating;
    private float checkoutRating;
    private float averageRating;

    public Restaurant() {
        restaurantId = -1;

    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int i) {
        restaurantId = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String s) {
        streetAddress = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        city = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String s) {
        zipcode = s;
    }

    public float getLiquorRating() {
        return liquorRating;
    }

    public void setLiquorRating(float f) {
        liquorRating = f;
    }

    public float getProduceRating() {
        return produceRating;
    }

    public void setProduceRating(float f) {
        produceRating = f;
    }

    public float getMeatRating() {
        return meatRating;
    }

    public void setMeatRating(float f) {
        meatRating = f;
    }

    public float getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(float f) {
        cheeseRating = f;
    }

    public float getCheckoutRating() {
        return checkoutRating;
    }

    public void setCheckoutRating(float f) {
        checkoutRating = f;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float f) {
        averageRating = f;
    }
}
