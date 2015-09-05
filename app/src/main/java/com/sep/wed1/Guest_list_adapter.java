package com.sep.wed1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kavi on 8/27/15.
 */

//extend arrayadapter as a  guest for get guest details as a object
public class Guest_list_adapter extends ArrayAdapter<guest> {

    public Guest_list_adapter(Context context, ArrayList<guest> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        guest g = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.guest_item, parent, false);
        }


        //initialize the text view
        TextView gname = (TextView) convertView.findViewById(R.id.guestname);
        TextView gtbno = (TextView) convertView.findViewById(R.id.tbno);
        TextView gside = (TextView) convertView.findViewById(R.id.side);
        TextView ginvite = (TextView) convertView.findViewById(R.id.invitessent);
        TextView gattending = (TextView) convertView.findViewById(R.id.attending);


        //set value for text view
        gtbno.setText(g.getTbno());
        gname.setText(g.getName());
        gside.setText(g.getSide());

        //set text

        if(g.getInvite().equals("Yes"))
        ginvite.setText("V");
        else if(g.getInvite().equals("No"))
            ginvite.setText("X");
        if(g.getAttending().equals("Yes"))
            gattending.setText("V");
        else if(g.getAttending().equals("No"))
            gattending.setText("X");
        else
            gattending.setText("-");


        return convertView;
    }
}