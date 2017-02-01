package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class League_Table extends AppCompatActivity {
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> goals = new ArrayList<String>();
    ArrayList<String> points = new ArrayList<String>();
    ArrayList<String> position = new ArrayList<String>();
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* team.add("Musab");
        goals.add("4");
        points.add("10");
        position.add("1");*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        ListView list=(ListView) findViewById(R.id.tablelist);
        final Table_adapter table_adapter = new Table_adapter(League_Table.this, position,team,goals, points);
                list.setAdapter(table_adapter);
        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams
        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                team.add(dataSnapshot.getKey().toString());
                position.add(dataSnapshot.getKey().toString());
                goals.add(dataSnapshot.getKey().toString());
                points.add(dataSnapshot.getKey().toString());
                table_adapter.notifyDataSetChanged();
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
}
