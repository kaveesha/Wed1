package com.sep.wed1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sep.wed1.Validators.PersonNameValidator;
import com.sep.wed1.Validators.RequiredFieldValidator;
import com.sep.wed1.Validators.SeatNumberValidator;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Guest_activity extends ActionBarActivity {
    private static final String guest = "guest";
    JSONArray guestarray=null;
    private static final String SUCCESS = "code";
    JSONParser jsonParser = new JSONParser();
    String url;
    String url1;
    String url2;
    ArrayList<com.sep.wed1.guest> arrayOfguests ;
    ListView guestlist;
    String gid;
    String name;
    String lastname1;
    String tbno;
    String side;
    String invit;
    String attnd;
    AlertDialog alert;
    ProgressDialog p;
    EditText inputSearch;
    Spinner filter;
    int click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list);
        click=0;
        url="http://weddingres.site11.com/kaveesha/loadguest.php";
        url1="http://weddingres.site11.com/kaveesha/updateguest.php";
        url2="http://weddingres.site11.com/kaveesha/deleteguest.php";
        arrayOfguests=new ArrayList<com.sep.wed1.guest>();
        guestlist=(ListView)findViewById(R.id.guestlist);
        new loadguest().execute();

        Button sort=(Button)findViewById(R.id.Button_Sort);


        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0)
                {
                    click=1;
                    Collections.sort(arrayOfguests, new Comparator<com.sep.wed1.guest>() {
                        @Override
                        public int compare(com.sep.wed1.guest guest, com.sep.wed1.guest t1) {


                            return guest.getName().compareToIgnoreCase(t1.getName());//Ascending order.





                        }
                    });
            }
                else
                {
                    click=0;
                    Collections.sort(arrayOfguests, new Comparator<com.sep.wed1.guest>() {
                        @Override
                        public int compare(com.sep.wed1.guest guest, com.sep.wed1.guest t1) {


                            return (guest.getName().compareToIgnoreCase(t1.getName()) * (-1));//Descending order.


//
                        }
                    });
                }
                Guest_list_adapter adapter1 = new Guest_list_adapter(Guest_activity.this, arrayOfguests);


                guestlist.setAdapter(null);
                guestlist.setAdapter(adapter1);


                adapter1.notifyDataSetChanged();
            }
        });

        guestlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                com.sep.wed1.guest entry = (com.sep.wed1.guest) adapterView.getItemAtPosition(i);
               gid=entry.getId()+"";
                String  name1=entry.getName();
                String   lastname11=entry.getLastname();
                String   tbno1=entry.getTbno();
                String   side1=entry.getSide();
                String  invit1=entry.getInvite();
                String  attnd1=entry.getAttending();


                AlertDialog.Builder builder = new AlertDialog.Builder(Guest_activity.this);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Guest_activity.this);
                // Get the layout inflater/
                LayoutInflater inflater = getLayoutInflater();
                LayoutInflater inflater1 = getLayoutInflater();
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View v1=inflater1.inflate(R.layout.editbtn, null);
                builder1.setView(v1);
                final AlertDialog alert2=builder1.create();
                alert2.show();




               final View v = inflater.inflate(R.layout.activity_update_guest, null);
                builder.setView(v);
               final TextView firstname=(TextView)v.findViewById(R.id.UeditText_Fname);
                final   TextView lastname=(TextView)v.findViewById(R.id.UeditText_Lname);
                final   TextView tblno=(TextView)v.findViewById(R.id.UeditText_SeatNo);

                final   RadioButton tbride=(RadioButton)v.findViewById(R.id.UradioBride);
                final   RadioButton tgroom=( RadioButton)v.findViewById(R.id.UradioGroom);
                final    RadioButton tinviteyes=( RadioButton)v.findViewById(R.id.UradioButtonInvitesYes);
                final   RadioButton tinviteno=(RadioButton)v.findViewById(R.id.UradioButtonInvitesNo);
                final    RadioButton tatendingyes=( RadioButton)v.findViewById(R.id.UradioButtonAttendingYes);
                final     RadioButton tattendingno=(RadioButton)v.findViewById(R.id.UradioButtonAttendingNo);
                final     RadioButton tattendingnone=(RadioButton)v.findViewById(R.id.UradioButtonAttendingUndecided);

                firstname.setText(name1);
                lastname.setText(lastname11);
                tblno.setText(tbno1);

                if(side1.equals("Groom"))
                {
                    tgroom.setChecked(true);
                }
                else
                {
                    tbride.setChecked(true);
                }
                if(invit1.equals("Yes"))
                {
                    tinviteyes.setChecked(true);
                }
                else{
                    tinviteno.setChecked(true);
                }
                if(attnd1.equals("Yes"))
                {
                    tatendingyes.setChecked(true);
                }
                else if(attnd1.equals("No"))
                {
                    tattendingno.setChecked(true);
                }
                else
                {
                    tattendingnone.setChecked(true);
                }
                alert=   builder.create();
                Button edit=(Button)v1.findViewById(R.id.edit);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.show();
                        alert2.dismiss();
                    }
                });
                Button dlt=(Button)v1.findViewById(R.id.delete);
                dlt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final  AlertDialog.Builder builder1 = new AlertDialog.Builder(Guest_activity.this);
                        builder1.setMessage("Are you sure you want Delete?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        new delete().execute();
                                        Toast.makeText(Guest_activity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                        alert.dismiss();
                                        dialog.cancel();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert1 = builder1.create();
                        alert1.show();
                        alert2.dismiss();
                    }
                });



                Button update=(Button)v.findViewById(R.id.UButton_Update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        name=firstname.getText().toString();
                         lastname1=lastname.getText().toString();
                       tbno=tblno.getText().toString();
                        if(tbride.isChecked())
                         side="Bride";
                        else
                            side="Groom";
                        if(tinviteyes.isChecked())
                        invit="Yes";
                        else
                        invit="No";
                        if(tatendingyes.isChecked())
                        attnd="Yes";
                        else if(tattendingno.isChecked())
                            attnd="No";
                        else
                            attnd="Undecided";
                      final  AlertDialog.Builder builder1 = new AlertDialog.Builder(Guest_activity.this);
                        builder1.setMessage("Are you sure you want Update?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        final EditText fname=(EditText)v.findViewById(R.id.UeditText_Fname);
                                        final EditText lname=(EditText)v.findViewById(R.id.UeditText_Lname);
                                        final EditText tablno=(EditText)v.findViewById(R.id.UeditText_SeatNo);
                                       String Fname = fname.getText().toString();
                                        String Lname = lname.getText().toString();
                                        String Tableseatno = tablno.getText().toString();

                                        boolean isValid = true;
                                        if (RequiredFieldValidator.isEmpty(Fname))
                                        {
                                            fname.setError("Please enter your First Name");
                                            isValid = false;
                                        }


                                        if (!SeatNumberValidator.isSeatNoValid(Tableseatno))
                                        {
                                            tablno.setError("Please enter a valid Seat Number");
                                            isValid = false;
                                        }
                                        if (!PersonNameValidator.isNameValid(Fname)) {
                                            fname.setError("Please enter a valid Name");
                                            isValid = false;
                                        }
                                        if (!PersonNameValidator.isNameValid(Lname)) {
                                            lname.setError("Please enter a valid Name");
                                            isValid = false;
                                        }


                                        if(isValid) {
                                            new update().execute();
                                            Toast.makeText(Guest_activity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                            alert.dismiss();
                                            dialog.cancel();
                                            finish();
                                            startActivity(getIntent());
                                        }



                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert1 = builder1.create();
                        alert1.show();


                    }



                });
                Button cancel=(Button)v.findViewById(R.id.UButton_Cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                    }
                });





            }
        });
        inputSearch=(EditText)findViewById(R.id.inputSearch);
        filter=(Spinner)findViewById(R.id.filter);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                // When user changed the Text

                int textlength = cs.length();
                ArrayList<guest> tempArrayList = new ArrayList<guest>();
                for (guest g : arrayOfguests) {
                    if (filter.getSelectedItemPosition() == 0) {

                        if (g.getAttending().toLowerCase().contains(cs.toString().toLowerCase())) {
                            tempArrayList.add(g);
                        }
                    } else if (filter.getSelectedItemPosition() == 1) {

                        if (g.getInvite().toLowerCase().contains(cs.toString().toLowerCase())) {
                            tempArrayList.add(g);
                        }

                    } else if (filter.getSelectedItemPosition() == 2) {
                        if (textlength <= g.getName().length()) {
                            if (g.getName().toLowerCase().contains(cs.toString().toLowerCase())) {
                                tempArrayList.add(g);
                            }
                        }

                    } else if (filter.getSelectedItemPosition() == 3) {

                        if (g.getSide().toLowerCase().contains(cs.toString().toLowerCase())) {
                            tempArrayList.add(g);

                        }
                    }


                }
                Guest_list_adapter mAdapter = new Guest_list_adapter(Guest_activity.this, tempArrayList);
                guestlist.setAdapter(mAdapter);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class loadguest extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            p = new ProgressDialog(Guest_activity.this);
            p.setCancelable(false);
            p.setMessage("Loading....");
            p.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {



            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();




            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(SUCCESS);

//               arrayOfUsers.clear();
                if (success == 1) {
                    guestarray = json.getJSONArray(guest);

                    arrayOfguests.clear();
                    for (int i = guestarray.length()-1; i >=0; i--) {


                        JSONObject jsnobj =guestarray.getJSONObject(i);


                        String firstname = jsnobj.getString("firstname");
                        String lastname = jsnobj.getString("lastname");
                        String tbno = jsnobj.getString("tableseatno");
                        int User_Id = jsnobj.getInt("Id");
                        String side=jsnobj.getString("side");
                        String invitesent=jsnobj.getString("invitesent");
                        String attending=jsnobj.getString("attending");

                        arrayOfguests.add(new guest(User_Id,firstname,lastname,side,invitesent,attending,tbno));

//

//

                    }
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            Guest_list_adapter adapter1 = new Guest_list_adapter(Guest_activity.this, arrayOfguests);
//


            guestlist.setAdapter(adapter1);


                adapter1.notifyDataSetChanged();


p.dismiss();

        }

    }

    class update extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();


            params.add(new BasicNameValuePair("Id",gid));
            params.add(new BasicNameValuePair("firstname", name));
            params.add(new BasicNameValuePair("lastname", lastname1));
            params.add(new BasicNameValuePair("tbno", tbno));
            params.add(new BasicNameValuePair("side", side));
            params.add(new BasicNameValuePair("invite", invit));
            params.add(new BasicNameValuePair("attend", attnd));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url1,"POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(SUCCESS);

//               arrayOfUsers.clear();
                if (success == 1) {

                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done


        }

    }

    class delete extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();


            params.add(new BasicNameValuePair("Id",gid));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url2,"POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(SUCCESS);

//               arrayOfUsers.clear();
                if (success == 1) {

                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done


        }

    }
}
