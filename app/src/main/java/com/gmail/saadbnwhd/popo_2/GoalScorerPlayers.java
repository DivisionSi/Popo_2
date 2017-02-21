package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.Adapters.playerslist_goals_adap;
import com.gmail.saadbnwhd.popo_2.Adapters.team_List_Adap;

import java.util.ArrayList;

public class GoalScorerPlayers extends AppCompatActivity {
    String Team1,Team2,Goals1,Goals2,Key;
    ArrayList<String> P1,P2;
    ArrayList<Integer> G1,G2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("RESULTS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }

        setContentView(R.layout.activity_goal_scorer_players);

        P1=new ArrayList<>();
        P2=new ArrayList<>();
        G1=new ArrayList<>();
        G2=new ArrayList<>();

        Bundle bundle=getIntent().getExtras();
        Team1 = bundle.getString("t1");
        Team2 = bundle.getString("t2");

        Goals1 = bundle.getString("g1");
        Goals2 = bundle.getString("g2");

        Key = bundle.getString("Key");

        ListView  TEAM1 = (ListView) findViewById(R.id.players_t1);
        ListView  TEAM2 = (ListView) findViewById(R.id.players_t2);
        final playerslist_goals_adap players1=new playerslist_goals_adap(GoalScorerPlayers.this,P1,G1);
        final playerslist_goals_adap players2=new playerslist_goals_adap(GoalScorerPlayers.this, P2, G2);

        TEAM1.setAdapter(players1);
        TEAM2.setAdapter(players2);

        Firebase ref1=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("League")
                .child("Results").child(Key).child("Team1").child("Scorers");

        ref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                P1.add(dataSnapshot.getKey());
                G1.add(Integer.parseInt(dataSnapshot.getValue().toString()));

                players1.notifyDataSetChanged();

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

        Firebase ref2=new Firebase("https://poponfa-8a11a.firebaseio.com/").child("League")
                .child("Results").child(Key).child("Team2").child("Scorers");

        ref2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                P2.add(dataSnapshot.getKey());
                G2.add(Integer.parseInt(dataSnapshot.getValue().toString()));
                players2.notifyDataSetChanged();
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






        TextView T1=(TextView) findViewById(R.id.team1);
        TextView T2=(TextView) findViewById(R.id.team2);
        TextView G1=(TextView) findViewById(R.id.goals1);
        TextView G2=(TextView) findViewById(R.id.goals2);
        T1.setText(Team1);
        T2.setText(Team2);
        G1.setText(Goals1);
        G2.setText(Goals2);

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
    @Override
    protected void onStart()
    {
        super.onStart();

    }
}
