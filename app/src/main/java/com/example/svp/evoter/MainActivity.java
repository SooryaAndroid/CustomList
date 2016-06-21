package com.example.svp.evoter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  EditText name,age,id,place;
    ArrayList<String> u_name,u_age,u_id,u_place;
ListView list1;

Map<String,String> jsdata;
    Button btn1,count,view;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DataBase(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list1 = (ListView)findViewById(R.id.list1);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        id = (EditText) findViewById(R.id.id);
        place = (EditText) findViewById(R.id.place);

        count = (Button) findViewById(R.id.count);
        view = (Button) findViewById(R.id.view);

        u_name = new ArrayList();
        u_age = new ArrayList();
        u_id = new ArrayList();
        u_place = new ArrayList();


        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsdata = new HashMap<>();
                jsdata.put("name", name.getText().toString());
                jsdata.put("age", age.getText().toString());
                jsdata.put("id", id.getText().toString());
                jsdata.put("place", place.getText().toString());
                JSONObject jsonObject = new JSONObject(jsdata);
                Log.d("JSON DATA IS", jsonObject.toString());
                db.adddetails(jsonObject);
                Toast.makeText(MainActivity.this, "" + jsdata, Toast.LENGTH_SHORT).show();
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db = new DataBase(getApplicationContext());
                    int count = db.getRecordsCount();
                    Toast.makeText(getApplicationContext(), count + "", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//
//                    JSONArray jsonArray = db.getAll();
//                  //  for (int i = 0; i < jsonArray.length(); i++) {
//
//                        Toast.makeText(getApplicationContext(), "" + jsonArray, Toast.LENGTH_SHORT).show();
//                    //}
//
//                } catch (Exception e) {
//
//                }




                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            u_name.clear();
                            u_age.clear();
                            u_id.clear();
                            u_place.clear();


                            JSONArray jsonArray = db.getAll();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject((jsonArray.get(i)).toString());
                                u_name.add(jsonObject.getString("name"));
                                u_age.add(jsonObject.getString("age"));
                                u_id.add(jsonObject.getString("id"));
                                u_place.add(jsonObject.getString("place"));

//                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("name"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) {

                        }
                        CustomList customList = new CustomList(MainActivity.this,u_name,u_age,u_id,u_place);
                        list1.setAdapter(customList);
                        Log.d("submit",u_name.toString());

               //  Log.d("JSON DATA IS",jsonObject.toString());

            }
        });

    }
}
