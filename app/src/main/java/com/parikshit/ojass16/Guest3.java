package com.parikshit.ojass16;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Guest3 extends Fragment {
    NavigationDrawerFragment.NavigationDrawerCallbacks obj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.guest3, container, false);
        Log.d("erew", "Guest3 created");
        return v;
    }
}