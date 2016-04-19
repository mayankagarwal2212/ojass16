package com.parikshit.ojass16;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rishavz_sagar on 15-Jan-16.
 */
public class Contact_Recycle_Adapter extends RecyclerView.Adapter<Contact_Recycle_Adapter.view_holder> {

    ArrayList<Contact_item> mess;

    public Contact_Recycle_Adapter(ArrayList<Contact_item> mess) {
        this.mess = mess;
    }

    CardView cv;

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indivi_contact, parent, false);
        cv = (CardView) v.findViewById(R.id.contact_cv);
        view_holder vh = new view_holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(view_holder holder, final int position) {
        holder.tv1.setText(mess.get(position).st1);
        holder.tv2.setText(mess.get(position).st2);
        holder.tv3.setText(mess.get(position).ph);
        holder.current = mess.get(position);
    }

    @Override
    public int getItemCount() {
        return mess.size();
    }

    public static class view_holder extends RecyclerView.ViewHolder {
        public Contact_item current;
        CardView cardView;
        TextView tv1, tv2, tv3;

        public view_holder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.contact_cv);
            tv1 = (TextView) v.findViewById(R.id.name_tv);
            tv2 = (TextView) v.findViewById(R.id.post_tv);
            tv3 = (TextView) v.findViewById(R.id.num_call);

        }

    }
}
