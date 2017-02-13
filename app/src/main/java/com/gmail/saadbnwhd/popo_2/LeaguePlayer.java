package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.realtime.util.StringListReader;

import java.util.ArrayList;

public class LeaguePlayer extends AppCompatActivity {
    ListView  playerlist;
FloatingActionButton pladd;
    ArrayList<String> players=new ArrayList<String>();
    ArrayList<String> number=new ArrayList<String>();
    ArrayList<String> positions=new ArrayList<>();

    Integer[] playerimgid = {
            R.drawable.playericon2
    };

    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_player);
        final String passingTeamName = getIntent().getStringExtra("passingTeamName");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle(passingTeamName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }
        Firebase.setAndroidContext(this);
        players=new ArrayList<String>();

        pladd=(FloatingActionButton) findViewById(R.id.playerfab);
        pladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plr=new Intent("android.intent.action.PlayerEditor");
                Bundle b = new Bundle();
                b.putBoolean("isPopo",false);
                b.putString("passingTeamName",passingTeamName);
                plr.putExtras(b);
                startActivity(plr);
            }
        });
        playerlist = (ListView) findViewById(R.id.list);
      playerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              String passingPlayerName=parent.getItemAtPosition(position).toString();
              String numb=number.get(position).toString();
              String pos=positions.get(position).toString();
              Intent intent = new Intent(LeaguePlayer.this,LeaguePlayer_Stats.class);
              intent.putExtra("passingPlayerName", passingPlayerName);
              intent.putExtra("number",numb);
              intent.putExtra("position",pos);
              intent.putExtra("passingTeamName",passingTeamName);
              startActivity(intent);


          }
      });


        final PlayersListView adapter = new PlayersListView(this, players,positions,number,playerimgid);
        playerlist.setAdapter(adapter);


        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
       Firebase playersRef;
        playersRef=ref.child("League").child("Teams").child(passingTeamName).child("Players");
        Toast.makeText(getApplicationContext(), playersRef.getKey().toString(), Toast.LENGTH_LONG).show();

        playersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getApplicationContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_LONG).show();
                players.add(dataSnapshot.getKey().toString());
                positions.add(dataSnapshot.child("Position").getValue().toString());
                number.add(dataSnapshot.child("Jersey Number").getValue().toString());
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
    @Override
    protected void onStart()
    {

        super.onStart();


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
