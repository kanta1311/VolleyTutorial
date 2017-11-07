package com.kantapp.retrofittutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinayak on 11/6/2017.
 */

public class Sqlite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PeopleDB";
    private static final String TABLE_NAME = "People";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firtname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_AGE = "age";
    private static final String[] COLUMNS = {KEY_ID, KEY_FIRSTNAME, KEY_LASTNAME,
            KEY_AGE};

    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATION_TABLE = "CREATE TABLE People ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "firstname TEXT, "
                + "lastname TEXT, " + "age TEXT )";

        sqLiteDatabase.execSQL(CREATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    void addPeople(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, movie.getFirstname()); // Contact Name
        values.put(KEY_LASTNAME, movie.getLastname()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection

    }

    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_FIRSTNAME, KEY_LASTNAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movie movie = new Movie(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
return movie;
    }
    // Getting All Contacts
    public List<Movie> getAllContacts() {
        List<Movie> contactList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setID(Integer.parseInt(cursor.getString(0)));
                movie.setFirstname(cursor.getString(1));
                movie.setLastname(cursor.getString(2));
                // Adding contact to list
                contactList.add(movie);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int updateContact(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, movie.getFirstname());
        values.put(KEY_LASTNAME, movie.getLastname());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getID()) });
    }


}
