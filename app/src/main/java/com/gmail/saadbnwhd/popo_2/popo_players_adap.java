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
    ArrayList<String> popo_players_position=new ArrayList<>();
    ArrayList<String> popo_players_number=new ArrayList<>();
    private Holder holder;

    public popo_players_adap(Context context, ArrayList<String> popo_players,ArrayList<String> popo_players_number,ArrayList<String> popo_players_position) {
        super(context, R.layout.popoplayers_listview,popo_players);
        this.context=context;
       this.popo_players=popo_players;
        this.popo_players_number=popo_players_number;
        this.popo_players_position=popo_players_position;
    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.popoplayers_listview, null, true);

        holder=new Holder();

        holder.Name = (TextView) rowView.findViewById(R.id.popoplayer_name);
        holder.Number = (TextView) rowView.findViewById(R.id.popoplayer_number);
        holder.Position = (TextView) rowView.findViewById(R.id.popoplayer_position);
        holder.img = (ImageView) rowView.findViewById(R.id.popoplayer_img);

        holder.Name.setText(popo_players.get(position));
        holder.Number.setText(popo_players_number.get(position));
        holder.Position.setText(popo_players_position.get(position));
       // txtlocation.setText(locations.get(position));
        holder.img.setImageResource(R.drawable.football_player);

      //  Toast.makeText(context, teams.get(position), Toast.LENGTH_SHORT).show();
        return rowView;

    };

    public class Holder
    {
        TextView Name;
        TextView Number;
        TextView Position;
       ImageView img;
    }

}
