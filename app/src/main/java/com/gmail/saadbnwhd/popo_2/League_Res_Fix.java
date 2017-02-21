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
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gmail.saadbnwhd.popo_2.Adapters.team_List_Adap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class League_Res_Fix extends AppCompatActivity {
    team_List_Adap adap1,adap2;
    Integer Total_Goals;

    String Team1,Team2,Datetime;
    Button done;
    Firebase ref;
   EditText goals1,goals2;
    ListView TEAM1,TEAM2,TEAM1d,TEAM2d;
    ArrayList<String> T_Pl_1,T_Pl_1_temp;
    ArrayList<Integer> G1,G1_TEMP,G2,G2_TEMP;
    ArrayList<String> T_Pl_2,T_Pl_2_temp;
    String[] startTEAM1;
    String[] startTEAM2;
    Long int_pts1,int_pts2;
    Integer int_goals1,int_apps1,int_won1,int_lost1,int_drawn1,
            int_goals2,int_apps2,int_won2,int_lost2,int_drawn2,
            fixtureGoals1,fixtureGoals2;
    Map<String,Integer> playerGoals1,playerApps1,playerGoals2,playerApps2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_res_fixx);
        Firebase.setAndroidContext(League_Res_Fix.this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        Bundle bundle=getIntent().getExtras();
        Team1 = bundle.getString("t1");
        Team2 = bundle.getString("t2");
        Datetime=bundle.getString("DateTime");

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
                        for(int i = 0; i <G2.size(); i++) {
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
                int_pts1=(Long) dataSnapshot.child("Pts").getValue();
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
                int_pts2=(Long) dataSnapshot.child("Pts").getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Firebase playersRef=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("League").child("Teams");


        playerApps1=new HashMap<>();
        playerGoals1=new HashMap<>();

        playersRef.child(Team1).child("Players").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                playerApps1.put(dataSnapshot.getKey(),Integer.parseInt(dataSnapshot.child("Apps").getValue().toString()));
                playerGoals1.put(dataSnapshot.getKey(),(Integer.parseInt(dataSnapshot.child("Goals").getValue().toString())));

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

        playerApps2=new HashMap<>();
        playerGoals2=new HashMap<>();
        playersRef.child(Team2).child("Players").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                playerApps2.put(dataSnapshot.getKey().toString(),Integer.parseInt(dataSnapshot.child("Apps").getValue().toString()));
                playerGoals2.put(dataSnapshot.getKey().toString(),Integer.parseInt(dataSnapshot.child("Goals").getValue().toString()));

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

        done=(Button) findViewById(R.id.result_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(League_Res_Fix.this,T_Pl_1.get(0),Toast.LENGTH_SHORT).show();


                        fixtureGoals1=Integer.parseInt(G1_TEMP.get(0).toString());
                fixtureGoals2=Integer.parseInt(G2_TEMP.get(0).toString());


                //Appearances Increment
                int_apps1++;
                team1_stats.child("Apps").setValue(Integer.toString(int_apps1));

                int_apps2++;
                team2_stats.child("Apps").setValue(Integer.toString(int_apps2));

                //Goals Update
                int_goals1=int_goals1+Integer.parseInt(G1_TEMP.get(0).toString());
                team1_stats.child("Goals").setValue(Integer.toString(int_goals1));

                int_goals2=int_goals2+Integer.parseInt(G2_TEMP.get(0).toString());
                team2_stats.child("Goals").setValue(Integer.toString(int_goals2));

                if(fixtureGoals1>fixtureGoals2)
                {
                    int_pts1=int_pts1+3;
                    team1_stats.child("Pts").setValue(int_pts1);

                    int_won1++;
                    team1_stats.child("Won").setValue(Integer.toString(int_won1));

                    int_lost2++;
                    team2_stats.child("Lost").setValue(Integer.toString(int_lost2));
                }
                else if (fixtureGoals1<fixtureGoals2)
                {
                    int_pts2=int_pts2+3;
                    team2_stats.child("Pts").setValue(int_pts2);


                    int_won2++;
                    team2_stats.child("Won").setValue(Integer.toString(int_won2));

                    int_lost1++;
                    team1_stats.child("Lost").setValue(Integer.toString(int_lost2));

                }
                else
                {
                    int_pts1=int_pts1+1;
                    team1_stats.child("Pts").setValue(int_pts1);
                    int_pts2=int_pts2+1;
                    team2_stats.child("Pts").setValue(int_pts2);


                    int_drawn1++;
                    team1_stats.child("Drawn").setValue(Integer.toString(int_drawn1));

                    int_drawn2++;
                    team2_stats.child("Drawn").setValue(Integer.toString(int_drawn2));


                }

                Firebase resultsRef=new Firebase ("https://poponfa-8a11a.firebaseio.com/").child("League").child("Results");
                String key=resultsRef.push().getKey();

                resultsRef.child(key).child("DateTime").setValue(Datetime);

                resultsRef.child(key).child("Team1").child("Name").setValue(Team1);
                resultsRef.child(key).child("Team2").child("Name").setValue(Team2);

                resultsRef.child(key).child("Team1").child("Total Goals").setValue(G1_TEMP.get(0));
                resultsRef.child(key).child("Team2").child("Total Goals").setValue(G2_TEMP.get(0));
                // Updating Results Node in Database
                // For team 1
                for (int i=1;i<T_Pl_1_temp.size();i++)
                {
                    resultsRef.child(key).child("Team1").child("Scorers").child(T_Pl_1_temp.get(i)).setValue(G1_TEMP.get(i));
                }

                // For team2
                for (int i=1;i<T_Pl_2_temp.size();i++)
                {
                    resultsRef.child(key).child("Team2").child("Scorers").child(T_Pl_2_temp.get(i)).setValue(G2_TEMP.get(i));
                }




                //Updating Player Stats in Database
                //For team1's players

                for (int i=1;i<T_Pl_1_temp.size();i++)
                {
                    Integer temp_apps,temp_goals;

                    temp_apps=playerApps1.get(T_Pl_1_temp.get(i));
                    temp_apps++;

                    temp_goals= playerGoals1.get(T_Pl_1_temp.get(i));
                    temp_goals=temp_goals+
                            G1_TEMP.get(i);


                    playersRef.child(Team1).child("Players")
                            .child(T_Pl_1_temp.get(i))
                            .child("Goals").setValue(temp_goals);

                    playersRef.child(Team1).child("Players")
                            .child(T_Pl_1_temp.get(i)).child("Apps").setValue(temp_apps);

                }

                //For team2's players

                for (int i=1;i<T_Pl_2_temp.size();i++)
                {
                    Integer temp_apps2,temp_goals2;

                    temp_apps2=playerApps2.get(T_Pl_2_temp.get(i));
                    temp_apps2++;

                    temp_goals2= playerGoals2.get(T_Pl_2_temp.get(i));
                    temp_goals2=temp_goals2+
                            G1_TEMP.get(i);


                    playersRef.child(Team2).child("Players").child(T_Pl_2_temp.get(i)).child("Goals").setValue(temp_goals2);
                    playersRef.child(Team2).child("Players").child(T_Pl_2_temp.get(i)).child("Apps").setValue(temp_apps2);

                }

            }
        });


    }

    public void Fetch_Players(){
        T_Pl_1 = new ArrayList<String>();
        T_Pl_2 = new ArrayList<String>();
        G1 = new ArrayList<Integer>();
        G2 = new ArrayList<Integer>();

        Firebase playersRef1=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("League").child("Teams").child(Team1).child("Players");
        Firebase playersRef2=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("League").child("Teams").child(Team2).child("Players");

        playersRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                T_Pl_1.add(dataSnapshot.child("Name").getValue().toString());
                G1.add(0);
                //T_Pl_1.notify();
                adap1.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),dataSnapshot.child("Name").getValue().toString(),Toast.LENGTH_SHORT).show();
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

        playersRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                T_Pl_2.add(dataSnapshot.child("Name").getValue().toString());
                G2.add(0);
                adap1.notifyDataSetChanged();
                //T_Pl_2.notify();

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

