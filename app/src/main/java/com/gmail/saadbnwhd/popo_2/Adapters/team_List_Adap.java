package com.gmail.saadbnwhd.popo_2.Adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/6/2017.
 */

public class team_List_Adap extends ArrayAdapter<String>{
    private final Activity context;

    int rival_goals;
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<Integer> goals = new ArrayList<Integer>();
    ArrayList<Integer> goals_alotted = new ArrayList<Integer>();
    ArrayList<String> scorers = new ArrayList<String>();
    ArrayList<String> players_appeared = new ArrayList<String>();
    boolean Value;

    public team_List_Adap(Activity context, ArrayList<String> team,ArrayList<Integer> goals,boolean value) {
        super(context, R.layout.fixtures_list, team);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.team = team;
        this.goals = goals;
        this.goals_alotted = new ArrayList<Integer>();
        Value = value;
        players_appeared = new ArrayList<String>();
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.name_itemm, null, true);

        final ViewHolder holder = new ViewHolder();

        holder.Team = (TextView) rowView.findViewById(R.id.p_name);
        holder.Goals = (TextView) rowView.findViewById(R.id.goals_scored);
        holder.appearence = (CheckBox) rowView.findViewById(R.id.player_appearence);
        holder.Team.setText(team.get(position));
        holder.Goals.setText(String.valueOf(goals.get(position)));


        if(Value) {
            holder.Goals.setVisibility(View.INVISIBLE);
            holder.Goals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Assign_goals(holder,holder.Goals, position);
                }
            });


            holder.appearence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.appearence.isChecked()) {
                        players_appeared.add(team.get(position));
                        holder.Goals.setVisibility(View.VISIBLE);

                    } else if (!holder.appearence.isChecked()) {
                        holder.Goals.setText("0");
                        holder.Goals.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < players_appeared.size(); i++) {
                            if (players_appeared.get(i).equals(holder.Team.getText())) {
                                players_appeared.remove(i);
                            }
                        }
                        for (int i = 0; i < scorers.size(); i++) {
                            if (scorers.get(i).equals(holder.Team.getText())) {
                                scorers.remove(i);
                                goals_alotted.remove(i);
                            }
                        }
                    }
                }
            });
        }

        if(!Value) {
            holder.appearence.setVisibility(View.INVISIBLE);
        }

        return rowView;

    };

    public ArrayList<String> Scorers ()
    {
        return scorers;
    }

    public void Assign_goals(final ViewHolder h, final TextView GOALS, final int position){
        RelativeLayout linearLayout = new RelativeLayout(context);
        final NumberPicker aNumberPicker = new NumberPicker(context);
        aNumberPicker.setMaxValue(50);
        aNumberPicker.setMinValue(0);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Select Number");
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                goals.set(position,Integer.valueOf(aNumberPicker.getValue()));
                                goals_alotted.add(Integer.valueOf(aNumberPicker.getValue()));
                                scorers.add(String.valueOf(h.Team.getText()));
                                GOALS.setText(String.valueOf(goals.get(position)));
                               /* adap1 = new team_List_Adap(League_Res_Fix.this,T_Pl_1,G1);
                                TEAM1d.setAdapter(adap1);*/
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public ArrayList<String> Players_Appeared(){
        return players_appeared;
    }
    public ArrayList<Integer> Goals_Alotted(){
        return goals_alotted;
    }

    static class ViewHolder {
       private TextView Team,Goals;
        private CheckBox appearence;
    }
}