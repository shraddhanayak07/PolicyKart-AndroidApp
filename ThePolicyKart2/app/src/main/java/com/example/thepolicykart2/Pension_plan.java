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

public class Pension_plan extends FragmentActivity {
	ViewPager Tab;
    TabPagerAdapter1 TabAdapter;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
     TabAdapter = new TabPagerAdapter1(getSupportFragmentManager());
        
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
			actionBar.addTab(actionBar.newTab().setText("LIC NEW JEEVAN NIDHI ").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("LIC AKSHAY VI").setTabListener(tabListener));
	}



    
		
	

}