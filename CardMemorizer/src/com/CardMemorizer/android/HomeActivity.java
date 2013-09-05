package com.CardMemorizer.android;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);
		((Button) findViewById(R.id.level_mode)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomeActivity.this, LevelBrowser.class));
			}
		});

		((Button) findViewById(R.id.custom_mode)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomeActivity.this, CustomGameCreation.class));
			}
		});

		((Button) findViewById(R.id.about)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomeActivity.this, AboutPage.class));
			}
		});
		int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this)) {
		case ConnectionResult.SUCCESS:
			break;
		case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
		case ConnectionResult.SERVICE_DISABLED:
			GooglePlayServicesUtil.getErrorDialog(errorCode, this, -1);
			break;
		default:
			break;
		}
	}
}
