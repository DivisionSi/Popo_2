package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayersFragment extends Fragment {
    ListView list;
    ArrayList<String> teams = new ArrayList<String>();

    private ArrayAdapter<String> listAdapter ;

    String[] TN = {
            "Fahad","SBW","Musab"
    };

    public PlayersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        teams.add("Fahad");
        teams.add("SBW");
        teams.add("Musab");
        popo_players_adap adapter = new popo_players_adap(getActivity().getBaseContext(), TN);

        list.setAdapter(adapter);


        return view;
    }


}
