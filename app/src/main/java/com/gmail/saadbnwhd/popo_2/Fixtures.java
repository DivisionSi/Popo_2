package com.gmail.saadbnwhd.popo_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class Fixtures extends AppCompatActivity {

    ListView list;
    Firebase ref; //Reference to our DB
    FloatingActionButton fab;

    ProgressBar pb;

    ArrayList<String> teams = new ArrayList<String>(); //String array for Team Names
    ArrayList<String> locations = new ArrayList<String>(); //String array for Team Locations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
