package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/10/2017.
 */

public class scorers_list_adapter extends ArrayAdapter<String> {
    TextView Goals;
    private final Context context;
    ArrayList<String> player_name=new ArrayList<>();
    ArrayList<Integer> goals = new ArrayList<Integer>(); //String array for Team Names

    private Holder holder;

    public scorers_list_adapter(Context context, ArrayList<String> scorers) {
        super(context, R.layout.results_scorer_listview,scorers);
        this.context=context;
        this.player_name=scorers;

    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.results_scorer_listview, null, true);

        holder=new Holder();
        Bundle bundle=new Bundle();
        holder.txtScorer = (TextView) rowView.findViewById(R.id.scorer_name);
        holder.txtgoals=(TextView) rowView.findViewById(R.id.player_goals);
        holder.txtScorer.setText(player_name.get(position));
        Goals = (TextView) rowView.findViewById(R.id.scorer_goals);
        String goalz= Goals.getText().toString();

        int g=Integer.getInteger(goalz);
        int goalas = 1;
        for(int i =0; i<20; i++)
        {
            goals.add(g+i);
            goalas=goalas+goals.get(i);
        }
        bundle.putInt("goals",goalas);
        return rowView;

    };

    public class Holder
    {
        TextView txtScorer;
        TextView txtgoals;
    }

}
