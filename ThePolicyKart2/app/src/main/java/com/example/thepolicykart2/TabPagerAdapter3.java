package com.example.thepolicykart2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter3 extends FragmentStatePagerAdapter {
    public TabPagerAdapter3(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
        case 0:
            return new New_jeevan_anand();
        case 1:
             return new New_endowment();
        case 2:
            return new Jeevan_lakshay();
        case 3:
             return new Single_endowment();
        case 4:
            return new Jeevan_arogya();
        case 5:
             return new Jeevan_labh();
        case 6:
            return new Jeevan_pragati();
      
        }
		return null;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7; //No of Tabs
	}


    }