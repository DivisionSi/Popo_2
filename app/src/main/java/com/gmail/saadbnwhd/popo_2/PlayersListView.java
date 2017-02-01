package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Musab on 12/11/2016.
 */
public class PlayersListView extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> players;
    private final ArrayList<String> postion;
    private final ArrayList<String> number;
    private final Integer[] playerimgid;

    public PlayersListView(Activity context, ArrayList<String> players, ArrayList<String> postion,ArrayList<String> number, Integer[] playerimgid) {
        super(context, R.layout.listview, players);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.players=players;
        this.postion=postion;
        this.number=number;
        this.playerimgid=playerimgid;
    }

    public View getView(int i,View view,ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.playerslistview, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.leagueplr_name);
        TextView txtposition = (TextView) rowView.findViewById(R.id.leagueplr_position);
        TextView txtnumber = (TextView) rowView.findViewById(R.id.leagueplr_number);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(players.get(i));
        txtposition.setText(postion.get(i));
        txtnumber.setText(number.get(i));
        imageView.setImageResource(playerimgid[0]);

        return rowView;

    };
}