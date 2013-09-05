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
		adapter = new LevelAdapter(this, LevelHolder.getInstance().getLevels());
		levelGrid = (GridView) findViewById(R.id.level_grid);

		levelGrid.setAdapter(adapter);

	}
}
