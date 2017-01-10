package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;


public class Teams extends AppCompatActivity{
    ListView list;
    Firebase ref; //Reference to our DB
FloatingActionButton fab;

    ProgressBar pb;

    ArrayList<String> teams = new ArrayList<String>(); //String array for Team Names
    ArrayList<String> locations = new ArrayList<String>(); //String array for Team Locations

    Integer[] imgid = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        pb = (ProgressBar)this.findViewById(R.id.wait);
        pb.setVisibility(INVISIBLE);


fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamseditor=new Intent("android.intent.action.TeamsEditor");
                startActivity(teamseditor);
            }
        });





        list = (ListView) findViewById(R.id.list);

        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {

                String passingTeamName=parent.getItemAtPosition(position).toString();
                Intent intent = new Intent("android.intent.action.LeaguePlayer");
                intent.putExtra("passingTeamName", passingTeamName);
                 startActivity(intent);


            }
        });

        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("Teams");  //Traversing to Teams

        final CustomListView adapter = new CustomListView(this, teams,locations, imgid);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);

        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);
                teams.add(dataSnapshot.getKey().toString());
                locations.add(dataSnapshot.child("Location").getValue().toString());


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
