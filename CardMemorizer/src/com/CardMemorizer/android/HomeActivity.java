package com.CardMemorizer.android;

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
		((Button) findViewById(R.id.start)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HomeActivity.this.startActivity(new Intent(HomeActivity.this, MainActivity.class));
			}
		});
	}
}
