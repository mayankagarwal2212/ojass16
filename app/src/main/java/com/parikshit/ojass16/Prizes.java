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
public class Prizes extends Fragment {

    NavigationDrawerFragment.NavigationDrawerCallbacks obj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_prizes, container, false);

        TextView prize = (TextView) v.findViewById(R.id.prizes);
        prize.setText(Activity2.str[3]);

        Typeface t = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ojass_font.ttf");
        prize.setTypeface(t);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        obj = (NavigationDrawerFragment.NavigationDrawerCallbacks) activity;
    }
}
