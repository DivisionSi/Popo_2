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

public class scorers_list_adapter extends ArrayAdapter<String> {

    private final Context context;
    ArrayList<String> scorers=new ArrayList<>();

    private Holder holder;

    public scorers_list_adapter(Context context, ArrayList<String> scorers) {
        super(context, R.layout.results_scorer_listview,scorers);
        this.context=context;
        this.scorers=scorers;
    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.results_scorer_listview,null, true);

        holder=new Holder();

        holder.txtScorer = (TextView) rowView.findViewById(R.id.scorer_name);

        holder.txtScorer.setText(scorers.get(position));

        //  Toast.makeText(context, teams.get(position), Toast.LENGTH_SHORT).show();
        return rowView;

    };

    public class Holder
    {
        TextView txtScorer;

    }

}
