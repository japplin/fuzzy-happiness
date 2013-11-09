package com.CardMemorizer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
