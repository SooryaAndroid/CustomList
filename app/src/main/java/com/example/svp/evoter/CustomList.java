package com.example.svp.evoter;

/**
 * Created by SVP on 21-Jun-16.
 */
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>{
TextView u_name,u_age,u_id,u_place;
    private ArrayList<String > lu_name;
    private ArrayList<String > lu_age;
    private ArrayList<String > lu_id;
    private ArrayList<String > lu_place;


    private Activity context;
    public CustomList(Activity context,
                      ArrayList<String> u_name,ArrayList<String> u_age,ArrayList<String> u_id,ArrayList<String> u_place) {
        super(context, R.layout.list, u_place);
        this.context = context;
        this.lu_name = u_name;
        this.lu_age = u_age;
        this.lu_id = u_id;
        this.lu_place = u_place;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list, null, true);

        u_name = (TextView)rowView.findViewById(R.id.u_name);
        u_age = (TextView)rowView.findViewById(R.id.u_age);
        u_id = (TextView)rowView.findViewById(R.id.u_id);
        u_place = (TextView)rowView.findViewById(R.id.u_place);

        u_name.setText(lu_name.get(position));
        u_age.setText(lu_age.get(position));
        u_id.setText(lu_id.get(position));
        u_place.setText(lu_place.get(position));


        return rowView;
    }
}