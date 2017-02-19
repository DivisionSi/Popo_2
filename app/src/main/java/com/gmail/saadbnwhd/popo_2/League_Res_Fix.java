package com.gmail.saadbnwhd.popo_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class League_Res_Fix extends AppCompatActivity {
    team_List_Adap adap1,adap2;
    Integer Total_Goals;

    Button done;
    Firebase ref;
    EditText goals1,goals2;
    ListView TEAM1,TEAM2,TEAM1d,TEAM2d;
    ArrayList<String> T_Pl_1,T_Pl_1_temp;
    ArrayList<Integer> G1,G1_TEMP,G2,G2_TEMP;
    ArrayList<String> T_Pl_2,T_Pl_2_temp;
    String[] startTEAM1;
    String[] startTEAM2;
    Integer int_goals1,int_apps1,int_won1,int_lost1,int_drawn1,
            int_goals2,int_apps2,int_won2,int_lost2,int_drawn2,
            fixtureGoals1,fixtureGoals2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_res_fixx);
        Firebase.setAndroidContext(League_Res_Fix.this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        Bundle bundle=getIntent().getExtras();
        final String Team1 = bundle.getString("t1");
        final String Team2 = bundle.getString("t2");

        final TextView T1 = (TextView) findViewById(R.id.team2);
        final TextView  T2 = (TextView) findViewById(R.id.team1);
        T1.setText(Team1);
        T2.setText(Team2);
        Total_Goals =0;

        TEAM1 = (ListView) findViewById(R.id.Fst_Team);
        TEAM2 = (ListView) findViewById(R.id.Snd_Team);
        startTEAM1 = new String[]{"","No Players Added"};
        startTEAM2 = new String[]{"","No Players Added"};



        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fetch_Players();
                adap1 = new team_List_Adap(League_Res_Fix.this,T_Pl_1,G1);
                LayoutInflater inflater = LayoutInflater.from(League_Res_Fix.this);
                View dialog_layout = inflater.inflate(R.layout.leaguefixture,null);
                AlertDialog.Builder db = new AlertDialog.Builder(League_Res_Fix.this);

                TEAM1d = (ListView) dialog_layout.findViewById(R.id.fixturelist);
                db.setView(dialog_layout);
                db.setTitle(Team1);
                db.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Total_Goals = 0;
                        for(int i = 0; i <G1.size(); i++) {
                            Total_Goals = Total_Goals + G1.get(i);
                        }
                        T_Pl_1_temp = new ArrayList<String>();
                        G1_TEMP = new ArrayList<Integer>();
                        T_Pl_1_temp.add("Total Goals");
                        G1_TEMP.add(Total_Goals);
                        for(int i = 0; i <G1.size(); i++) {
                                if(G1.get(i) >0){
                                    T_Pl_1_temp .add(T_Pl_1.get(i));
                                    G1_TEMP.add(G1.get(i));
                                }
                        }
                        adap1 = new team_List_Adap(League_Res_Fix.this,T_Pl_1_temp,G1_TEMP);
                        TEAM1.setAdapter(adap1);
                    }
                });
                db.show();
           //     TEAM1d.setAdapter(adap1);
                TEAM1d.setAdapter(adap1);
                TEAM1d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Assign_goals1(i);
                    }
                });
            }
        });

        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fetch_Players();
                adap2 = new team_List_Adap(League_Res_Fix.this,T_Pl_2,G2);
                LayoutInflater inflater = LayoutInflater.from(League_Res_Fix.this);
                View dialog_layout = inflater.inflate(R.layout.leaguefixture,null);
                AlertDialog.Builder db = new AlertDialog.Builder(League_Res_Fix.this);

                TEAM2d = (ListView) dialog_layout.findViewById(R.id.fixturelist);
                db.setView(dialog_layout);
                db.setTitle(Team2);
                db.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Total_Goals = 0;
                        for(int i = 0; i <G2.size(); i++) {
                            Total_Goals = Total_Goals + G2.get(i);
                        }
                        T_Pl_2_temp = new ArrayList<String>();
                        G2_TEMP = new ArrayList<Integer>();
                        T_Pl_2_temp.add("Total Goals");
                        G2_TEMP.add(Total_Goals);
                        for(int i = 0; i <G1.size(); i++) {
                            if(G2.get(i) >0){
                                T_Pl_2_temp .add(T_Pl_2.get(i));
                                G2_TEMP.add(G2.get(i));
                            }
                        }
                        adap2 = new team_List_Adap(League_Res_Fix.this,T_Pl_2_temp,G2_TEMP);
                        TEAM2.setAdapter(adap2);
                    }
                });
                db.show();
                //     TEAM1d.setAdapter(adap1);
                TEAM2d.setAdapter(adap2);
                TEAM2d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Assign_goals2(i);
                    }
                });
            }
        });


        ArrayAdapter<String> itemsAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, startTEAM1);
        ArrayAdapter<String> itemsAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, startTEAM2);
        TEAM1.setAdapter(itemsAdapter1);
        TEAM2.setAdapter(itemsAdapter2);


        final Firebase team1_stats=ref.child("League").child("Stats").child(Team1);

        team1_stats.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int_goals1=Integer.parseInt(dataSnapshot.child("Goals").getValue().toString());
                int_apps1=Integer.parseInt(dataSnapshot.child("Apps").getValue().toString());
                int_won1=Integer.parseInt(dataSnapshot.child("Won").getValue().toString());
                int_drawn1=Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString());
                int_lost1=Integer.parseInt(dataSnapshot.child("Lost").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Firebase team2_stats=ref.child("League").child("Stats").child(Team2);

        team2_stats.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int_goals2=Integer.parseInt(dataSnapshot.child("Goals").getValue().toString());
                int_apps2=Integer.parseInt(dataSnapshot.child("Apps").getValue().toString());
                int_won2=Integer.parseInt(dataSnapshot.child("Won").getValue().toString());
                int_drawn2=Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString());
                int_lost2=Integer.parseInt(dataSnapshot.child("Lost").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        done=(Button) findViewById(R.id.result_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixtureGoals1=Integer.parseInt(goals1.getText().toString());
                fixtureGoals2=Integer.parseInt(goals2.getText().toString());

                //Appearances Increment
                int_apps1++;
                team1_stats.child("Apps").setValue(Integer.toString(int_apps1));

                int_apps2++;
                team2_stats.child("Apps").setValue(Integer.toString(int_apps2));

                //Goals Update
                int_goals1=int_goals1+Integer.parseInt(goals1.getText().toString());
                team1_stats.child("Goals").setValue(Integer.toString(int_goals1));

                int_goals2=int_goals2+Integer.parseInt(goals2.getText().toString());
                team2_stats.child("Goals").setValue(Integer.toString(int_goals2));

                if(fixtureGoals1>fixtureGoals2)
                {
                    int_won1++;
                    team1_stats.child("Won").setValue(Integer.toString(int_won1));

                    int_lost2++;
                    team2_stats.child("Lost").setValue(Integer.toString(int_lost2));
                }
                else if (fixtureGoals1<fixtureGoals2)
                {
                    int_won2++;
                    team2_stats.child("Won").setValue(Integer.toString(int_won2));

                    int_lost1++;
                    team1_stats.child("Lost").setValue(Integer.toString(int_lost2));

                }
                else
                {

                    int_drawn1++;
                    team1_stats.child("Drawn").setValue(Integer.toString(int_drawn1));

                    int_drawn2++;
                    team2_stats.child("Drawn").setValue(Integer.toString(int_drawn2));


                }

            }
        });


    }

    public void Fetch_Players(){
        T_Pl_1 = new ArrayList<String>();
        T_Pl_2 = new ArrayList<String>();
        G1 = new ArrayList<Integer>();
        G2 = new ArrayList<Integer>();
        for(int i = 0; i <15; i++)
        {
            T_Pl_1.add("T1Player "+String.valueOf(i));
            G1.add(0);
            T_Pl_2.add("T2Player "+String.valueOf(i));
            G2.add(0);
        }
    }

    public void Assign_goals1(final int position){
        RelativeLayout linearLayout = new RelativeLayout(League_Res_Fix.this);
        final NumberPicker aNumberPicker = new NumberPicker(League_Res_Fix.this);
        aNumberPicker.setMaxValue(50);
        aNumberPicker.setMinValue(0);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(League_Res_Fix.this);
        alertDialogBuilder.setTitle("Select the number");
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                               // Log.e("","New Quantity Value : "+ aNumberPicker.getValue());
                           //     Toast.makeText(League_Res_Fix.this, String.valueOf(aNumberPicker.getValue()), Toast.LENGTH_SHORT).show();
                                G1.set(position,Integer.valueOf(aNumberPicker.getValue()));
                                adap1 = new team_List_Adap(League_Res_Fix.this,T_Pl_1,G1);
                                TEAM1d.setAdapter(adap1);
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
    public void Assign_goals2(final int position){
        RelativeLayout linearLayout = new RelativeLayout(League_Res_Fix.this);
        final NumberPicker aNumberPicker = new NumberPicker(League_Res_Fix.this);
        aNumberPicker.setMaxValue(50);
        aNumberPicker.setMinValue(0);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(League_Res_Fix.this);
        alertDialogBuilder.setTitle("Select the number");
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                G2.set(position,Integer.valueOf(aNumberPicker.getValue()));
                                adap2 = new team_List_Adap(League_Res_Fix.this,T_Pl_2,G2);
                                TEAM2d.setAdapter(adap2);
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
    @Override
    protected void onStart() {

        super.onStart();

    }
}

