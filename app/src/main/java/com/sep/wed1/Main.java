package com.sep.wed1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kavi on 6/19/2015.
 */
public class Main extends ActionBarActivity
{

    Button GuestList,NewGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GuestList = (Button)findViewById(R.id.button_MyGuestList);
        NewGuest = (Button)findViewById(R.id.button_NewGuest);

        NewGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main.this,AddNewGuest.class);
                startActivity(i);
            }
        });
        GuestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main.this,Guest_activity.class);
                startActivity(i);
            }
        });


    }
}
