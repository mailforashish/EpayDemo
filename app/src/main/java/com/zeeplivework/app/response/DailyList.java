package com.zeeplivework.app.response;

public class DailyList {
    int image;
    String User_Name;
    String coins;
    String date;

    public DailyList(int image, String user_Name, String coins, String date) {
        this.image = image;
        User_Name = user_Name;
        this.coins = coins;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
