package com.CardMemorizer.android;

import java.util.ArrayList;

import android.app.Activity;
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

	private ArrayList<Card> deck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridView = ((GridView) findViewById(R.id.grid_view));
		drawer = ((DrawerLayout) findViewById(R.id.drawer_layout));
		startButton = ((Button) drawer.findViewById(R.id.start));
		restartButton = ((Button) drawer.findViewById(R.id.restart));
		
		deck = new ArrayList<Card>(CardMemorizerSavedState.getInstance().getShuffledDeck());

		adapter = new CardGridViewAdapter(this, deck);
		gridView.setColumnWidth(deck.get(0).getWidth());
		gridView.setAdapter(adapter);
		setupButtons();
	}
	
	public void setupButtons() {
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!CardMemorizerSavedState.getInstance().isRunning()) {
					CardMemorizerSavedState.getInstance().setIsRunning(true);
					for (Card card : deck) {
						card.shouldShowBack(true);
					}
				}

			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (Card card : deck) {
					card.shouldShowBack(false);
				}

				CardMemorizerSavedState.getInstance().setIsRunning(false);
			}
		});
	}

	@Override
	protected void onPause() {
		CardMemorizerSavedState.getInstance().setShuffledDeck(deck);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
