package com.example.thepolicykart2;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Term_plan extends FragmentActivity {
	ViewPager Tab;
    TabPagerAdapter5 TabAdapter;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
     TabAdapter = new TabPagerAdapter5(getSupportFragmentManager());
        
        Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                       
                    	actionBar = getActionBar();
                    	actionBar.setSelectedNavigationItem(position);                    }
                });
        Tab.setAdapter(TabAdapter);
        
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

			@Override
			public void onTabReselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			 public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	          
	            Tab.setCurrentItem(tab.getPosition());
	        }

			@Override
			public void onTabUnselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}};
			//Add New Tab
			actionBar.addTab(actionBar.newTab().setText("AMOLYA JEEVAN -2").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("ANMOL JEEVAN -2").setTabListener(tabListener));
	}
}


    