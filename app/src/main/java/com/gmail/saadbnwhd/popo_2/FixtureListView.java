package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Musab on 12/11/2016.
 */
public class FixtureListView extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> team1;
    private final ArrayList<String> team2;
    private final ArrayList<String> DateTime;
    private final Integer[] imgid1;
    private final Integer[] imgid2;


    public FixtureListView(Activity context, ArrayList<String> team1, ArrayList<String> team2, ArrayList<String> DateTime, Integer[] imgid1,Integer[] imgid2) {
        super(context, R.layout.fixtures_list,team1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team1=team1;
        this.team2=team2;
        this.DateTime=DateTime;
        this.imgid1=imgid1;
        this.imgid2=imgid2;

    }

    public View getView(int position,View view,ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.fixtures_list, null, true);

        TextView txtTeam1 = (TextView) rowView.findViewById(R.id.team1);
        ImageView icon = (ImageView) rowView.findViewById(R.id.logo);

        TextView txtTeam2 = (TextView) rowView.findViewById(R.id.team2);
        ImageView icon2 = (ImageView) rowView.findViewById(R.id.icon2);

        TextView txtDateTime = (TextView) rowView.findViewById(R.id.txtDateTime);

        txtDateTime.setText(DateTime.get(position));
        txtTeam1.setText(team1.get(position));
        txtTeam2.setText(team2.get(position));



        byte[] logobyte1= Logos.logos.get(team1.get(position));
        byte[] logobyte2= Logos.logos.get(team2.get(position));

        Log.i("Team1",team1.get(position));
        Log.i("Team2",team2.get(position));

        try {
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(logobyte1, 0, logobyte1.length);
            icon.setImageBitmap(bitmap1);


            Bitmap bitmap2 = BitmapFactory.decodeByteArray(logobyte2, 0, logobyte2.length);
            icon2.setImageBitmap(bitmap2);
        }
        catch (Exception  e)
        {


        }
        return rowView;

    }

}