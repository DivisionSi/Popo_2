package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

public class LeaguePlayer_Stats extends AppCompatActivity {
    String dob,age_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_player_stats);
        Bundle bundle = getIntent().getExtras();
        String passingPlayerName = bundle.getString("passingPlayerName");
        String numb=bundle.getString("number");
        String club=bundle.getString("passingTeamName");
        String pos=bundle.getString("position");


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }
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
        final TextView dob=(TextView) findViewById(R.id.dob);
        final TextView age_group=(TextView) findViewById(R.id.age_group);
        final TextView txt_apps=(TextView) findViewById(R.id.txt_apps);
        final TextView txt_goals=(TextView) findViewById(R.id.txt_goals);

        Firebase.setAndroidContext(this);
        Firebase ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        final Firebase playerref=ref.child("League").child("Teams").child(club).child("Players").child(passingPlayerName);

        playerref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dob.setText(dataSnapshot.child("DoB").getValue().toString());
                age_group.setText(dataSnapshot.child("Age Group").getValue().toString());
                txt_apps.setText(dataSnapshot.child("Apps").getValue().toString());
                txt_goals.setText(dataSnapshot.child("Goals").getValue().toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Button delete=(Button) findViewById(R.id.delete);
        delete.setVisibility(View.INVISIBLE);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
            delete.setVisibility(View.VISIBLE);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerref.removeValue();
                Toast.makeText(getApplication(),"Player Deleted",Toast.LENGTH_SHORT).show();
                delete.setVisibility(view.INVISIBLE);
            }
        });

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
    @Override
    protected void onStart()
    {
        super.onStart();

    }
}
