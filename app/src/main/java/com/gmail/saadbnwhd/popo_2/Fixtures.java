package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.widget.Toast.LENGTH_SHORT;

public class Fixtures extends AppCompatActivity {

    ListView list;
    Firebase ref; //Reference to our DB
    FloatingActionButton fab;

    ProgressBar pb;

    ArrayList<String> team1 = new ArrayList<String>(); //String array for Team A
    ArrayList<String> team2 = new ArrayList<String>(); //String array for Team B
    ArrayList<String> DateTime = new ArrayList<String>(); //String array for DateTime of Fixture

    Integer[] imgid1 = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
    };
    Integer[] imgid2 = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        pb = (ProgressBar)this.findViewById(R.id.wait);
        pb.setVisibility(INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent("android.intent.action.AddFixture");
                startActivity(i);
            }
        });

        list = (ListView) findViewById(R.id.list);
        // list.setAdapter(adapter);
        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = team1.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, LENGTH_SHORT).show();


            }
        });


        Firebase FixturesRef; //Reference to Teams node
        FixturesRef=ref.child("League").child("Fixtures");  //Traversing to Fixtures

        final FixtureListView adapter = new FixtureListView(this, team1,team2,DateTime,imgid1,imgid2);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);

        FixturesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);
                Toast.makeText(getApplicationContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_LONG).show();
                team1.add(dataSnapshot.child("Team1").getValue().toString());
                team2.add(dataSnapshot.child("Team2").getValue().toString());
                DateTime.add(dataSnapshot.child("Date").getValue().toString() + " | " + dataSnapshot.child("Time").getValue().toString());

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




}
