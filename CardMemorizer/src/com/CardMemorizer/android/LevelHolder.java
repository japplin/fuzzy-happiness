package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class LevelHolder {

	private final static Set<Suit> L1s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		L1s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L1r;
	static {
		final Set<Rank> tempSet = getRankRange(3);
		L1r = Collections.unmodifiableSet(tempSet);
	}

	private final int L1d = 3;
	private final int L1g = 5;

	Level level1 = new Level(L1s, L1r, L1d, L1g);

	// ****

	private final static Set<Suit> L2s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		L2s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L2r;
	static {
		final Set<Rank> tempSet = getRankRange(3);
		L2r = Collections.unmodifiableSet(tempSet);
	}

	private final int L2d = 3;
	private final int L2g = 5;

	Level level2 = new Level(L2s, L2r, L2d, L2g);

	// ****

	private final static Set<Suit> L3s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		L3s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L3r;
	static {
		final Set<Rank> tempSet = getRankRange(4);
		L3r = Collections.unmodifiableSet(tempSet);
	}

	private final int L3d = 4;
	private final int L3g = 5;

	Level level3 = new Level(L3s, L3r, L3d, L3g);

	// ****

	private final static Set<Suit> L4s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		L4s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L4r;
	static {
		final Set<Rank> tempSet = getRankRange(4);
		L4r = Collections.unmodifiableSet(tempSet);
	}

	private final int L4d = 4;
	private final int L4g = 5;

	Level level4 = new Level(L4s, L4r, L4d, L4g);

	// ****

	private final static Set<Suit> L5s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		L5s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L5r;
	static {
		final Set<Rank> tempSet = getRankRange(6);
		L5r = Collections.unmodifiableSet(tempSet);
	}

	private final int L5d = 6;
	private final int L5g = 5;

	Level level5 = new Level(L5s, L5r, L5d, L5g);

	// ****

	private final static Set<Suit> L6s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		L6s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L6r;
	static {
		final Set<Rank> tempSet = getRankRange(6);
		L6r = Collections.unmodifiableSet(tempSet);
	}

	private final int L6d = 6;
	private final int L6g = 5;

	Level level6 = new Level(L6s, L6r, L6d, L6g);

	// ****

	private final static Set<Suit> L7s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		L7s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L7r;
	static {
		final Set<Rank> tempSet = getRankRange(8);
		L7r = Collections.unmodifiableSet(tempSet);
	}

	private final int L7d = 8;
	private final int L7g = 5;

	Level level7 = new Level(L7s, L7r, L7d, L7g);

	// ****

	private final static Set<Suit> L8s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		L8s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L8r;
	static {
		final Set<Rank> tempSet = getRankRange(8);
		L8r = Collections.unmodifiableSet(tempSet);
	}

	private final int L8d = 8;
	private final int L8g = 5;

	Level level8 = new Level(L8s, L8r, L8d, L8g);

	// ****

	private final static Set<Suit> L9s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		tempSet.add(Suit.diamonds);
		tempSet.add(Suit.clubs);
		L9s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L9r;
	static {
		final Set<Rank> tempSet = getRankRange(8);
		L9r = Collections.unmodifiableSet(tempSet);
	}

	private final int L9d = 8;
	private final int L9g = 5;

	Level level9 = new Level(L9s, L9r, L9d, L9g);

	// ****

	private final static Set<Suit> L10s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		L10s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L10r;
	static {
		final Set<Rank> tempSet = getRankRange(10);
		L10r = Collections.unmodifiableSet(tempSet);
	}

	private final int L10d = 10;
	private final int L10g = 5;

	Level level10 = new Level(L10s, L10r, L10d, L10g);

	// ****

	private final static Set<Suit> L11s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		L11s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L11r;
	static {
		final Set<Rank> tempSet = getRankRange(10);
		L11r = Collections.unmodifiableSet(tempSet);
	}

	private final int L11d = 10;
	private final int L11g = 5;

	Level level11 = new Level(L11s, L11r, L11d, L11g);

	// ****

	private final static Set<Suit> L12s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		tempSet.add(Suit.diamonds);
		tempSet.add(Suit.clubs);
		L12s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L12r;
	static {
		final Set<Rank> tempSet = getRankRange(10);
		L12r = Collections.unmodifiableSet(tempSet);
	}

	private final int L12d = 10;
	private final int L12g = 5;

	Level level12 = new Level(L12s, L12r, L12d, L12g);

	// ****

	private final static Set<Suit> L13s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		L13s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L13r;
	static {
		final Set<Rank> tempSet = getRankRange(13);
		L13r = Collections.unmodifiableSet(tempSet);
	}

	private final int L13d = 13;
	private final int L13g = 5;

	Level level13 = new Level(L13s, L13r, L13d, L13g);

	// ****

	private final static Set<Suit> L14s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		L14s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L14r;
	static {
		final Set<Rank> tempSet = getRankRange(13);
		L14r = Collections.unmodifiableSet(tempSet);
	}

	private final int L14d = 13;
	private final int L14g = 5;

	Level level14 = new Level(L14s, L14r, L14d, L14g);

	// ****

	private final static Set<Suit> L15s;
	static {
		final Set<Suit> tempSet = new HashSet<Suit>();
		tempSet.add(Suit.hearts);
		tempSet.add(Suit.spades);
		tempSet.add(Suit.diamonds);
		tempSet.add(Suit.clubs);
		L15s = Collections.unmodifiableSet(tempSet);
	}
	private final static Set<Rank> L15r;
	static {
		final Set<Rank> tempSet = getRankRange(13);
		L15r = Collections.unmodifiableSet(tempSet);
	}

	private final int L15d = 13;
	private final int L15g = 5;

	Level level15 = new Level(L15s, L15r, L15d, L15g);

	private static Set<Rank> getRankRange(int range) {
		Set<Rank> temprankarray = new HashSet<Rank>();

		if (range >= 1) {
			temprankarray.add(Rank.ace);
		}
		if (range >= 2) {
			temprankarray.add(Rank.two);
		}
		if (range >= 3) {
			temprankarray.add(Rank.three);
		}
		if (range >= 4) {
			temprankarray.add(Rank.four);
		}
		if (range >= 5) {
			temprankarray.add(Rank.five);
		}
		if (range >= 6) {
			temprankarray.add(Rank.six);
		}
		if (range >= 7) {
			temprankarray.add(Rank.seven);
		}
		if (range >= 8) {
			temprankarray.add(Rank.eight);
		}
		if (range >= 9) {
			temprankarray.add(Rank.nine);
		}
		if (range >= 10) {
			temprankarray.add(Rank.ten);
		}
		if (range >= 11) {
			temprankarray.add(Rank.jack);
		}
		if (range >= 12) {
			temprankarray.add(Rank.queen);
		}
		if (range >= 13) {
			temprankarray.add(Rank.king);
		}

		return temprankarray;
	}

	public ArrayList<Level> getLevels() {

		ArrayList<Level> templevelarray = new ArrayList<Level>();

		templevelarray.add(level1);
		templevelarray.add(level2);
		templevelarray.add(level3);
		templevelarray.add(level4);
		templevelarray.add(level5);
		templevelarray.add(level6);
		templevelarray.add(level7);
		templevelarray.add(level8);
		templevelarray.add(level9);
		templevelarray.add(level10);
		templevelarray.add(level11);
		templevelarray.add(level12);
		templevelarray.add(level13);
		templevelarray.add(level14);
		templevelarray.add(level15);

		return templevelarray;
	}

}
