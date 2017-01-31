package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;

public class League_Res_Fix extends AppCompatActivity {
    ListView scorer_list1,scorer_list2;
    Button done;
    Firebase ref,team1ref,team2ref;
    ArrayList<String> player_name = new ArrayList<String>();
    ArrayList<Integer> goals = new ArrayList<Integer>(); //String array for goals of players
    ArrayList<String> scorers1= new ArrayList<String>();
    ArrayList<String> scorers2= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_res_fixx);
        done=(Button) findViewById(R.id.result_done);
        final Bundle b=this.getIntent().getExtras();
        final int golazo;
        golazo=b.getInt("goals");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView goals=(TextView) findViewById(R.id.score_team1);
                goals.setText(Integer.toString(golazo));
            }
        });
        Bundle bundle=getIntent().getExtras();
        String Team1 = bundle.getString("t1");
        String Team2 = bundle.getString("t2");

        Firebase.setAndroidContext(League_Res_Fix.this);  //Setting up Firebase
            ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        scorer_list1 = (ListView) findViewById(R.id.scorers_list);
        scorer_list2=(ListView) findViewById(R.id.scorers_list1);

     TextView T1 = (TextView) findViewById(R.id.team1);
        TextView  T2 = (TextView) findViewById(R.id.team2);
        T1.setText(Team1);
        T2.setText(Team2);
        // list.setAdapter(adapter);
        Firebase FixturesRef; //Reference to Teams node
        team1ref=ref.child("League").child("Teams").child(Team1).child("Players");  //Traversing to that Team
        team2ref=ref.child("League").child("Teams").child(Team2).child("Players");

        final scorers_list_adapter adapter_scorers1 = new scorers_list_adapter(this, scorers1);
        final scorers_list_adapter adapter_scorers2 = new scorers_list_adapter(this, scorers2);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        scorer_list1.setAdapter(adapter_scorers1);
        scorer_list2.setAdapter(adapter_scorers2);

        team1ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);
                Toast.makeText(getApplicationContext(),dataSnapshot.getKey().toString(),Toast.LENGTH_LONG).show();
                scorers1.add(dataSnapshot.getKey().toString());

                adapter_scorers1.notifyDataSetChanged();
                PlayersFragment.ListUtils.setDynamicHeight(scorer_list1);

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

        team2ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);
                //      Toast.makeText(getApplicationContext(),dataSnapshot.getKey().toString(),Toast.LENGTH_LONG).show();
                scorers2.add(dataSnapshot.getKey().toString());

                adapter_scorers2.notifyDataSetChanged();
                PlayersFragment.ListUtils.setDynamicHeight(scorer_list2);
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
    protected void onStart() {

        super.onStart();

    }
}

