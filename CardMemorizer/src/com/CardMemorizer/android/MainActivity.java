package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.CardMemorizer.android.Card.Rank;

public class MainActivity extends Activity {

	private int deckSize;
	private boolean addClubs;
	private boolean addSpades;
	private boolean addDiamonds;
	private boolean addHearts;
	private int maxSize;
	private ArrayList<Card> deck;
	private Button startButton;
	private Button restartButton;
	private Button selectButton;
	private Button newgameButton;
	private LinearLayout deckContainer;
	private DrawerLayout drawer;
	private CheckBox heartsChk;
	private CheckBox diamondsChk;
	private CheckBox clubsChk;
	private CheckBox spadesChk;
	private NumberPicker np;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deckSize = 52;
		addClubs = true;
		addSpades = true;
		addDiamonds = true;
		addHearts = true;
		maxSize = 52;
		deck = new ArrayList<Card>(deckSize);
		startButton = ((Button) findViewById(R.id.start));
		restartButton = ((Button) findViewById(R.id.restart));
		selectButton = ((Button) findViewById(R.id.select));
		newgameButton = ((Button) findViewById(R.id.new_game));
		deckContainer = ((LinearLayout) findViewById(R.id.shuffled_card_container));
		drawer = ((DrawerLayout) findViewById(R.id.drawer_layout));
		heartsChk = ((CheckBox) findViewById(R.id.chkhearts));
		diamondsChk = ((CheckBox) findViewById(R.id.chkdiamonds));
		clubsChk = ((CheckBox) findViewById(R.id.chkclubs));
		spadesChk = ((CheckBox) findViewById(R.id.chkspades));

		np = (NumberPicker) findViewById(R.id.np);
		np.setMaxValue(52);
		np.setMinValue(1);

