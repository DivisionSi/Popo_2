package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LeaguePlayer_Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_player__stats);
        Bundle bundle = getIntent().getExtras();
        String passingPlayerName = bundle.getString("passingPlayerName");
        String numb=bundle.getString("number");
        String club=bundle.getString("passingTeamName");
        String pos=bundle.getString("position");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle(passingPlayerName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView name=(TextView) findViewById(R.id.player_name);
        TextView position=(TextView) findViewById(R.id.player_position);
        TextView number=(TextView) findViewById(R.id.player_number);
        TextView team=(TextView) findViewById(R.id.player_club);
        name.setText(passingPlayerName);
        position.setText(pos);
        number.setText(numb);
        team.setText(club);
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
