package com.gmail.saadbnwhd.popo_2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/10/2017.
 */

public class scorers_list_adapter extends ArrayAdapter<String> {
    String m;
    StringBuilder temp = new StringBuilder(3);
    TextView txtgoals;
    int Total = 0;
    private final Context context;
    ArrayList<String> player_name=new ArrayList<>();
    ArrayList<String> goals = new ArrayList<String>(); //String array for Team Names

    private Holder holder;

    public scorers_list_adapter(Context context, ArrayList<String> scorers) {
        super(context, R.layout.results_scorer_listview,scorers);
        this.context=context;
        this.player_name=scorers;

    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.results_scorer_listview, null, true);

        holder=new Holder();
        holder.txtScorer = (TextView) rowView.findViewById(R.id.scorer_name);
        txtgoals=(TextView) rowView.findViewById(R.id.scorer_goals);
        holder.txtScorer.setText(player_name.get(position));

        return rowView;

    };

    public class Holder
    {
        TextView txtScorer;

    }

    public  int Total_Goals(){
        return 0;
    }
}
