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
public class Rules extends Fragment {

    NavigationDrawerFragment.NavigationDrawerCallbacks obj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rules, container, false);

        TextView rule = (TextView) v.findViewById(R.id.rules);
        rule.setText(Activity2.str[2]);

        Typeface t = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ojass_font.ttf");
        rule.setTypeface(t);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        obj = (NavigationDrawerFragment.NavigationDrawerCallbacks) activity;
    }
}
