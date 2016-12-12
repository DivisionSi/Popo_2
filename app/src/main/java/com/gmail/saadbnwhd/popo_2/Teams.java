package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class Teams extends AppCompatActivity{
    ListView list;
    Firebase ref; //Reference to our DB
    String[] teams = {
            "POPO",
            "BARCELONA",
            "REAL MADRID",
    };

    Integer[] imgid = {
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,

    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        CustomListView adapter = new CustomListView(this, teams, imgid);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = teams[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();


            }
        });
    }
}
