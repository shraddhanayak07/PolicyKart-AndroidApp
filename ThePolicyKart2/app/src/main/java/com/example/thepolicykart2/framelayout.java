package com.example.thepolicykart2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class framelayout extends FragmentActivity {

    public String caller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.framelayout);
        caller = getIntent().getStringExtra("caller");

        MainFragment newFragment = new MainFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame,newFragment);
        transaction.commit();
    }

    public String getValue()
    {
        return caller;
    }

}
