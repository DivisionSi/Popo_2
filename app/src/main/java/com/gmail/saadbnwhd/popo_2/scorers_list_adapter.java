package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.OnPausedListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/10/2017.
 */

public class scorers_list_adapter extends ArrayAdapter<String> {
    String m;
    TextView txtgoals;
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
        txtgoals.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (int i = 0; i < 20; i++) {
                    m = m + txtgoals.getText().toString();
                    goals.add(i, m);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        return rowView;

    };

    public class Holder
    {
        TextView txtScorer;

    }

}
