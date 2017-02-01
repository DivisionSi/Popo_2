package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.app.IntentService;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class League_Table extends AppCompatActivity {
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> goals = new ArrayList<String>();
    ArrayList<String> points = new ArrayList<String>();
    ArrayList<String> position = new ArrayList<String>();
    Firebase ref;
    Integer count,int_points,int_won,int_drawn,team_count;
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

         count=1;
        team_count=0;


        Firebase StatsRef; //Reference to Teams node
        StatsRef=ref.child("League").child("Stats");  //Traversing to Teams




        StatsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                position.add(Integer.toString(count));
                team.add(dataSnapshot.getKey().toString());
                goals.add(dataSnapshot.child("Goals").getValue().toString());

                int_won=Integer.parseInt(dataSnapshot.child("Won").getValue().toString());
                int_drawn=Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString());

                int_points=(int_won*3)+(int_drawn*2);
                points.add(Integer.toString(int_points));

                table_adapter.notifyDataSetChanged();
                count++;
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
