package com.example.svp.evoter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SVP on 18-Jun-16.
 */
public class DataBase extends SQLiteOpenHelper {


        private static  String DATABASE_NAME= "DD";
    private static  int DATABASE_VERSION= 1;


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
        Log.d("value","cont");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query;
            query = "CREATE TABLE details ( UID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT, ID TEXT, PLACE TEXT)";
            db.execSQL(query);
        }catch (Exception e)
        {
            Log.d("value","create"+e);
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "details");

        // Create tables again
        onCreate(db);
    }
    void adddetails(JSONObject jsonObject) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        try {
            values.put("NAME", jsonObject.getString("name")); // Contact Name
            values.put("AGE", jsonObject.getString("age"));
            values.put("ID", jsonObject.getString("id"));
            values.put("PLACE", jsonObject.getString("place"));// Contact Phone
            //values.put(KEY_QNTY, contact.get_Qnty()); // Contact Phone

            // Inserting Row
            db.insert("details", null, values);
            db.close(); // Closing database connection

            Log.d("values","created");
        } catch (Exception e)
        {
e.printStackTrace();
        }

    }

    public int getRecordsCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM  details";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);



        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    public JSONArray getAll() {
        JSONArray jsnary = new JSONArray();
        Map<String,String> jsdata = new HashMap<>();

        JSONObject jsonObject =null;

        String selectQuery = "SELECT  * FROM details";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                jsdata.put("name",cursor.getString(1));
                jsdata.put("age",cursor.getString(2));
                jsdata.put("id",cursor.getString(3));
                jsdata.put("place",cursor.getString(4));
                jsonObject = new JSONObject(jsdata);
                jsnary.put(jsonObject);
            } while (cursor.moveToNext());
        }


        return jsnary;

    }
//    public List<String> getAllContacts() {
//        List<String> contactList = new ArrayList<Contact>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + "invoice";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Contact contact = new Contact();
//                contact.setSl(Integer.parseInt(cursor.getString(0)));
//                contact.setBrand(cursor.getString(1));
//                contact.setProduct(cursor.getString(2));
//                contact.setPrice(cursor.getString(3));
//                contact.setQuantity(cursor.getString(4));
//                // Adding contact to list
//                contactList.add(contact);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return contactList;
//}
}
