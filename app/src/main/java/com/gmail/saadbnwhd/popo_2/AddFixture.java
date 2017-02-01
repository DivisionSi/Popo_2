package com.gmail.saadbnwhd.popo_2;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class AddFixture extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView txt_t1,txt_t2;
    Firebase ref; //Reference to our DB
    ListView fixtureteamlist;
    ArrayList<String> team1 = new ArrayList<String>(); //String array for Team1 Names
    ArrayList<String> team2 = new ArrayList<String>(); //String array for Team2 Names
    ArrayList<String> DateTime=new ArrayList<String>();
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_fixture);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        txt_t1=(TextView) findViewById(R.id.T1);
        txt_t2=(TextView) findViewById(R.id.T2);
        txt_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_teamshow1();
            }
        });
        txt_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_teamshow2();
            }
        });
        fixtureteamlist = (ListView) findViewById(R.id.fixturelist);

        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams

        final FixtureListView fixtureadapter = new FixtureListView(this, team1,team2, DateTime,imgid1,imgid2);

        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                team1.add(dataSnapshot.getKey().toString());
                team2.add(dataSnapshot.getKey().toString());


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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void txt_teamshow1() {
        onStart();
        final Dialog p = new Dialog(this);
        p.setTitle("txt_Team");
        p.setContentView(R.layout.leaguefixture);
        fixtureteamlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    txt_t1.setText(position);
                    p.dismiss();
                } else if (position == 1) {
                    txt_t1.setText(position);
                    p.dismiss();
                } else if (position == 2) {
                    txt_t1.setText(position);
                    p.dismiss();
                }

            }
        });
        p.show();
    }
    public void txt_teamshow2() {
        onStart();
        final Dialog p = new Dialog(this);
        p.setTitle("txt_Team");
        p.setContentView(R.layout.leaguefixture);
        fixtureteamlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    txt_t2.setText(position);
                    p.dismiss();
                } else if (position == 1) {
                    txt_t2.setText(position);
                    p.dismiss();
                } else if (position == 2) {
                    txt_t2.setText(position);
                    p.dismiss();
                }

            }
        });

        p.show();
    }

    protected void onStart() {

        super.onStart();


    }





}