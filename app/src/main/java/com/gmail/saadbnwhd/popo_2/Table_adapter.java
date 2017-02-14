package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Table_adapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> rank;
    private final ArrayList<String> team_name;
    private final ArrayList<String> goals;
    private final ArrayList<String> points;
    private final ArrayList<String> draw;
    private final ArrayList<String> lose;
    private final ArrayList<String> win;
    public Table_adapter(Activity context, ArrayList<String> rank, ArrayList<String> team_name, ArrayList<String> goals, ArrayList<String> points, ArrayList<String> draw,ArrayList<String> lose, ArrayList<String> win) {
        super(context, R.layout.activity_table_adapter,team_name);
        this.context=context;
        this.team_name=team_name;
        this.rank=rank;
        this.goals=goals;
        this.points=points;
        this.draw=draw;
        this.lose=lose;
        this.win=win;
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_table_adapter, null, true);
        TextView pts = (TextView) rowView.findViewById(R.id.points);
        TextView team = (TextView) rowView.findViewById(R.id.team);
        TextView goal = (TextView) rowView.findViewById(R.id.goals);
        TextView pos = (TextView) rowView.findViewById(R.id.position);
        TextView txt_draw = (TextView) rowView.findViewById(R.id.draw);
        TextView txt_lose = (TextView) rowView.findViewById(R.id.lose);
        TextView txt_win = (TextView) rowView.findViewById(R.id.wins);

        pos.setText(rank.get(position));
        team.setText(team_name.get(position));
        pts.setText(points.get(position));
        goal.setText(goals.get(position));
        txt_draw.setText(draw.get(position));
        txt_lose.setText(lose.get(position));
        txt_win.setText(win.get(position));


        return rowView;
    }
}