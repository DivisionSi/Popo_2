package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class PlayersFragment extends Fragment {
    ListView list;
    ArrayList<String> Popo_players = new ArrayList<String>();

    private ArrayAdapter<String> listAdapter ;



    public PlayersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getActivity().getApplicationContext());





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_players, container, false);

       FloatingActionButton fab=(FloatingActionButton) view.findViewById(R.id.playersfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamseditor=new Intent("android.intent.action.PlayerEditor");
                startActivity(teamseditor);
            }
        });

        list = (ListView) view.findViewById(R.id.players_list);

        final popo_players_adap adapter = new popo_players_adap(getActivity().getBaseContext(), Popo_players);

        list.setAdapter(adapter);
        Firebase ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        Firebase popo_player_ref=ref.child("Popo").child("Players");


        popo_player_ref.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Popo_players.add(dataSnapshot.getKey().toString());
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

        return view;
    }


}
