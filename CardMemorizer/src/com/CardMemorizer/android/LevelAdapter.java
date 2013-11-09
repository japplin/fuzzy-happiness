package com.CardMemorizer.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LevelAdapter extends BaseAdapter {

	private ArrayList<Level> levels;
	private Activity activity;

	public LevelAdapter(Activity activity, ArrayList<Level> levels) {
		this.levels = levels;
		this.activity = activity;
	}

	public void setSetLevels(ArrayList<Level> levels) {
		this.levels = levels;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return levels.size();
	}

	@Override
	public Level getItem(int position) {
		return levels.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public RelativeLayout getView(final int position, View convertView, ViewGroup parent) {
		RelativeLayout relativeLayout;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			relativeLayout = (RelativeLayout) inflater.inflate(R.layout.level_icon, null);
		} else {
			relativeLayout = (RelativeLayout) convertView;
		}
		ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.bg_color);

		if (levels.get(position).hasBeenCompleted()) {
			imageView.setImageResource(R.color.blue);
		} else {
			imageView.setImageResource(R.color.LightSlateGray);
		}
		TextView textView = (TextView) relativeLayout.findViewById(R.id.level_name);
		textView.setText("" + position);
		relativeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CardMemorizerSavedState.getInstance().loadLevel(getItem(position), activity);
			}
		});
		return relativeLayout;
	}

}
