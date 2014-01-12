package com.CardMemorizer.android;

import android.app.Activity;
import android.content.Intent;

import com.CardMemorizer.android.MainNavigation.HomeActivity;
import com.CardMemorizer.android.MainNavigation.CustomGameCreation;
import com.CardMemorizer.android.MainNavigation.HomeActivity;
import com.CardMemorizer.android.MainNavigation.LevelBrowserFragment;

public class NavigationHelper {

	private static NavigationHelper instance;

	public NavigationHelper() {

	}

	public static NavigationHelper getInstance() {
		if (instance == null) {
			instance = new NavigationHelper();
		}
		return instance;
	}

	public void goToHomeActivity(Activity activity) {
		Intent intent = new Intent(activity, HomeActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	public void goToLevelBrowserActivity(Activity activity) {
		Intent intent = new Intent(activity, LevelBrowserFragment.class);
		activity.startActivity(intent);
		activity.finish();
	}

	public void goToCustomGameCreation(Activity activity) {
		Intent intent = new Intent(activity, CustomGameCreation.class);
		activity.startActivity(intent);
		activity.finish();
	}
	
	public void goToAboutPage(Activity activity) {
		Intent intent = new Intent(activity, HomeActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

}
