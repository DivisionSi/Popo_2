package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.FixtureListView;
import com.gmail.saadbnwhd.popo_2.R;

import java.util.ArrayList;
import java.util.Map;

import static android.view.View.INVISIBLE;

public class POPO_Results_Edit extends AppCompatActivity {
    ListView list;
    FloatingActionButton fb;
    Firebase ref;

    ArrayList<String> team1 = new ArrayList<String>(); //String array for Team A
    ArrayList<String> team2 = new ArrayList<String>(); //String array for Team B
    ArrayList<String> DateTime = new ArrayList<String>(); //String array for DateTime of Fixture
    ArrayList<String> Keys = new ArrayList<String>();

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


        /*Bundle bundle=getIntent().getExtras();
        team1 = bundle.getStringArrayList("TEAM1");
        team2 = bundle.getStringArrayList("TEAM2");
        DateTime=bundle.getStringArrayList("DateTime");*/
        team1 = new ArrayList<String>();
        team2 = new ArrayList<String>();

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

      /*  Firebase.setAndroidContext(POPO_Results_Edit.this);  //Setting up Firebase
        ref = new Firebase("https://poponfa-8a11a.firebaseio.com/");*/

        list = (ListView) findViewById(R.id.list);
        fb = (FloatingActionButton) findViewById(R.id.fab);

        // list.setAdapter(adapter);


        StartUp();
        fb.setVisibility(INVISIBLE);
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
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public void StartUp(){


        team1.clear();
        team2.clear();
        DateTime.clear();

        Firebase.setAndroidContext(POPO_Results_Edit.this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
               /* String Slecteditem = team1.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, LENGTH_SHORT).show();*/


                Intent i = new Intent(POPO_Results_Edit.this, Result_Score_assign.class);
                //     i.putExtra("t1", team1.get(position));
                //
                i.putExtra("Key",Keys.get(position));
                i.putExtra("DateTime", DateTime.get(position));
                i.putExtra("t2", team2.get(position));


                startActivity(i);

            }
        });

        Firebase FixturesRef; //Reference to Teams node
        FixturesRef=ref.child("Popo").child("Fixtures");  //Traversing to Fixtures

        final FixtureListView adapter = new FixtureListView(POPO_Results_Edit.this, team1,team2,DateTime,imgid1,imgid2);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);



        FixturesRef.orderByChild("TimeStamp").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                //  Toast.makeText(getApplicationContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_LONG).show();
                team1.add("Popo FC");
                team2.add(dataSnapshot.child("Rival").getValue().toString());
                Keys.add(dataSnapshot.getKey());
                try {
                    DateTime.add(dataSnapshot.child("Date").getValue().toString() + " | " +
                            dataSnapshot.child("Time").getValue().toString());
                }
                catch(Exception e)
                {


                }

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
