package com.gmail.saadbnwhd.popo_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    String Team1,Team2,Datetime,Key;
    Button done;
    Firebase ref;
   EditText goals1,goals2;
    ListView TEAM1,TEAM2,TEAM1d,TEAM2d;
    ArrayList<String> T_Pl_1,T_Pl_1_temp,T_Pl_1_adap;
    ArrayList<Integer> G1,G1_TEMP,G2,G2_TEMP;
    ArrayList<String> T_Pl_2,T_Pl_2_temp,T_Pl_2_adap;
    ArrayList<String> Team1_PlayersApp,Team2_PlayersApp;
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

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("EDIT RESULTS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }

        Bundle bundle=getIntent().getExtras();
        Team1 = bundle.getString("t1");
        Team2 = bundle.getString("t2");
        Datetime=bundle.getString("DateTime");
        Key=bundle.getString("Key");

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
                adap1 = new team_List_Adap(League_Res_Fix.this,T_Pl_1,G1,true);
                LayoutInflater inflater = LayoutInflater.from(League_Res_Fix.this);
                View dialog_layout = inflater.inflate(R.layout.leaguefixture,null);
                AlertDialog.Builder db = new AlertDialog.Builder(League_Res_Fix.this);


                TEAM1d = (ListView) dialog_layout.findViewById(R.id.fixturelist);
                db.setView(dialog_layout);
                db.setTitle(Team1);
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
                        Team1_PlayersApp = adap1.Players_Appeared();
                        Log.i("App1",Team1_PlayersApp.toString());
                     //   Toast.makeText(League_Res_Fix.this, String.valueOf(adap1.Players_Appeared().size()), Toast.LENGTH_SHORT).show();
                        adap1 = new team_List_Adap(League_Res_Fix.this,T_Pl_1_temp,G1_TEMP,false);
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
                Fetch_Players();
                adap2 = new team_List_Adap(League_Res_Fix.this,T_Pl_2,G2,true);
                LayoutInflater inflater = LayoutInflater.from(League_Res_Fix.this);
                View dialog_layout = inflater.inflate(R.layout.leaguefixture,null);
                AlertDialog.Builder db = new AlertDialog.Builder(League_Res_Fix.this);

                TEAM2d = (ListView) dialog_layout.findViewById(R.id.fixturelist);
                db.setView(dialog_layout);
                db.setTitle(Team2);
                db.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Total_Goals = 0;
                        T_Pl_2_adap = adap2.Scorers();
                        G2.clear();
                        G2 = adap2.Goals_Alotted();

                        for(int i = 0; i <G2.size(); i++) {
                            Total_Goals = Total_Goals + G2.get(i);
                        }

                        T_Pl_2_temp = new ArrayList<String>();
                        G2_TEMP = new ArrayList<Integer>();
                        T_Pl_2_temp.add("Total Goals");
                        G2_TEMP.add(Total_Goals);
                        for(int i = 0; i <G2.size(); i++) {
                            // if(G1.get(i) >0){
                            T_Pl_2_temp .add(T_Pl_2_adap.get(i));
                            G2_TEMP.add(G2.get(i));
                            // }
                        }
                        Team2_PlayersApp = adap2.Players_Appeared();
                        adap2 = new team_List_Adap(League_Res_Fix.this,T_Pl_2_temp,G2_TEMP,false);
                        TEAM2.setAdapter(adap2);
                    }
                });
                db.show();

                TEAM2d.setAdapter(adap2);

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

                Map<String,Object> testmapRes=new HashMap<String,Object>();

                Map<String,Object> testmapTeam1= new HashMap<>();
                Map<String,Object> testmapTeam2= new HashMap<>();

                Map<String,Integer> testmapscorer1= new HashMap<>();
                Map<String,Integer> testmapscorer2= new HashMap<>();




                testmapTeam1.put("Name",Team1);
                testmapTeam1.put("Total Goals",G1_TEMP.get(0).toString());

                testmapTeam2.put("Name",Team2);
                testmapTeam2.put("Total Goals",G2_TEMP.get(0).toString());




                //resultsRef.child(key).child("Team2").child("Name").setValue(Team2);

               // resultsRef.child(key).child("Team1").child("Total Goals").setValue(G1_TEMP.get(0));
               // resultsRef.child(key).child("Team2").child("Total Goals").setValue(G2_TEMP.get(0));



                // Updating Results Node in Database
                // For team 1
                for (int i=1;i<T_Pl_1_temp.size();i++)
                {
                    testmapscorer1.put(T_Pl_1_temp.get(i),G1_TEMP.get(i));
                    //resultsRef.child(key).child("Team1").child("Scorers").child(T_Pl_1_temp.get(i)).setValue(G1_TEMP.get(i));
                }

                // For team2
                for (int i=1;i<T_Pl_2_temp.size();i++)
                {
                    testmapscorer2.put(T_Pl_2_temp.get(i),G2_TEMP.get(i));
                    //resultsRef.child(key).child("Team2").child("Scorers").child(T_Pl_2_temp.get(i)).setValue(G2_TEMP.get(i));
                }

                testmapTeam1.put("Scorers",testmapscorer1);
                testmapTeam2.put("Scorers",testmapscorer2);

                testmapRes.put("DateTime",Datetime);
                testmapRes.put("Team1",testmapTeam1);
                testmapRes.put("Team2",testmapTeam2);


                resultsRef.child(key).setValue(testmapRes);
                Firebase fixtureRef=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("League").child("Fixtures").child(Key);
                fixtureRef.removeValue();


                //Updating Player Stats in Database
                //For team1's players

                for (int i=1;i<T_Pl_1_temp.size();i++)
                {
                    Integer temp_goals;



                    temp_goals= playerGoals1.get(T_Pl_1_temp.get(i));
                    temp_goals=temp_goals+
                            G1_TEMP.get(i);


                    playersRef.child(Team1).child("Players")
                            .child(T_Pl_1_temp.get(i))
                            .child("Goals").setValue(temp_goals);


                }

                //Team1 player Apps
                for (int i=0;i<Team1_PlayersApp.size();i++) {
                    Integer temp_apps;

                    Log.i("Player Apps1",playerApps1.toString());

                    temp_apps = playerApps1.get(Team1_PlayersApp.get(i));
                    temp_apps++;


                    playersRef.child(Team1).child("Players")
                            .child(Team1_PlayersApp.get(i)).child("Apps").setValue(temp_apps);
                }

                    //For team2's players Goals

                for (int i=1;i<T_Pl_2_temp.size();i++)
                {
                    Integer temp_goals2;



                    temp_goals2= playerGoals2.get(T_Pl_2_temp.get(i));
                    temp_goals2=temp_goals2+
                            G1_TEMP.get(i);


                    playersRef.child(Team2).child("Players").child(T_Pl_2_temp.get(i)).child("Goals").setValue(temp_goals2);

                }

                //Team 2 player Apps
                for (int i=0;i<Team2_PlayersApp.size();i++) {
                    Integer temp_apps2;

                    temp_apps2 = playerApps2.get(Team2_PlayersApp.get(i));
                    temp_apps2++;


                    playersRef.child(Team2).child("Players").child(Team2_PlayersApp.get(i)).child("Apps").setValue(temp_apps2);
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


    @Override
    protected void onStart()
    {

        super.onStart();


    }
    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}

