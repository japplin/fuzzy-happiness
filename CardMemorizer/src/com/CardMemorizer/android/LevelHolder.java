package com.CardMemorizer.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.CardMemorizer.android.Card.Rank;
import com.CardMemorizer.android.Card.Suit;

public class LevelHolder {

	public static final int CUSTOM_GAME = -1;
	
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
		levels.add(new Level(getSuitSet(1), getRankSet(3), 3, 1, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(2), 4, 1, levels.size()));
		levels.add(new Level(getSuitSet(1), getRankSet(6), 6, 1, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(3), 6, 2, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(2), 6, 2, levels.size()));
		levels.add(new Level(getSuitSet(1), getRankSet(8), 8, 2, levels.size()));
		levels.add(new Level(getSuitSet(4), getRankSet(2), 8, 2, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(3), 9, 2, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(5), 10, 3, levels.size()));
		levels.add(new Level(getSuitSet(1), getRankSet(13), 13, 3, levels.size()));
		levels.add(new Level(getSuitSet(2), getRankSet(7), 14, 3, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(5), 15, 4, levels.size()));
		levels.add(new Level(getSuitSet(4), getRankSet(4), 16, 4, levels.size()));
		levels.add(new Level(getSuitSet(3), getRankSet(6), 18, 4, levels.size()));
		levels.add(new Level(getSuitSet(4), getRankSet(5), 20, 5, levels.size()));
	}
	
	public Level getLevel(int id) {
		if (id == CUSTOM_GAME) {
			return null;
		}
		return levels.get(id);
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
