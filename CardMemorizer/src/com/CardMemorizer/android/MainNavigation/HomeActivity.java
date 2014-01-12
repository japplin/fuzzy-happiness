package com.CardMemorizer.android.MainNavigation;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.CardMemorizer.android.R;
import com.CardMemorizer.android.Adapters.MainNavFragmentPagerAdapter;

public class HomeActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}
		};
		
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new MainNavFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				getActionBar().setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		actionBar.addTab(actionBar.newTab().setText(R.string.levels).setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText(R.string.custom_game).setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText(R.string.about).setTabListener(tabListener));
	}
}
