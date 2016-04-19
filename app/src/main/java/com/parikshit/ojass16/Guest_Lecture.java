package com.parikshit.ojass16;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Guest_Lecture extends Fragment {


    ImageView im1,im2,im3;
    TextView tv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_guest__lecture, container, false);

        im1= (ImageView) v.findViewById(R.id.im1);
        im1.setBackgroundResource(R.drawable.ojass16_logo);
        im2= (ImageView) v.findViewById(R.id.im2);
        im3= (ImageView) v.findViewById(R.id.im3);
        tv1= (TextView) v.findViewById(R.id.guest_n);
        tv1.setText("DAY 1");

        ViewPager pager= (ViewPager) v.findViewById(R.id.page_guest);
        pager.setAdapter(new Guest(getFragmentManager()));
        pager.setOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if(position==0)
                        {
                            im1.setBackgroundResource(R.drawable.ojass16_logo);
                            im2.setBackgroundResource(R.drawable.ojass16_logo_chota);
                            im3.setBackgroundResource(R.drawable.ojass16_logo_chota);
                            tv1.setText("DAY 1");
                        }

                        else if(position==1)
                        {
                            im2.setBackgroundResource(R.drawable.ojass16_logo);
                            im1.setBackgroundResource(R.drawable.ojass16_logo_chota);
                            im3.setBackgroundResource(R.drawable.ojass16_logo_chota);
                            tv1.setText("DAY 2");
                        }
                        else if (position==2)
                        {
                            im3.setBackgroundResource(R.drawable.ojass16_logo);
                            im2.setBackgroundResource(R.drawable.ojass16_logo_chota);
                            im1.setBackgroundResource(R.drawable.ojass16_logo_chota);
                            tv1.setText("DAY 3");
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
        return v;
    }

    class Guest extends FragmentStatePagerAdapter
    {

        public Guest(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0)
            {
                return new Guest1();
            }

            else if(position==1)
            {
                return new Guest2();
            }
            else if (position==2)
            {
                return new Guest3();
            }
            else
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
