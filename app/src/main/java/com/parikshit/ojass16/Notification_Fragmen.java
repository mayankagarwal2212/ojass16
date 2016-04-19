package com.parikshit.ojass16;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Notification_Fragmen extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    String message[];
    ImageButton img;
    ImageButton ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v;
        v = inflater.inflate(R.layout.branch_event_frag3, container, false);

        img = (ImageButton) v.findViewById(R.id.ghar);
        ref = (ImageButton) v.findViewById(R.id.ref);
        img.setOnClickListener(this);
        if (Activity2.pgroup == 0)
            img.setVisibility(View.VISIBLE);
        else
            img.setVisibility(View.INVISIBLE);

        ref.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Activity2.update();

                        if (Activity2.pgroup != 0)
                            getFragmentManager().beginTransaction().replace(R.id.frame1, new Notification_Fragmen()).commit();
                        else
                            getFragmentManager().beginTransaction().replace(R.id.cont, new Notification_Fragmen()).commit();
                    }
                }
        );

        //Toast.makeText(getActivity(), Activity2.stringMessage[0], Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);

        switch (Activity2.pgroup) {
            case 0:
                message = Activity2.stringMessage[0];
                //Log.d("hello", "Inside my message");
                break;
            case 2:
                message = Activity2.stringMessage[2];
                //Log.d("hello", "Inside my message");
                break;
            case 3:
                message = Activity2.stringMessage[3];
                break;
            case 4:
                message = Activity2.stringMessage[4];
                break;
            case 5:
                message = Activity2.stringMessage[5];
                //Log.d("hello", "Inside my message");
                break;
            case 6:
                message = Activity2.stringMessage[6];
                //Log.d("hello", "Inside my message");
                break;
            case 7:
                message = Activity2.stringMessage[7];
                break;
            case 8:
                message = Activity2.stringMessage[8];
                break;
            case 9:
                message = Activity2.stringMessage[9];
                //Log.d("hello", "Inside my message");
                break;
            case 10:
                message = Activity2.stringMessage[10];
                //Log.d("hello", "Inside my message");
                break;
            case 11:
                message = Activity2.stringMessage[11];
                break;
            case 12:
                message=Activity2.stringMessage[12];
                break;
            case 13:
                message = Activity2.stringMessage[13];
                break;
            case 14:
                message = Activity2.stringMessage[14];
                //Log.d("hello", "Inside my message");
                break;
            case 15:
                message = Activity2.stringMessage[15];
                //Log.d("hello", "Inside my message");
                break;
            case 16:
                message = Activity2.stringMessage[16];
                break;
            case 17:
                message = Activity2.stringMessage[17];
                break;
            case 18:
                message = Activity2.stringMessage[18];
                break;
        }

        ArrayList<Notification_item> itemList = new ArrayList<>();


        //Concentrate here

        int i;
        for (i = 0; i < Activity2.messageLength[Activity2.pgroup] && i < 20; i++)
        {
            if(itemList!=null&&message!=null&&Activity2.images!=null)
            itemList.add(new Notification_item(Activity2.images[Activity2.pgroup][i], message[i]));
            else
            {
                if(Activity2.defImage!=null&&message!=null)
                itemList.add(new Notification_item(Activity2.defImage, message[i]));
            }
        }

        recyclerView.setAdapter(new RecycleAdapter(itemList));

        return v;
    }

    @Override
    public void onClick(View v) {
        img.setVisibility(View.INVISIBLE);
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.right_in, R.animator.right_out, R.animator.left_in, R.animator.left_out).replace(R.id.cont, new Contents_frag()).commit();
    }
}
