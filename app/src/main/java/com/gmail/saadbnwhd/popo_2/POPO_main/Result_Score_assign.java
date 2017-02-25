package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.saadbnwhd.popo_2.Adapters.team_List_Adap;
import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

public class Result_Score_assign extends Activity {
    int Total_Goals;
    team_List_Adap adap1;
    ListView TEAM1,TEAM1d;
    ArrayList<String> T_Pl_1,T_Pl_1_temp,T_Pl_1_adap;
    ArrayList<Integer> G1,G1_TEMP;
    String[] startTEAM1;
    Long int_pts1,int_pts2;
    Integer int_goals1,int_apps1,int_won1,int_lost1,int_drawn1,
            int_goals2,int_apps2,int_won2,int_lost2,int_drawn2,
            fixtureGoals1,fixtureGoals2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__score_assign);

        final TextView T1 = (TextView) findViewById(R.id.POPO);
        TEAM1 = (ListView) findViewById(R.id.popoteam);

        Fetch_Players();



        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fetch_Players();
                adap1 = new team_List_Adap(Result_Score_assign.this,T_Pl_1,G1,true);
                LayoutInflater inflater = LayoutInflater.from(Result_Score_assign.this);
                View dialog_layout = inflater.inflate(R.layout.leaguefixture,null);
                AlertDialog.Builder db = new AlertDialog.Builder(Result_Score_assign.this);


                TEAM1d = (ListView) dialog_layout.findViewById(R.id.fixturelist);
                db.setView(dialog_layout);
                db.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Total_Goals = 0;
                        T_Pl_1_adap = adap1.Scorers();
                        G1.clear();
                        G1 = adap1.Goals_Alotted();

                        for(int i = 0; i <G1.size(); i++) {
                            Total_Goals = Total_Goals + G1.get(i);
                        }

                        T_Pl_1_temp = new ArrayList<String>();
                        G1_TEMP = new ArrayList<Integer>();
                        T_Pl_1_temp.add("Total Goals");
                        G1_TEMP.add(Total_Goals);
                        for(int i = 0; i <G1.size(); i++) {
                            // if(G1.get(i) >0){
                            T_Pl_1_temp .add(T_Pl_1_adap.get(i));
                            G1_TEMP.add(G1.get(i));
                            // }
                        }
                        Toast.makeText(Result_Score_assign.this, String.valueOf(adap1.Players_Appeared().size()), Toast.LENGTH_SHORT).show();
                        adap1 = new team_List_Adap(Result_Score_assign.this,T_Pl_1_temp,G1_TEMP,false);
                        TEAM1.setAdapter(adap1);
                    }
                });
                db.show();
                //     TEAM1d.setAdapter(adap1);
                TEAM1d.setAdapter(adap1);

            }
        });
    }


    public void Fetch_Players(){
        T_Pl_1 = new ArrayList<String>();
        G1 = new ArrayList<Integer>();
        for(int i = 0; i<20; i++)
        {
            T_Pl_1.add("Player "+String.valueOf(i));
            G1.add(0);
        }

    }
}
