package com.parikshit.ojass16;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {

    TextView about, coordinator;
    NavigationDrawerFragment.NavigationDrawerCallbacks obj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        about = (TextView) v.findViewById(R.id.about);
        coordinator = (TextView) v.findViewById(R.id.coordinator);

        Typeface t = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ojass_font.ttf");
        about.setTypeface(t);
        coordinator.setTypeface(t);


        about.setText(Activity2.str[0]);
        coordinator.setText(Activity2.str[4]);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        obj = (NavigationDrawerFragment.NavigationDrawerCallbacks) activity;
    }
}

