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
import java.util.Calendar;

public class PlayersFragment extends Fragment {
    ListView list_sr,list_u16,list_u14;
    ArrayList<String> Popo_players_sr = new ArrayList<String>();
    ArrayList<String> Popo_players_u16 = new ArrayList<String>();
    ArrayList<String> Popo_players_u14 = new ArrayList<String>();

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

        list_sr = (ListView) view.findViewById(R.id.players_list);
        list_u16 = (ListView) view.findViewById(R.id.players_list1);
        list_u14 = (ListView) view.findViewById(R.id.players_list2);

        final popo_players_adap adapter_sr = new popo_players_adap(getActivity().getBaseContext(), Popo_players_sr);
        final popo_players_adap adapter_u16 = new popo_players_adap(getActivity().getBaseContext(), Popo_players_u16);
        final popo_players_adap adapter_u14 = new popo_players_adap(getActivity().getBaseContext(), Popo_players_u14);

        list_sr.setAdapter(adapter_sr);
        list_u16.setAdapter(adapter_u16);
        list_u14.setAdapter(adapter_u14);

        Firebase ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        Firebase popo_player_ref=ref.child("Popo").child("Players");
        Integer age;


        popo_player_ref.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Popo_players_sr.add(dataSnapshot.getKey().toString());
                adapter_sr.notifyDataSetChanged();

                Popo_players_u16.add(dataSnapshot.getKey().toString());
                adapter_u16.notifyDataSetChanged();
                Popo_players_u14.add(dataSnapshot.getKey().toString());
                adapter_u14.notifyDataSetChanged();



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
