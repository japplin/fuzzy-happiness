package com.CardMemorizer.android.Adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CardMemorizer.android.CardMemorizerSavedState;
import com.CardMemorizer.android.Level;
import com.CardMemorizer.android.R;

public class LevelAdapter extends BaseAdapter {

	private ArrayList<Level> levels;
	private Activity activity;

	public LevelAdapter(Activity activity) {
		this.levels = new ArrayList<Level>();
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
			relativeLayout = (RelativeLayout) inflater.inflate(R.layout.level_icon, parent, false);
		} else {
			relativeLayout = (RelativeLayout) convertView;
		}
		TextView textView = (TextView) relativeLayout.findViewById(R.id.level_name);
		if (levels.get(position).hasBeenCompleted()) {
			relativeLayout.setBackgroundResource(R.color.Red);
			relativeLayout.findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
			textView.setVisibility(View.GONE);
		} else {
			relativeLayout.setBackgroundResource(R.color.DarkGray);
			relativeLayout.findViewById(R.id.checkmark).setVisibility(View.GONE);
			textView.setText("" + position);
			textView.setTextColor(activity.getResources().getColor(R.color.White));
		}

		relativeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CardMemorizerSavedState.getInstance().loadLevel(getItem(position), activity);
			}
		});
		return relativeLayout;
	}

}
