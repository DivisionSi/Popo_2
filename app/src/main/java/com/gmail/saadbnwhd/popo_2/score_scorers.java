package com.gmail.saadbnwhd.popo_2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class score_scorers extends AppCompatActivity {
    Button T1;
    Button T2;
    ArrayList<String> x1 = new ArrayList<String>();
    ArrayList<String> x2 = new ArrayList<String>();
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_res_fixx);
        Bundle bundle = getIntent().getExtras();
        String t1 = bundle.getString("t1");
        String t2 = bundle.getString("t2");



        for(int i = 0; i<26; i++){
            String s = "1Player "+String.valueOf(i);
            x1.add(i,s);
        }

        for(int i = 0; i<21; i++){
            String s = "2Player "+String.valueOf(i);
            x2.add(i,s);
        }

        T1 = (Button) findViewById(R.id.team_1);
        T2 = (Button) findViewById(R.id.team_2);

        T1.setText(t1);
        T2.setText(t2);

        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog a = new Dialog(score_scorers.this);
                a.setContentView(R.layout.team_listt);
                list = (ListView) a.findViewById(R.id.tlist);

                final team_List_Adap adapter = new team_List_Adap(score_scorers.this, x1);
                list.setAdapter(adapter);
                a.show();
            }
        });

        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog a = new Dialog(score_scorers.this);
                a.setContentView(R.layout.team_listt);
                list = (ListView) a.findViewById(R.id.tlist);

                final team_List_Adap adapter = new team_List_Adap(score_scorers.this, x2);
                list.setAdapter(adapter);
                a.show();
            }
        });

    }
}
