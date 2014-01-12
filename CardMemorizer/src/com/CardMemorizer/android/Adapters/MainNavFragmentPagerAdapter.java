package com.CardMemorizer.android.Adapters;

import com.CardMemorizer.android.MainNavigation.CustomGameCreation;
import com.CardMemorizer.android.MainNavigation.LevelBrowserFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainNavFragmentPagerAdapter extends FragmentPagerAdapter {

	private Fragment[] fragmentList;

	public MainNavFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		fragmentList = new Fragment[]{new LevelBrowserFragment(), new CustomGameCreation()};
		
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList[position];
	}

	@Override
	public int getCount() {
		return fragmentList.length;
	}

}