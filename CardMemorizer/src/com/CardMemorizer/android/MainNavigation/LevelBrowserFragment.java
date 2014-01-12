package com.CardMemorizer.android.MainNavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.CardMemorizer.android.LevelHolder;
import com.CardMemorizer.android.R;
import com.CardMemorizer.android.Adapters.LevelAdapter;

public class LevelBrowserFragment extends Fragment {

	private GridView mLevelGrid;
	private LevelAdapter mAadapter;
	private View mLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.level_browser, container, false);
		return mLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mAadapter = new LevelAdapter(getActivity());
		mLevelGrid = (GridView) mLayout.findViewById(R.id.level_grid);
		mLevelGrid.setAdapter(mAadapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		mAadapter.setSetLevels(LevelHolder.getInstance().getLevels());
	}
	
}
