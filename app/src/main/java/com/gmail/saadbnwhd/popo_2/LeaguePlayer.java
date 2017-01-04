package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class LeaguePlayer extends AppCompatActivity {
    ListView  playerlist;
FloatingActionButton playerfab;
    String[] players={
        "Musab","Saad","Fahad"

    };
    String[] position={"Mid","Striker","Keeper"};
    Integer[] playerimgid = {
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
        setContentView(R.layout.activity_league_player);



        playerlist = (ListView) findViewById(R.id.list);
      playerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          }
      });

    }
    @Override
    protected void onStart()
    {

        super.onStart();
        final PlayersListView adapter = new PlayersListView(this, players,position,playerimgid);
        playerlist.setAdapter(adapter);

    }
}
