package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class score_scorers extends AppCompatActivity {
    TextView T1,T2;
    ArrayAdapter<String> adapter;

    ArrayList<String> x1 = new ArrayList<String>();
    ArrayList<String> x2 = new ArrayList<String>();


    ListView list,list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_res_fixx);
        Bundle bundle = getIntent().getExtras();
        String t1 = bundle.getString("t1");
        String t2 = bundle.getString("t2");
        String playername=bundle.getString("playername");
        list=(ListView) findViewById(R.id.scorers_list);
        list1=(ListView) findViewById(R.id.scorers_list1);



        for(int i = 0; i<26; i++){
            String s = "1Player "+String.valueOf(i);
            x1.add(i,s);
        }

        for(int i = 0; i<21; i++){
            String s = "2Player "+String.valueOf(i);
            x2.add(i,s);
        }

        T1 = (TextView) findViewById(R.id.team1);
        T2 = (TextView) findViewById(R.id.team2);

        T1.setText(t1);
        T2.setText(t2);

        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          /*      Dialog a = new Dialog(score_scorers.this);
                a.setContentView(R.layout.team_listt);
                list = (ListView) a.findViewById(R.id.tlist);
                final team_List_Adap adapter = new team_List_Adap(score_scorers.this, x1);
                list.setAdapter(adapter);
                a.show();*/
                Intent i = new Intent(score_scorers.this, scorers_goals.class);
                i.putStringArrayListExtra("team", x1);
                startActivity(i);
            }
        });

        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Dialog a = new Dialog(score_scorers.this);
                a.setContentView(R.layout.team_listt);
                list = (ListView) a.findViewById(R.id.tlist);
                final team_List_Adap adapter = new team_List_Adap(score_scorers.this, x2);
                list.setAdapter(adapter);
                a.show();*/
                Intent i = new Intent(score_scorers.this, scorers_goals.class);
                i.putStringArrayListExtra("team", x2);
                startActivity(i);
            }
        });
        Intent name = new Intent(score_scorers.this, scorers_list_adapter.class);

        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}