package com.gmail.saadbnwhd.popo_2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

public class playerslist_goals_adap extends ArrayAdapter<String> {
    private final Context context;
    ArrayList<String> player_name=new ArrayList<>();
    ArrayList<Integer> goal = new ArrayList<Integer>();
    public playerslist_goals_adap(Context context, ArrayList<String> scorers,ArrayList<Integer> goals) {
        super(context,R.layout.activity_playerslist_goals_adap,scorers);
        this.context=context;
        this.player_name=scorers;
        this.goal=goals;
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.activity_playerslist_goals_adap, null, true);
        TextView player=(TextView) rowView.findViewById(R.id.scorer_name);
        TextView goals=(TextView) rowView.findViewById(R.id.goalscored);

        player.setText(player_name.get(position));
        goals.setText(goal.get(position).toString());


        return rowView;

    };
}
