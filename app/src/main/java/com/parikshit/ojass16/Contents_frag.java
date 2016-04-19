package com.parikshit.ojass16;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by rishavz_sagar on 14-Jan-16.
 */
public class Contents_frag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_frag, container, false);

        TextView tv= (TextView) v.findViewById(R.id.tvcont);
        Typeface t = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ojass_font.ttf");
        tv.setTypeface(t);

        TextView txt = (TextView) v.findViewById(R.id.heading);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/warpthruster.ttf");
        txt.setTypeface(typeFace);


        ((ImageButton) v.findViewById(R.id.fb)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.right_in, R.animator.right_out, R.animator.left_in, R.animator.left_out)
                        .replace(R.id.container, new Notification_Fragmen()).commit();
            }
        });
        return v;
    }
}
