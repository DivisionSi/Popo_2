package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class LeaguePlayer extends AppCompatActivity {
    ListView  playerlist;
FloatingActionButton playeradd;
    ArrayList<String> players;

    String[] position={"Mid","Striker","Keeper"};
    Integer[] playerimgid = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
    };

    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_player);
        String passingTeamName = getIntent().getStringExtra("passingTeamName");
        Firebase.setAndroidContext(this);
        players=new ArrayList<String>();
playeradd=(FloatingActionButton) findViewById(R.id.playerfab);
playeradd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent addplayers=new Intent("android.intent.action.PlayerEditor");
        startActivity(addplayers);
    }
});
        playerlist = (ListView) findViewById(R.id.list);
      playerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          }
      });


        final PlayersListView adapter = new PlayersListView(this, players,position,playerimgid);
        playerlist.setAdapter(adapter);


        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
       Firebase playersRef;
        playersRef=ref.child("League").child("Teams").child(passingTeamName).child("Players");
        Toast.makeText(getApplicationContext(), playersRef.getKey().toString(), Toast.LENGTH_LONG).show();

        playersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getApplicationContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_LONG).show();
                players.add(dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
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
}
