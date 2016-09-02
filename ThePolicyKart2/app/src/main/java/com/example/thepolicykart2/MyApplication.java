package com.example.thepolicykart2;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    private static MyApplication application;
    private User userprofiledetails;
    private String premium;
    int login;

    public MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

    public User getUserprofiledetails() {
        return userprofiledetails;
    }

    public void setUserprofiledetails(User userprofiledetails) {
        this.userprofiledetails = userprofiledetails;
    }

    public void clearUserprofiledetails(){
        this.userprofiledetails=null;
    }

    public int getLoggedIn() {
        return login;
    }

    public void setP(String premium)
    {
        this.premium=premium;
    }

    public String getP() {
        return premium;
    }

    public void setLoggedIn(int login)
    {
        this.login=login;
    }
}