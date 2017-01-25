package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/25/2017.
 */

public class league_scorers_adap extends ArrayAdapter<String> {
    private final Activity context;

    TextView Team;
    EditText goals;
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> scorers = new ArrayList<String>();
    ArrayList<Boolean> B = new ArrayList<Boolean>();

    public league_scorers_adap(Activity context, ArrayList<String> team) {
        super(context, R.layout.scorers_list_item, team);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team = team;
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.scorers_list_item, null, true);

        Team = (TextView) rowView.findViewById(R.id.sc_name);
        goals = (EditText) rowView.findViewById(R.id.sc_goal);
        Team.setText(team.get(position));

        B.add(false);




        return rowView;

    };

    public ArrayList<String> Scorers ()
    {
        return scorers;
    }
}
