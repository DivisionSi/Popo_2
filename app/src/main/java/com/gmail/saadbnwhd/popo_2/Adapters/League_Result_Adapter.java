package com.gmail.saadbnwhd.popo_2.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

/**
 * Created by Fahaid on 2/19/2017.
 */

public class League_Result_Adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> team1;
    private final ArrayList<String> team2;
    private final ArrayList<String> DateTime;
    private final Integer[] imgid1;
    private final Integer[] imgid2;


    public League_Result_Adapter(Activity context, ArrayList<String> team1, ArrayList<String> team2, ArrayList<String> DateTime, Integer[] imgid1,Integer[] imgid2) {
        super(context, R.layout.fixtures_list,team1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team1=team1;
        this.team2=team2;
        this.DateTime=DateTime;
        this.imgid1=imgid1;
        this.imgid2=imgid2;

    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.league_result, null, true);

        TextView txtTeam1 = (TextView) rowView.findViewById(R.id.team1);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);

        TextView txtTeam2 = (TextView) rowView.findViewById(R.id.res_team2);
        ImageView icon2 = (ImageView) rowView.findViewById(R.id.icon2);

        TextView txtDateTime = (TextView) rowView.findViewById(R.id.res_date_time);

        txtTeam1.setText(team1.get(position));
        txtTeam2.setText(team2.get(position));
        txtDateTime.setText(DateTime.get(position));
        //    icon.setImageResource(imgid1[position]);
        //  icon2.setImageResource(imgid2[position]);


        return rowView;

    };

}
