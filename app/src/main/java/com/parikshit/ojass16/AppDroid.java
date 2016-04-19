package com.parikshit.ojass16;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by rishavz_sagar on 30-Dec-15.
 */
public class AppDroid extends Fragment {
    NavigationDrawerFragment.NavigationDrawerCallbacks obj;

    static TabHost tb;
    TabHost.TabSpec spec1;
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_app_droid, container, false);

        tb = (TabHost) v.findViewById(R.id.tabhost_2);
        tb.setup();
        spec1 = tb.newTabSpec("tag1");
        spec1.setContent(R.id.container2);
        spec1.setIndicator("About");
        tb.addTab(spec1);
        spec1 = tb.newTabSpec("tag2");
        spec1.setContent(R.id.container_2);
        spec1.setIndicator("Details");
        tb.addTab(spec1);
        spec1 = tb.newTabSpec("tag3");
        spec1.setContent(R.id.container_2a);
        spec1.setIndicator("Rules");
        tb.addTab(spec1);
        spec1 = tb.newTabSpec("tag4");
        spec1.setContent(R.id.container_2b);
        spec1.setIndicator("Prizes");
        tb.addTab(spec1);

        int i;

        tv = (TextView) v.findViewById(R.id.text);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.w("touched", "touched");
                return false;
            }
        });

        for (i = 0; i < tb.getTabWidget().getTabCount(); i++)
            tb.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab_background);
        tb.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            int currentTab = 0;

            @Override
            public void onTabChanged(String tabId) {
                View currentView = tb.getCurrentView();
                if (tb.getCurrentTab() > currentTab) {
                    currentView.setAnimation(inFromRightAnimation());
                } else {
                    currentView.setAnimation(outToRightAnimation());
                }

                currentTab = tb.getCurrentTab();
            }
        });

        return v;
    }

    public Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(240);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    public Animation outToRightAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(240);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
}