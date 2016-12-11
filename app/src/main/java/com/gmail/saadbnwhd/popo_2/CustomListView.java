package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Musab on 12/11/2016.
 */
public class CustomListView extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] teams;
    private final Integer[] imgid;

    public CustomListView(Activity context, String[] teams, Integer[] imgid) {
        super(context, R.layout.listview, teams);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.teams=teams;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.team);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(teams[position]);
        imageView.setImageResource(imgid[position]);

        return rowView;

    };
}