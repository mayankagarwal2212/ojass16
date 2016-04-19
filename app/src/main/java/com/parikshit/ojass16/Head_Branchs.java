package com.parikshit.ojass16;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Head_Branchs extends Fragment {

    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_head__branchs, container, false);

        tv = (TextView) v.findViewById(R.id.head);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BohemianTypewriter.ttf");
        tv.setTypeface(typeFace);
        tv.setTextColor(Color.WHITE);
        tv.setText(Activity2.current_head);
        // Inflate the layout for this fragment
        return v;
    }


}
