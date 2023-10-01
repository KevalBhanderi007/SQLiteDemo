package com.demo.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Contactdb";
    private static final int DATABASE_ID = 1;
    private static final String TABLE_CONTACT = "contact";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_CONTACT +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_NAME + " TEXT," + KEY_PHONE_NO + " TEXT " + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        onCreate(db);

    }

    public void addContact(String name, String phone_no) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE_NO, phone_no);

        db.insert(TABLE_CONTACT, null, values);
        Log.e("===addContact===", "insert data");
        db.close();
    }


    public ArrayList<ContactModel> fetchContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);


        ArrayList<ContactModel> arr = new ArrayList<>();
        while (cursor.moveToNext()) {


            ContactModel model = new ContactModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.phone_no = cursor.getString(2);

            arr.add(model);
        }
        return arr;
    }


    public void upadateContact(ContactModel contactModel) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_PHONE_NO, contactModel.phone_no);

        database.update(TABLE_CONTACT, cv, KEY_ID + "=" + contactModel.id, null);

    }

    public void deleteContact(int id) {
        SQLiteDatabase database = this.getWritableDatabase();


        database.delete(TABLE_CONTACT,KEY_ID+" = ? ",new String[]{String.valueOf(id)});
    }
}