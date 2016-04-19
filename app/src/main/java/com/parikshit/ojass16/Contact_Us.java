package com.parikshit.ojass16;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by rishavz_sagar on 31-Dec-15.
 */
public class Contact_Us extends Fragment {
    NavigationDrawerFragment.NavigationDrawerCallbacks obj;
    RecyclerView rcv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_contactus, container, false);

        rcv = (RecyclerView) v.findViewById(R.id.contact_rv);
        LinearLayoutManager manager = new LinearLayoutManager(container.getContext());
        rcv.setLayoutManager(manager);
        rcv.setHasFixedSize(false);

        ArrayList<Contact_item> list = new ArrayList<Contact_item>();

        list.add(new Contact_item("ANKIT SHARMA", "TECHNICAL SECRETARY", "08809102121"));
        list.add(new Contact_item("AKASH", "JOINT SECRETARY", "09199859854"));
        list.add(new Contact_item("KUMAR VISHAL", "GENERAL SECRETARY(INTERNAL)", "08102023927"));
        list.add(new Contact_item("SWATHI KIRAN MODALAVALASA", "GENERAL SECRETARY(EXTERNAL)", "09431745417"));
        list.add(new Contact_item("SURESH KUMAR PAREEK", "TREASURER", "07209632032"));
        list.add(new Contact_item("SANKET RAJ", "PLANING AND DEVELOPMENT", "08797534356"));
        list.add(new Contact_item("PAWAN MISHRA", "PLANING AND DEVELOPMENT", "09431314370"));
        list.add(new Contact_item("DHANANJAY KUMAR", "PUBLIC RELATION", "09507664936"));
        list.add(new Contact_item("SUKANTO MUKHERJEE", "PUBLIC RELATION", "09006055439"));
        list.add(new Contact_item("SAKET SAURABH", "PUBLIC RELATION", "09031796750"));
        list.add(new Contact_item("ASTHA", "PUBLIC RELATION", "09199020880"));
        list.add(new Contact_item("SHASHANK KUMAR", "CORPORATE AFFAIRS", "09973702202"));
        list.add(new Contact_item("AKARSH DANG", "CORPORATE AFFAIRS", "09472703591"));
        list.add(new Contact_item("SHAIKH FAYAZ AHAMAD", "CORPORATE AFFAIRS", "08051161441"));
        list.add(new Contact_item("KIRAN KUMAR OBILISETTY", "CORPORATE AFFAIRS", "09472704106"));
        list.add(new Contact_item("PRABHAT PRAKASH", "LOGISTICS", "07209412904"));
        list.add(new Contact_item("K. AKASHDEEP", "LOGISTICS", "09494884477"));
        list.add(new Contact_item("K.V. BHARGAV REDDY", "LOGISTICS", "09472703591"));
        list.add(new Contact_item("ROHIT GUPTA", "EVENT MANAGEMENT", "09693744914"));
        list.add(new Contact_item("SAJAL SACHAN", "EVENT MANAGEMENT", "09471317311"));
        list.add(new Contact_item("IVATURI SANJANA", "EVENT MANAGEMENT", "09031619324"));
        list.add(new Contact_item("DEEPANSHU SRIVASTAVA", "EVENT MANAGEMENT", "07070450420"));
        list.add(new Contact_item("DIVANSHU GUPTA", "CREATIVE TEAMS", "09471378732"));
        list.add(new Contact_item("RAHUL KUMAR", "CREATIVE TEAMS", "09661670006"));
        list.add(new Contact_item("SHRUTI SETH", "CREATIVE TEAMS", "08986714868"));
        list.add(new Contact_item("SAURAV YADAV", "CREATIVE TEAMS", "07209820020"));
        list.add(new Contact_item("HARSH VARDHAN", "WEB AND DESIGNING", "09431786542"));
        list.add(new Contact_item("C S VATSYANAN", "WEB AND DESIGNING", "08409179548"));
        list.add(new Contact_item("KUNAL KUMAR SINGH", "WEB AND DESIGNING", "08083803507"));
        list.add(new Contact_item("SAVYASACHI", "WEB AND DESIGNING", "07209490265"));
        list.add(new Contact_item("NEHA PRAKASH", "WEB AND DESIGNING", "07209778110"));
        list.add(new Contact_item("ROHIT VINEET", "WEB AND DESIGNING", "08102930290"));
        list.add(new Contact_item("CHANDAN KUMAR", "HOSPITALITY", "07209415855"));
        list.add(new Contact_item("AJITESH BANERJEE", "HOSPITALITY", "08674884803"));
        list.add(new Contact_item("GOKUL REDDY", "HOSPITALITY", "09472703591"));
        list.add(new Contact_item("ANKITA SINGH", "HOSPITALITY", "08986760739"));
        list.add(new Contact_item("ANIT DIWAKAR", "ROBOTICS", "09031741117"));
        list.add(new Contact_item("SAKET SATYARTHI", "ROBOTICS", "08507083580"));
        list.add(new Contact_item("ANKIT KUMAR SHRIVASTAVA", "ROBOTICS", "09472706227"));

        rcv.setAdapter(new Contact_Recycle_Adapter(list));
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        obj = (NavigationDrawerFragment.NavigationDrawerCallbacks) activity;
    }
}
