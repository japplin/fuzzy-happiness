package com.CardMemorizer.android;

import android.app.Activity;
import android.content.Intent;

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
	
}
