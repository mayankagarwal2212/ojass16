package com.parikshit.ojass16;

/**
 * Created by parikshit on 1/21/2016.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                About tab1 = new About();
                return tab1;
            case 1:
                Details tab2 = new Details();
                return tab2;
            case 3:
                Prizes tab4 = new Prizes();
                return tab4;
            case 2:
                Rules tab3 = new Rules();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
