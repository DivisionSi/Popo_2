package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Teams extends AppCompatActivity{
    ListView list;
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
                if (Slecteditem == "Popo") {
                    Intent outfitters = new Intent("android.intent.action.outfitters");
                    startActivity(outfitters);
                }
                if (Slecteditem == "Real Madrid") {
                    Intent stoneage = new Intent("android.intent.action.stoneage");
                    startActivity(stoneage);
                }
                if (Slecteditem == "Barcelona") {
                    Intent cougar = new Intent("android.intent.action.cougar");
                    startActivity(cougar);
                }

            }
        });
    }
}
