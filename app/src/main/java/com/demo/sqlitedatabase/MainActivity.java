package com.demo.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

//        databaseHelper.addContact("Rajesh", "9865432125");
//        databaseHelper.addContact("Jjeshda", "9885432125");
//        databaseHelper.addContact("Oajeshpa", "9865432825");
//        databaseHelper.addContact("rajesh", "9865432925");
//        databaseHelper.addContact("rajesh", "9865432225");

// for update

//        ContactModel model = new ContactModel();
//        model.id = 1;
//        model.name = "mayur";
//        model.phone_no = "6366636365";
//        databaseHelper.upadateContact(model);
//

//for delete

//        databaseHelper.deleteContact(2);
//        databaseHelper.deleteContact(5);
//        databaseHelper.deleteContact(8);

        ArrayList<ContactModel> arr = databaseHelper.fetchContact();

        for (int i = 0; i < arr.size(); i++) {
            Log.e("======", arr.get(i).id + ":" + arr.get(i).name + ":" + arr.get(i).phone_no);
        }


    }
}