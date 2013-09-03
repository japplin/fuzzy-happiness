package com.CardMemorizer.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

	private GridView gridView;
	private CardGridViewAdapter adapter;
	private DrawerLayout drawer;

	private Button startButton;
	private Button restartButton;

	private ArrayList<CardInfo> deck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();

		gridView = ((GridView) findViewById(R.id.grid_view));
		drawer = ((DrawerLayout) findViewById(R.id.drawer_layout));
		startButton = ((Button) drawer.findViewById(R.id.start));
		restartButton = ((Button) drawer.findViewById(R.id.restart));

		if (deck == null ) {
			deck = intent.getParcelableArrayListExtra(CustomGameCreation.DECK_INFO);
		} 
		
		adapter = new CardGridViewAdapter(this, deck);
		gridView.setColumnWidth(175);
		gridView.setAdapter(adapter);
		setupButtons();
	}

	public void setupButtons() {
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!CardMemorizerSavedState.getInstance().isRunning()) {
					CardMemorizerSavedState.getInstance().setIsRunning(true);
					for (CardInfo card : deck) {
						card.isBackShowing(true);
					}
					adapter.notifyDataSetChanged();
				}
			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (CardInfo card : deck) {
					card.isBackShowing(false);
				}
				adapter.notifyDataSetChanged();
				CardMemorizerSavedState.getInstance().setIsRunning(false);
			}
		});
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		deck = savedInstanceState.getParcelableArrayList(CustomGameCreation.DECK_INFO);
		adapter.setData(deck);
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList(CustomGameCreation.DECK_INFO, deck);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