		heartsChk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					addHearts = true;
				} else {
					addHearts = false;
				}
				setNPRange();

				if (!addClubs && !addSpades && !addHearts && !addDiamonds) {
					newgameButton.setEnabled(false);
				} else {
					newgameButton.setEnabled(true);
				}
			}
		});

		diamondsChk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					addDiamonds = true;
				} else {
					addDiamonds = false;
				}
				setNPRange();

				if (!addClubs && !addSpades && !addHearts && !addDiamonds) {
					newgameButton.setEnabled(false);
				} else {
					newgameButton.setEnabled(true);
				}
			}
		});

		clubsChk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					addClubs = true;
				} else {
					addClubs = false;
				}
				setNPRange();

				if (!addClubs && !addSpades && !addHearts && !addDiamonds) {
					newgameButton.setEnabled(false);
				} else {
					newgameButton.setEnabled(true);
				}
			}
		});

		spadesChk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					addSpades = true;
				} else {
					addSpades = false;
				}
				setNPRange();

				if (!addClubs && !addSpades && !addHearts && !addDiamonds) {
					newgameButton.setEnabled(false);
				} else {
					newgameButton.setEnabled(true);
				}
			}
		});

		newgameButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				deck = createDeck(addClubs, addSpades, addDiamonds, addHearts);
				Collections.shuffle(deck);
				deckContainer.removeAllViews();

				int dcount2 = 0;

				for (Card card : deck) {
					if (dcount2 < np.getValue()) {
						card.setSelectedState(false);
						card.shouldShowBack(false);
						deckContainer.addView(card);
					}
					dcount2 = dcount2 + 1;
				}

				CardMemorizerSavedState.getInstance().setIsRunning(false);
				updateButtonVisibility();
			}
		});

		drawer.openDrawer(Gravity.LEFT);
		if (CardMemorizerSavedState.getInstance().isRunning()) {
			deck = new ArrayList<Card>(CardMemorizerSavedState.getInstance().getShuffledDeck());
		} else {
			deck = createDeck(addClubs, addSpades, addDiamonds, addHearts);
			Collections.shuffle(deck);
		}

		addDeckToLayout(deck, R.id.shuffled_card_container, maxSize);
		setupButtons();
	}

	public void setupButtons() {
		updateButtonVisibility();
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!CardMemorizerSavedState.getInstance().isRunning()) {
					CardMemorizerSavedState.getInstance().setIsRunning(true);
					for (Card card : deck) {
						card.shouldShowBack(true);
					}
					updateButtonVisibility();
				}

			}
		});

		restartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Collections.shuffle(deck);
				deckContainer.removeAllViews();
				for (Card card : deck) {
					card.setSelectedState(false);
					card.shouldShowBack(false);
					deckContainer.addView(card);
				}

				CardMemorizerSavedState.getInstance().setIsRunning(false);
				updateButtonVisibility();
			}
		});

		selectButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Card selectedCard = CardMemorizerSavedState.getInstance().getSelectedCard();
				if (selectedCard != null && isSelectedSuit(selectedCard) && isSelectedRank(selectedCard)) {
					selectedCard.shouldShowBack(false);
					selectedCard.setSelectedState(false);
				}
			}
		});
	}

	private void updateButtonVisibility() {
		startButton.setVisibility(CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
		restartButton.setVisibility(!CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
		selectButton.setVisibility(!CardMemorizerSavedState.getInstance().isRunning() ? View.GONE : View.VISIBLE);
	}

	private boolean isSelectedSuit(Card selectedCard) {
		RadioGroup suit = (RadioGroup) findViewById(R.id.card_suit);
		return suit.getCheckedRadioButtonId() == selectedCard.getSuit().getId();
	}

	private boolean isSelectedRank(Card selectedCard) {
		RadioGroup rank = (RadioGroup) findViewById(R.id.card_rank);
		return rank.getCheckedRadioButtonId() == selectedCard.getRank().getId();
	}

	@Override
	protected void onPause() {
		CardMemorizerSavedState.getInstance().setShuffledDeck(deck);
		deckContainer.removeAllViews();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private ArrayList<Card> createDeck(boolean addClubs, boolean addSpades, boolean addDiamonds, boolean addHearts) {
		ArrayList<Card> deck = new ArrayList<Card>(deckSize);

		if (addClubs) {
			deck.add(new Card(this, R.drawable.clubs_ace, Card.Rank.ace, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_2, Card.Rank.two, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_3, Card.Rank.three, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_4, Card.Rank.four, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_5, Card.Rank.five, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_6, Card.Rank.six, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_7, Card.Rank.seven, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_8, Card.Rank.eight, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_9, Card.Rank.nine, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_10, Card.Rank.ten, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_jack, Card.Rank.jack, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_queen, Card.Rank.queen, Card.Suit.clubs));
			deck.add(new Card(this, R.drawable.clubs_king, Card.Rank.king, Card.Suit.clubs));
		}

		if (addDiamonds) {
			deck.add(new Card(this, R.drawable.diamonds_ace, Card.Rank.ace, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_2, Card.Rank.two, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_3, Card.Rank.three, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_4, Card.Rank.four, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_5, Card.Rank.five, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_6, Card.Rank.six, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_7, Card.Rank.seven, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_8, Card.Rank.eight, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_9, Card.Rank.nine, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_10, Card.Rank.ten, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_jack, Card.Rank.jack, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_queen, Card.Rank.queen, Card.Suit.diamonds));
			deck.add(new Card(this, R.drawable.diamonds_king, Card.Rank.king, Card.Suit.diamonds));
		}

		if (addHearts) {
			deck.add(new Card(this, R.drawable.hearts_ace, Card.Rank.ace, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_2, Card.Rank.two, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_3, Card.Rank.three, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_4, Card.Rank.four, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_5, Card.Rank.five, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_6, Card.Rank.six, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_7, Card.Rank.seven, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_8, Card.Rank.eight, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_9, Card.Rank.nine, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_10, Card.Rank.ten, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_jack, Card.Rank.jack, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_queen, Card.Rank.queen, Card.Suit.hearts));
			deck.add(new Card(this, R.drawable.hearts_king, Card.Rank.king, Card.Suit.hearts));
		}

		if (addSpades) {
			deck.add(new Card(this, R.drawable.spades_ace, Card.Rank.ace, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_2, Card.Rank.two, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_3, Card.Rank.three, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_4, Card.Rank.four, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_5, Card.Rank.five, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_6, Card.Rank.six, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_7, Card.Rank.seven, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_8, Card.Rank.eight, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_9, Card.Rank.nine, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_10, Card.Rank.ten, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_jack, Card.Rank.jack, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_queen, Card.Rank.queen, Card.Suit.spades));
			deck.add(new Card(this, R.drawable.spades_king, Card.Rank.king, Card.Suit.spades));
		}
		return deck;
	}

	public void addDeckToLayout(ArrayList<Card> deck, int id, int max) {

		int dcount = 0;

		for (Card s : deck) {
			if (dcount < max) {
				deckContainer.addView(s);
			}
			dcount = dcount + 1;
		}
	}

	public void onRadioButtonClicked(View view) {
		((RadioButton) view).isChecked();

	}

	public void setNPRange() {
		int range = 0;

		if (addClubs) {
			range += Rank.values().length;
		}
		if (addDiamonds) {
			range += Rank.values().length;
			;
		}
		if (addSpades) {
			range += Rank.values().length;
			;
		}
		if (addHearts) {
			range += Rank.values().length;
			;
		}

		np.setMaxValue(range);
		np.setMinValue(1);
	}
}
