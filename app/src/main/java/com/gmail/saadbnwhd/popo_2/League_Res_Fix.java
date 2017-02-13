package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;

public class League_Res_Fix extends AppCompatActivity {

    Button done;
    Firebase ref;
    EditText goals1,goals2;

    Integer int_goals1,int_apps1,int_won1,int_lost1,int_drawn1,
            int_goals2,int_apps2,int_won2,int_lost2,int_drawn2,
            fixtureGoals1,fixtureGoals2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_res_fixx);
        Firebase.setAndroidContext(League_Res_Fix.this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        Bundle bundle=getIntent().getExtras();
        String Team1 = bundle.getString("t1");
        String Team2 = bundle.getString("t2");


        TextView T1 = (TextView) findViewById(R.id.team1);
        TextView  T2 = (TextView) findViewById(R.id.team2);
        T1.setText(Team1);
        T2.setText(Team2);
        // list.setAdapter(adapter);
        goals1=(EditText) findViewById(R.id.score_team1);
        goals2=(EditText) findViewById(R.id.score_team2);

        final Firebase team1_stats=ref.child("League").child("Stats").child(Team1);

        team1_stats.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int_goals1=Integer.parseInt(dataSnapshot.child("Goals").getValue().toString());
                int_apps1=Integer.parseInt(dataSnapshot.child("Apps").getValue().toString());
                int_won1=Integer.parseInt(dataSnapshot.child("Won").getValue().toString());
                int_drawn1=Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString());
                int_lost1=Integer.parseInt(dataSnapshot.child("Lost").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Firebase team2_stats=ref.child("League").child("Stats").child(Team2);

        team2_stats.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int_goals2=Integer.parseInt(dataSnapshot.child("Goals").getValue().toString());
                int_apps2=Integer.parseInt(dataSnapshot.child("Apps").getValue().toString());
                int_won2=Integer.parseInt(dataSnapshot.child("Won").getValue().toString());
                int_drawn2=Integer.parseInt(dataSnapshot.child("Drawn").getValue().toString());
                int_lost2=Integer.parseInt(dataSnapshot.child("Lost").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        done=(Button) findViewById(R.id.result_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fixtureGoals1=Integer.parseInt(goals1.getText().toString());
                fixtureGoals2=Integer.parseInt(goals2.getText().toString());

                //Appearances Increment
                int_apps1++;
                team1_stats.child("Apps").setValue(Integer.toString(int_apps1));

                int_apps2++;
                team2_stats.child("Apps").setValue(Integer.toString(int_apps2));

                //Goals Update
                int_goals1=int_goals1+Integer.parseInt(goals1.getText().toString());
                team1_stats.child("Goals").setValue(Integer.toString(int_goals1));

                int_goals2=int_goals2+Integer.parseInt(goals2.getText().toString());
                team2_stats.child("Goals").setValue(Integer.toString(int_goals2));

                if(fixtureGoals1>fixtureGoals2)
                {
                    int_won1++;
                    team1_stats.child("Won").setValue(Integer.toString(int_won1));

                    int_lost2++;
                    team2_stats.child("Lost").setValue(Integer.toString(int_lost2));
                }
                else if (fixtureGoals1<fixtureGoals2)
                {
                    int_won2++;
                    team2_stats.child("Won").setValue(Integer.toString(int_won2));

                    int_lost1++;
                    team1_stats.child("Lost").setValue(Integer.toString(int_lost2));

                }
                else
                {

                    int_drawn1++;
                    team1_stats.child("Drawn").setValue(Integer.toString(int_drawn1));

                    int_drawn2++;
                    team2_stats.child("Drawn").setValue(Integer.toString(int_drawn2));


                }

            }
        });


    }

    @Override
    protected void onStart() {

        super.onStart();

    }
}

