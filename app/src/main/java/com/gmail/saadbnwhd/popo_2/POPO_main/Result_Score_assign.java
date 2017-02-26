package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.Adapters.team_List_Adap;
import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;

public class Result_Score_assign extends Activity {
    int Total_Goals;
    int rival_goals;
    team_List_Adap adap1;
    ListView TEAM1,TEAM1d;
    ArrayList<String> T_Pl_1,T_Pl_1_temp,T_Pl_1_adap;
    ArrayList<String> POPO_PlayersApp;
    ArrayList<Integer> G1,G1_TEMP;
    String[] startTEAM1;
    Long int_pts1,int_pts2;
    Integer int_goals1,int_apps1,int_won1,int_lost1,int_drawn1,
            int_goals2,int_apps2,int_won2,int_lost2,int_drawn2,
            fixtureGoals1,fixtureGoals2;
    Button Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__score_assign);

        final TextView T1 = (TextView) findViewById(R.id.POPO);
        final TextView T2 = (TextView) findViewById(R.id.rival_Goals);
        TEAM1 = (ListView) findViewById(R.id.popoteam);
        Done = (Button) findViewById(R.id.done_score_assign);

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
                        POPO_PlayersApp = adap1.Players_Appeared();
                  //      Toast.makeText(Result_Score_assign.this, String.valueOf(adap1.Players_Appeared().size()), Toast.LENGTH_SHORT).show();
                        adap1 = new team_List_Adap(Result_Score_assign.this,T_Pl_1_temp,G1_TEMP,false);
                        TEAM1.setAdapter(adap1);
                    }
                });
                db.show();
                //     TEAM1d.setAdapter(adap1);
                TEAM1d.setAdapter(adap1);

            }
        });

        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Assign_goals_rival(T2);
            }
        });


        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Result_Score_assign.this, "OKAS", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void Fetch_Players(){
        T_Pl_1 = new ArrayList<String>();
        G1 = new ArrayList<Integer>();
        Firebase playersRef1=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("Popo").child("Players");


        playersRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                T_Pl_1.add(dataSnapshot.child("Name").getValue().toString());
                G1.add(0);
                //T_Pl_1.notify();
                //adap1.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        for(int i = 0; i<20; i++)
        {

        }

    }
    public void Assign_goals_rival(final TextView T){
        RelativeLayout linearLayout = new RelativeLayout(Result_Score_assign.this);
        final NumberPicker aNumberPicker = new NumberPicker(Result_Score_assign.this);
        aNumberPicker.setMaxValue(50);
        aNumberPicker.setMinValue(0);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Result_Score_assign.this);
        alertDialogBuilder.setTitle("Select Number");
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               rival_goals = Integer.valueOf(aNumberPicker.getValue());
                                T.setText(String.valueOf(rival_goals));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
