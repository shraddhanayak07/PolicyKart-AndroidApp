package com.example.thepolicykart2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter5 extends FragmentStatePagerAdapter {
    public TabPagerAdapter5(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
        case 0:
            return new Amolya_jeevan();
        case 1:
             return new Anmol_jeevan();
      
        }
		return null;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2; //No of Tabs
	}


    }