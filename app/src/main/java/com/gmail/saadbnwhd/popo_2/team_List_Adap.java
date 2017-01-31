package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/6/2017.
 */

public class team_List_Adap extends ArrayAdapter<String>{
    private final Activity context;

    TextView Team,Goals;
    CheckBox check;
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> scorers = new ArrayList<String>();
    ArrayList<Boolean> B = new ArrayList<Boolean>();

    public team_List_Adap(Activity context, ArrayList<String> team) {
        super(context, R.layout.fixtures_list, team);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team = team;
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.name_itemm, null, true);

        Team = (TextView) rowView.findViewById(R.id.p_name);
        check = (CheckBox) rowView.findViewById(R.id.chk);
        Team.setText(team.get(position));

        B.add(false);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!B.get(position)) {
                    scorers.add(team.get(position));
                    B.set(position,true);
                }

            }
        });




        return rowView;

    };

    public ArrayList<String> Scorers ()
    {
        return scorers;
    }
}