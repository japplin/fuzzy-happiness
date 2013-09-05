package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class LevelHolder {

	private ArrayList<Level> levels;

	private static LevelHolder instance;

	public static LevelHolder getInstance() {
		if (instance == null) {
			instance = new LevelHolder();
		}
		return instance;

	}

	private LevelHolder() {
		levels = new ArrayList<Level>();
		levels.add(new Level(getSuitSet(1), getRankSet(3), 3, 0, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(2), 4, 0, levels.size()));
		levels.add(new Level(getSuitSet(1), getRankSet(6), 6, 0, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(3), 6, 1, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(2), 6, 1, levels.size()));
		levels.add(new Level(getSuitSet(1), getRankSet(8), 8, 1, levels.size()));
		levels.add(new Level(getSuitSet(4), getRankSet(2), 8, 1, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(3), 9, 1, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(5), 10, 2, levels.size()));
		levels.add(new Level(getSuitSet(1), getRankSet(13), 13, 2, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(7), 14, 2, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(5), 15, 3, levels.size()));
		levels.add(new Level(getSuitSet(4), getRankSet(4), 16, 3, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(6), 18, 3, levels.size()));
		levels.add(new Level(getSuitSet(4), getRankSet(5), 20, 5, levels.size()));
	}

	private Set<Rank> getRankSet(int range) {
		return new HashSet<Rank>(Arrays.asList(Rank.values()).subList(0, range));
	}

	private Set<Suit> getSuitSet(int range) {
		return new HashSet<Suit>(Arrays.asList(Suit.values()).subList(0, range));
	}

	public ArrayList<Level> getLevels() {
		return levels;
	}

}
