package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.Adapters.League_Result_Adapter;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static java.lang.Thread.sleep;

public class Results_Teams extends AppCompatActivity {
    ListView list;
    FloatingActionButton fb;
    Firebase ref;
    String totalGoals1,totalGoals2;

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
        setContentView(R.layout.activity_results__teams);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("RESULTS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }
        ProgressBar wait = (ProgressBar) findViewById(R.id.wait);
        wait.setVisibility(INVISIBLE);

        Firebase.setAndroidContext(Results_Teams.this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Results_Teams.this,GoalScorerPlayers.class);
                i.putExtra("t1",team1.get(position));
                i.putExtra("t2",team2.get(position));
                i.putExtra("g1",totalGoals1);
                i.putExtra("g2",totalGoals2);
                startActivity(i);
            }
        });
        fb = (FloatingActionButton) findViewById(R.id.fab);
        StartUp();
        // list.setAdapter(adapter);
     /*   list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
               *//* String Slecteditem = team1.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, LENGTH_SHORT).show();*//*


                Intent i=new Intent(getApplicationContext(),League_Res_Fix.class);
                i.putExtra("t1",team1.get(position));
                i.putExtra("t2",team2.get(position));

                team1.clear();
                team2.clear();
                DateTime.clear();

                startActivity(i);

            }
        });*/

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Results_Teams.this, Edit_Leage_Results.class);
                startActivity(i);
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
    @Override
    protected void onStart()
    {
        super.onStart();

    }

    public void StartUp(){
        Firebase FixturesRef; //Reference to Teams node
        FixturesRef=ref.child("League").child("Results");  //Traversing to Fixtures

        final League_Result_Adapter adapter = new League_Result_Adapter(this, team1,team2,DateTime,imgid1,imgid2);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);

        FixturesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                team1.add(dataSnapshot.child("Team1").child("Name").getValue().toString() + "  " +
                        dataSnapshot.child("Team1").child("Total Goals").getValue().toString());

                team2.add(" " + dataSnapshot.child("Team2").child("Total Goals").getValue().toString() + "  " +
                        dataSnapshot.child("Team2").child("Name").getValue().toString());

                DateTime.add(dataSnapshot.child("DateTime").getValue().toString());

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
