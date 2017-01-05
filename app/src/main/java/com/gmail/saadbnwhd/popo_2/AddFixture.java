package com.gmail.saadbnwhd.popo_2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class AddFixture extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText txt_t1,txt_t2;
    ArrayList<String> teams = new ArrayList<String>(); //String array for Team Names
    Firebase ref; //Reference to our DB
    Spinner spinner1;
    Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixture);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        txt_t1=(EditText) findViewById(R.id.T1);
        txt_t2=(EditText) findViewById(R.id.T2);
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
        final Dialog p = new Dialog(this);
        p.setTitle("txt_Team");
        p.setContentView(R.layout.leaguefixture);

        final Button a, ar, b, r, g,po,c;
        a = (Button) p.findViewById(R.id.Alliance);
        ar = (Button) p.findViewById(R.id.Arsenal);
        b = (Button) p.findViewById(R.id.Barcelona);
        r = (Button) p.findViewById(R.id.RealMadrid);
        g = (Button) p.findViewById(R.id.Gladiators);
        po = (Button) p.findViewById(R.id.POPO);
        c = (Button) p.findViewById(R.id.Cake);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("Alliance FC");
                p.dismiss();
            }
        });
        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("Arsenal FC");
                p.dismiss();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("Real Madrid");
                p.dismiss();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("Barcelona FC");
                p.dismiss();
            }
        });
        po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("POPO FC");
                p.dismiss();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("Cake FC");
                p.dismiss();
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t1.setText("Gladiators FC");
                p.dismiss();
            }
        });
        p.show();
    }
    public void txt_teamshow2() {
        final Dialog p = new Dialog(this);
        p.setTitle("txt_Team");
        p.setContentView(R.layout.leaguefixture);

        final Button a, ar, b, r, g,po,c;
        a = (Button) p.findViewById(R.id.Alliance);
        ar = (Button) p.findViewById(R.id.Arsenal);
        b = (Button) p.findViewById(R.id.Barcelona);
        r = (Button) p.findViewById(R.id.RealMadrid);
        g = (Button) p.findViewById(R.id.Gladiators);
        po = (Button) p.findViewById(R.id.POPO);
        c = (Button) p.findViewById(R.id.Cake);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("Alliance FC");
                p.dismiss();
            }
        });
        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("Arsenal FC");
                p.dismiss();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("Real Madrid");
                p.dismiss();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("Barcelona FC");
                p.dismiss();
            }
        });
        po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("POPO FC");
                p.dismiss();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("Cake FC");
                p.dismiss();
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_t2.setText("Gladiators FC");
                p.dismiss();
            }
        });
        p.show();
    }

    @Override
    protected void onStart() {

        super.onStart();
        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams


        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);
                teams.add(dataSnapshot.getKey().toString());

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
