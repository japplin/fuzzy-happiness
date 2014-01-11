package com.CardMemorizer.android.MainNavigation;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.CardMemorizer.android.NavigationHelper;
import com.CardMemorizer.android.R;

public class AboutPage extends Activity {
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(R.string.about);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavigationHelper.getInstance().goToHomeActivity(AboutPage.this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
