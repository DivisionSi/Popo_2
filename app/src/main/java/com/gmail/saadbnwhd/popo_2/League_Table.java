package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class League_Table extends AppCompatActivity {
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> goals = new ArrayList<String>();
    ArrayList<String> points = new ArrayList<String>();
    ArrayList<String> position = new ArrayList<String>();
    ArrayList<String> draw= new ArrayList<String>();
    ArrayList<String> lose = new ArrayList<String>();
    ArrayList<String> win = new ArrayList<String>();

    Firebase ref;
    Integer count,int_points,int_won,int_drawn,team_count,tablePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("League Table");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        ListView list=(ListView) findViewById(R.id.tablelist);
        final Table_adapter table_adapter = new Table_adapter(League_Table.this, position,team,goals, points,draw,lose,win);
                list.setAdapter(table_adapter);

         count=1;
        team_count=0;


        final Firebase StatsRef; //Reference to Teams node
        StatsRef=ref.child("League").child("Stats");  //Traversing to Teams

        final Map<Integer,Map<String,Integer>> tableMap= new HashMap<>();
        final Map<String,Integer> teamMap=new HashMap<>();
        tablePosition=0;



        StatsRef.orderByChild("Pts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Map
                teamMap.put("Goals",Integer.parseInt(dataSnapshot.child("Goals").getValue().toString()));
                teamMap.put("Drawn",Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString()));
                teamMap.put("Lost",Integer.parseInt(dataSnapshot.child("Lost").getValue().toString()));
                teamMap.put("Won",Integer.parseInt(dataSnapshot.child("Won").getValue().toString()));

                tableMap.put(tablePosition,teamMap);
                tablePosition++;

//                Toast.makeText(getApplicationContext(),dataSnapshot.getKey().toString(),Toast.LENGTH_SHORT).show();
                //----------

                position.add(Integer.toString(count));
                team.add(dataSnapshot.getKey().toString());
                goals.add(dataSnapshot.child("Goals").getValue().toString());
                draw.add(dataSnapshot.child("Drawn").getValue().toString());
                lose.add(dataSnapshot.child("Lost").getValue().toString());
                win.add(dataSnapshot.child("Won").getValue().toString());



                int_won=Integer.parseInt(dataSnapshot.child("Won").getValue().toString());
                int_drawn=Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString());

                int_points=(int_won*3)+(int_drawn);
                points.add(Integer.toString(int_points));

                ref.child("League").child("Stats").child(dataSnapshot.getKey()).child("Pts").setValue(-int_points);

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
