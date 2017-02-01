package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class League_Table extends Activity {
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> goals = new ArrayList<String>();
    ArrayList<String> points = new ArrayList<String>();
    ArrayList<String> position = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        ListView list=(ListView) findViewById(R.id.tablelist);
        final Table_adapter adapter = new Table_adapter(this, position,team,goals, points);
                list.setAdapter(adapter);

    }
}
