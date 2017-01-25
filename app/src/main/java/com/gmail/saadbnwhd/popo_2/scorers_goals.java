package com.gmail.saadbnwhd.popo_2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class scorers_goals extends AppCompatActivity {


    ArrayList<String> T = new ArrayList<String>();
    ListView list;
    Button Proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_listt);
        Bundle bundle = getIntent().getExtras();
        T = bundle.getStringArrayList("team");

        list = (ListView) findViewById(R.id.tlist);
        Proceed = (Button) findViewById(R.id.prcd);

        final team_List_Adap adapter = new team_List_Adap(scorers_goals.this, T);
        list.setAdapter(adapter);

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> t = adapter.Scorers();
                if(t.size() > 0)
                {
                    Dialog a = new Dialog(scorers_goals.this);
                    a.setContentView(R.layout.team_listt);
                    Button Pr = (Button) a.findViewById(R.id.prcd);
                    list = (ListView) a.findViewById(R.id.tlist);

                    final league_scorers_adap adapter = new league_scorers_adap(scorers_goals.this, t);
                    list.setAdapter(adapter);
                    a.show();

                    Pr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(scorers_goals.this, "Added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(scorers_goals.this, "0", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
