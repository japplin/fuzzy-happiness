package com.CardMemorizer.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);

		ImageView hearticon;
		ImageView spadeicon;
		ImageView diamondicon;
		ImageView clubicon;
		ImageView altlogo;

		hearticon = (ImageView) findViewById(R.id.heart);
		hearticon.setImageResource(R.drawable.hearts);

		spadeicon = (ImageView) findViewById(R.id.spade);
		spadeicon.setImageResource(R.drawable.spades);

		diamondicon = (ImageView) findViewById(R.id.diamond);
		diamondicon.setImageResource(R.drawable.diamonds);

		clubicon = (ImageView) findViewById(R.id.club);
		clubicon.setImageResource(R.drawable.clubs);

		altlogo = (ImageView) findViewById(R.id.altlogo);
		altlogo.setImageResource(R.drawable.altlogo);

		((Button) findViewById(R.id.level_mode)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NavigationHelper.getInstance().goToLevelBrowserActivity(HomeActivity.this);
			}
		});

		((Button) findViewById(R.id.custom_mode)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NavigationHelper.getInstance().goToCustomGameCreation(HomeActivity.this);
			}
		});

		((Button) findViewById(R.id.about)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NavigationHelper.getInstance().goToAboutPage(HomeActivity.this);
			}
		});
	}
	
}
