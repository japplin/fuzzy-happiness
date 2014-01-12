package com.CardMemorizer.android.MainNavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.CardMemorizer.android.R;

public class AboutFragment extends Fragment {
	private View mLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.about_page, container, false);
		TextView apacheText = (TextView) mLayout.findViewById(R.id.apache);
		apacheText.setText("Some parts of this software are protected by the Apache License, version 2.0. It can be found at "
				+ "http://www.apache.org/licenses/LICENSE-2.0.html");
		apacheText.setMovementMethod(LinkMovementMethod.getInstance());
		return mLayout;
	}

}
