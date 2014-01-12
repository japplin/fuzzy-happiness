package com.CardMemorizer.android.MainNavigation;

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

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new MainNavFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
	}

}
