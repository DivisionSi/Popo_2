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
 * Created by Fahaid on 2/18/2017.
 */

public class League_Add_Fixture_Adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> team1;
  //  private final ArrayList<String> team2;
    private final Integer[] imgid1;
    private final Integer[] imgid2;


    public League_Add_Fixture_Adapter(Activity context, ArrayList<String> team1, Integer[] imgid1,Integer[] imgid2) {
        super(context, R.layout.fixtures_list,team1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team1=team1;
   //     this.team2=team2;
        this.imgid1=imgid1;
        this.imgid2=imgid2;

    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null, true);

        TextView txtTeam1 = (TextView) rowView.findViewById(R.id.team1);
        TextView txtlocation = (TextView) rowView.findViewById(R.id.location);
        ImageView icon = (ImageView) rowView.findViewById(R.id.logo);

       txtlocation.setText("  ");

        txtTeam1.setText(team1.get(position));
        //   txtDateTime.setText(DateTime.get(position));
        //    icon.setImageResource(imgid1[position]);
        //  icon2.setImageResource(imgid2[position]);


        return rowView;

    };

}
