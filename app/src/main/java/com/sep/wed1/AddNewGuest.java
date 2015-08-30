package com.sep.wed1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by kavi on 6/19/2015.
 */


public class AddNewGuest extends ActionBarActivity {

    int code;
    String line;
    InputStream is;
    String Fname, Lname, result, Tableseatno, Side, InviteSent, Attending;
    String sideindex ="Bride";
    String attentdingindex="Yes";
    String invitesentindex="Yes";
    // RadioGroup InviteSent,Attending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewguest);

        final EditText fname = (EditText) findViewById(R.id.editText_Fname); final RadioGroup side = (RadioGroup) findViewById(R.id.radioSide);
        final RadioGroup invitesent = (RadioGroup) findViewById(R.id.radioInvitesSent);
        final RadioGroup attending = (RadioGroup) findViewById(R.id.radioAttending);

        final EditText lname = (EditText) findViewById(R.id.editText_Lname);
        final EditText tsno = (EditText) findViewById(R.id.editText_SeatNo);
//

        Button b1 = (Button) findViewById(R.id.Button_OK);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Fname = fname.getText().toString();
                Lname = lname.getText().toString();
                Tableseatno = tsno.getText().toString();

               new SomeTask().execute();

            }
        });

        Button b2 = (Button) findViewById(R.id.Button_Cancel);

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });


    }

//        final GuestInfoDB db = new GuestInfoDB(this);
//
//
//
//       // addListenerOnButton();
//
//
////        bride = (RadioButton)findViewById(R.id.radioButton_Bride);
////        Bride = (TextView)findViewById(R.id.textView_Bride);
////        groom = (RadioButton)findViewById(R.id.radioButton_Bride);
////        Groom = (TextView)findViewById(R.id.textView_Groom);
//        RadioSide = (RadioGroup) findViewById(R.id.radioSide);
//        InvitesSent = (RadioGroup)findViewById(R.id.radioInvitesSent);
//        Attending = (RadioGroup)findViewById(R.id.radioAttending);
//        Ok = (Button)findViewById(R.id.Button_OK);
//
//        Ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                GuestName = (TextView)findViewById(R.id.textView_GuestName);
//                Fname = (EditText)findViewById(R.id.editText_Fname);
//                Lname = (EditText)findViewById(R.id.editText_Lname);
//                TableSeatNo = (TextView)findViewById(R.id.textView_SeatNo);
//                SeatNo = (EditText)findViewById(R.id.editText_SeatNo);
//                Side = (TextView)findViewById(R.id.textView_Side);
//
//
//
//            }
//        });
//
//        Cancel = (Button)findViewById(R.id.Button_Cancel);
//    }
//
//
//
//    }
//}

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioBride:
                if (checked)
                   sideindex  = "Bride";
                    break;
            case R.id.radioGroom:
                if (checked)
                    sideindex  = "Groom";
                    break;
            case R.id.radioButtonAttendingYes:
                if (checked)
                    attentdingindex  = "Yes";
                break;
            case R.id.radioButtonAttendingNo:
                if (checked)
                    attentdingindex  = "No";
                break;
            case R.id.radioButtonAttendingUndecided:
                if (checked)
                    attentdingindex  = "Undecided";
                break;
            case R.id.radioButtonInvitesYes:
                if (checked)
                    invitesentindex  = "Yes";
                break;
            case R.id.radioButtonInvitesNo:
                if (checked)
                    invitesentindex  = "No";
                break;

        }
    }

    public void addnewguest() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("firstname", Fname));
        nameValuePairs.add(new BasicNameValuePair("lastname", Lname));
        nameValuePairs.add(new BasicNameValuePair("tableseatno", Tableseatno));
        nameValuePairs.add(new BasicNameValuePair("side",sideindex));
        nameValuePairs.add(new BasicNameValuePair("invitesent",invitesentindex));
        nameValuePairs.add(new BasicNameValuePair("attending",attentdingindex));


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://weddingres.site11.com/kaveesha/AddNewGuest.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");

        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            runOnUiThread(new Runnable()
            {
                public void run()
                {
            Toast.makeText(getApplicationContext(), "Unable to connect to the server",
                    Toast.LENGTH_LONG).show();
                }
            });
        }

        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success " + result);
        } catch (Exception e) {
            Log.e("Fail 2", e.toString());
        }

        try {
            JSONObject json_data = new JSONObject(result);
            Log.e("Check",result.toString());
            code = (json_data.getInt("code"));

            if (code == 1) {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                Toast.makeText(getBaseContext(), "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",

                        Toast.LENGTH_LONG).show();
                    }
                });
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }

    // Dialog
    public class SomeTask extends AsyncTask<Void, Void, Integer> {
        private ProgressDialog Dialog = new ProgressDialog(AddNewGuest.this);

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Please Wait");
            Dialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            //Task for doing something
            addnewguest();
            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {

          Dialog.hide();

        }
    }
}


