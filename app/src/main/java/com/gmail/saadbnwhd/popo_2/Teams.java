package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.Adapters.CustomListView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class Teams extends AppCompatActivity{
    ListView list;
    Firebase ref; //Reference to our DB
FloatingActionButton fab;


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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_team);
        Firebase.setAndroidContext(Teams.this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("TEAMS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamseditor=new Intent("android.intent.action.TeamsEditor");
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(teamseditor);

            }
        });


        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
            fab.setVisibility(View.VISIBLE);


        list = (ListView) findViewById(R.id.list);

        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {

                String passingTeamName=parent.getItemAtPosition(position).toString();
                Intent intentstart = new Intent(Teams.this,LeaguePlayer.class);
                intentstart.putExtra("passingTeamName", passingTeamName);
                 startActivity(intentstart);


            }
        });

        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams


        final CustomListView adapter = new CustomListView(this, teams,locations, imgid);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);

        teamRef.orderByKey().addChildEventListener(new ChildEventListener() {
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
    public void finish() {
        super.finish();
       // onLeaveThisActivity();
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
