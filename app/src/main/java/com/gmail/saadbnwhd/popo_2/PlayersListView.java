package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

/**
 * Created by Musab on 12/11/2016.
 */
public class PlayersListView extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] players;
    private final String[] postion;
    private final Integer[] playerimgid;

    public PlayersListView(Activity context, String[] players, String[] postion, Integer[] playerimgid) {
        super(context, R.layout.listview, players);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.players=players;
        this.postion=postion;
        this.playerimgid=playerimgid;
    }

    public View getView(int position,View view,ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.team1);
        TextView txtlocation = (TextView) rowView.findViewById(R.id.location);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(players[position]);
        txtlocation.setText(postion[position]);
        imageView.setImageResource(playerimgid[position]);

        return rowView;

    };
}