package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.Adapters.popo_players_adap;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PlayersFragment extends Fragment {
    ListView list_sr, list_u16, list_u14;
    ArrayList<String> Popo_players = new ArrayList<String>();
    ArrayList<String> Popo_players_numbers = new ArrayList<String>();
    ArrayList<String> Popo_players_postion = new ArrayList<String>();

    ArrayList<String> Popo_players_u16 = new ArrayList<String>();
    ArrayList<String> Popo_players_u16_numbers = new ArrayList<String>();
    ArrayList<String> Popo_players_u16_postion = new ArrayList<String>();

    ArrayList<String> Popo_players_u14= new ArrayList<String>();
    ArrayList<String> Popo_players_u14_numbers = new ArrayList<String>();
    ArrayList<String> Popo_players_u14_postion = new ArrayList<String>();

    String AgeGroup;


    private ArrayAdapter<String> listAdapter;


    public PlayersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getActivity().getApplicationContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        clearArrays();
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.playersfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamseditor = new Intent("android.intent.action.PlayerEditor");
                Bundle b = new Bundle();
                b.putBoolean("isPopo",true);
                teamseditor.putExtras(b);
                startActivity(teamseditor);
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
            fab.setVisibility(View.VISIBLE);


        list_sr = (ListView) view.findViewById(R.id.scorers_list);

        list_sr.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent stats=new Intent(getActivity(),LeaguePlayer_Stats.class);
                stats.putExtra("passingPlayerName", Popo_players);
                stats.putExtra("number", Popo_players_numbers);
                stats.putExtra("position", Popo_players_postion);
                startActivity(stats);
            }

        });
        list_u16 = (ListView) view.findViewById(R.id.scorers_list1);
        list_u16.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent stats = new Intent(getActivity(), LeaguePlayer_Stats.class);
                stats.putExtra("passingPlayerName", Popo_players_u16);
                stats.putExtra("number", Popo_players_u16_numbers);
                stats.putExtra("position", Popo_players_u16_postion);
                startActivity(stats);
            }

        });

        list_u14 = (ListView) view.findViewById(R.id.players_list2);
list_u14.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent stats = new Intent(getActivity(), LeaguePlayer_Stats.class);
        stats.putExtra("passingPlayerName", Popo_players_u14);
        stats.putExtra("number", Popo_players_u14_numbers);
        stats.putExtra("position", Popo_players_u14_postion);
        startActivity(stats);
    }

});
        final popo_players_adap adapter_sr = new popo_players_adap(getActivity().getBaseContext(), Popo_players,Popo_players_numbers,Popo_players_postion);
        final popo_players_adap adapter_u16 = new popo_players_adap(getActivity().getBaseContext(), Popo_players_u16,Popo_players_u16_numbers,Popo_players_u16_postion);
        final popo_players_adap adapter_u14= new popo_players_adap(getActivity().getBaseContext(), Popo_players_u14,Popo_players_u14_numbers,Popo_players_u14_postion);

        list_sr.setAdapter(adapter_sr);

        list_u16.setAdapter(adapter_u16);

        list_u14.setAdapter(adapter_u14);

        Firebase ref = new Firebase("https://poponfa-8a11a.firebaseio.com/");
        Firebase popo_player_ref = ref.child("Popo").child("Players");
        Integer age;


        popo_player_ref.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                AgeGroup=dataSnapshot.child("Age Group").getValue().toString();


                if(AgeGroup.equals("Senior"))

                { Popo_players.add(dataSnapshot.child("Name").getValue().toString());
                Popo_players_numbers.add(dataSnapshot.child("Jersey Number").getValue().toString());
                Popo_players_postion.add(dataSnapshot.child("Position").getValue().toString());
                adapter_sr.notifyDataSetChanged();}

                if (AgeGroup.equals("Under 16"))
                {
                    Popo_players_u16.add(dataSnapshot.getKey().toString());
                    Popo_players_u16_numbers.add(dataSnapshot.child("Jersey Number").getValue().toString());
                    Popo_players_u16_postion.add(dataSnapshot.child("Position").getValue().toString());
                    adapter_u16.notifyDataSetChanged();
                }
                else if (AgeGroup.equals("Under 14"))
                {
                Popo_players_u14.add(dataSnapshot.getKey().toString());
                Popo_players_u14_numbers.add(dataSnapshot.child("Jersey Number").getValue().toString());
                Popo_players_u14_postion.add(dataSnapshot.child("Position").getValue().toString());
                adapter_u14.notifyDataSetChanged();}


                ListUtils.setDynamicHeight(list_sr);
                ListUtils.setDynamicHeight(list_u16);
                ListUtils.setDynamicHeight(list_u14);

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
    private void clearArrays()
    {
        Popo_players.clear();
        Popo_players_u16.clear();
        Popo_players_u14.clear();
        Popo_players_postion.clear();
        Popo_players_u16_postion.clear();
        Popo_players_u14_postion.clear();
        Popo_players_numbers.clear();
        Popo_players_u16_numbers.clear();
        Popo_players_u14_numbers.clear();




    }

    public void Start()
    {



    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }

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
        getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
