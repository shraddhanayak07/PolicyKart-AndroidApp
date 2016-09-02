package com.example.thepolicykart2;

import android.os.Bundle;
import android.app.ActionBar;
//import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Home extends FragmentActivity {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;
    String caller;
    User user, user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        //caller = getIntent().getStringExtra("caller");
        //user=(User) getIntent().getSerializableExtra("User Details");
        //user1=(User) getIntent().getSerializableExtra("LoggedIn");
        //Log.v("LoggedIn", Integer.toString(user1.LoggedIn));

		/*getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ff59")));*/
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());

        Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {

                        actionBar = getActionBar();
                        actionBar.setSelectedNavigationItem(position);

                    }
                });
        Tab.setAdapter(TabAdapter);

        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

                Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub

            }};
        //Add New Tab
        actionBar.addTab(actionBar.newTab().setText("PLANS").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("QUERY NOW").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("CALCULATOR").setTabListener(tabListener));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        User getUser = SaveSharedPreference.getUserName(Home.this);

        /*if(getUser!=null)
        {
            Log.v("value of getUser",getUser.name);
        }*/
        if(getUser.username == null)
        {
            Intent it=new Intent(getApplicationContext(),Login.class);
            startActivity(it);
        }
        else {
            MyApplication myApplication = new MyApplication().getInstance();
            myApplication.setUserprofiledetails(getUser);
            Intent i = new Intent(getApplicationContext(), UserProfileDetails.class);
            startActivity(i);
            //User user2=new User(user1.LoggedIn);
            //  Intent i=new Intent(getApplicationContext(), Login.class);
            //i.putExtra("User Details", user);
            //i.putExtra("LoggedIn", user2);
            //startActivity(i);
        }
        return true;
    }
}