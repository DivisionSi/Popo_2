package com.gmail.saadbnwhd.popo_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/10/2017.
 */

public class popo_players_adap extends ArrayAdapter<String> {

    private final Context context;
    ArrayList<String> popo_players=new ArrayList<>();
    private Holder holder;

    public popo_players_adap(Context context, ArrayList<String> popo_players) {
        super(context, R.layout.listview, popo_players);
        this.context=context;
       this.popo_players=popo_players;
    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.listview, null, true);

        holder=new Holder();

        holder.Title = (TextView) rowView.findViewById(R.id.team1);
        holder.location = (TextView) rowView.findViewById(R.id.location);

        holder.img = (ImageView) rowView.findViewById(R.id.icon);
        holder.Title.setText(popo_players.get(position));
       // txtlocation.setText(locations.get(position));
        holder.img.setImageResource(R.drawable.football_player);

      //  Toast.makeText(context, teams.get(position), Toast.LENGTH_SHORT).show();
        return rowView;

    };

    public class Holder
    {
        TextView Title;
        TextView location;
       ImageView img;
    }

}
