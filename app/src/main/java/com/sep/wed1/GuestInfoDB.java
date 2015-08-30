package com.sep.wed1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kavi on 6/19/2015.
 */
public class GuestInfoDB extends SQLiteOpenHelper
{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GuestInfoDB";

    // Contacts table name
    private static final String TABLE_Name = "Guest";

    // Contacts Table Columns names
    private static final String KEY_ID = "Id";
    private static final String KEY_Fname = "Fname";
    private static final String KEY_Lname = "Lname";
    private static final String KEY_SeatNo = "Side";
    private static final String KEY_Side = "SeatNo";
    private static final String KEY_InviteSent = "InvitesSent";
    private static final String KEY_Attending = "Attending";

    public GuestInfoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Name + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Fname + " TEXT,"
                + KEY_Lname + " TEXT,"
                +  KEY_SeatNo + " TEXT," +  KEY_Side + " TEXT," +  KEY_InviteSent + " TEXT," +
                KEY_Attending + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Name);

        // Create tables again
        onCreate(db);
    }


    void addguest(GuestInfoConClass guestDB) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Fname, guestDB.getFname());
        values.put(KEY_Lname, guestDB.getLname());
        values.put(KEY_SeatNo, guestDB.getSeatNo());
        values.put(KEY_Side, guestDB.getSide());
        values.put(KEY_InviteSent, guestDB.getInvitesSent());
        values.put(KEY_Attending, guestDB.getAttending());


        // Inserting Row
        db.insert(TABLE_Name, null, values);
        db.close(); // Closing database connection
    }
}
