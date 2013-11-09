package com.CardMemorizer.android;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

public class LevelBrowser extends Activity {

	GridView levelGrid;
	LevelAdapter adapter;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_browser);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(R.string.select_level);

		adapter = new LevelAdapter(this, LevelHolder.getInstance().getLevels());
		levelGrid = (GridView) findViewById(R.id.level_grid);
		levelGrid.setAdapter(adapter);

	}

	@Override
	protected void onResume() {
		adapter.setSetLevels(LevelHolder.getInstance().getLevels());
		super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavigationHelper.getInstance().goToHomeActivity(LevelBrowser.this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
