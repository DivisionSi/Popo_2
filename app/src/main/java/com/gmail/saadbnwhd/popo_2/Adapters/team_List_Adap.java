package com.gmail.saadbnwhd.popo_2.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/6/2017.
 */

public class team_List_Adap extends ArrayAdapter<String>{
    private final Activity context;

    TextView Team,Goals;
    CheckBox check;
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<Integer> goals = new ArrayList<Integer>();
    ArrayList<String> scorers = new ArrayList<String>();
    ArrayList<Boolean> B = new ArrayList<Boolean>();

    public team_List_Adap(Activity context, ArrayList<String> team,ArrayList<Integer> goals) {
        super(context, R.layout.fixtures_list, team);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team = team;
        this.goals = goals;
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.name_itemm, null, true);

        Team = (TextView) rowView.findViewById(R.id.p_name);
        Goals = (TextView) rowView.findViewById(R.id.goals_scored);
        Team.setText(team.get(position));
        Goals.setText(String.valueOf(goals.get(position)));

      //  B.add(false);






        return rowView;

    };

    public void setGoals(String value){
        Goals.setText(value);
    }
    public ArrayList<String> Scorers ()
    {
        return scorers;
    }
}