package com.CardMemorizer.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class LevelBrowser extends Activity {

	GridView levelGrid;
	LevelAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_browser);
		LevelHolder holder = new LevelHolder();
		adapter = new LevelAdapter(this, holder.getLevels());
		levelGrid = (GridView) findViewById(R.id.level_grid);

		levelGrid.setAdapter(adapter);

	}
}
