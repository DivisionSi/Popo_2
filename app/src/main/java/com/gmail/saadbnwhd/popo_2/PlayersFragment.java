package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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

        View view = inflater.inflate(R.layout.fragment_players, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.playersfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamseditor = new Intent("android.intent.action.PlayerEditor");
                startActivity(teamseditor);
            }
        });

        list_sr = (ListView) view.findViewById(R.id.scorers_list);
        list_u16 = (ListView) view.findViewById(R.id.scorers_list1);
        list_u14 = (ListView) view.findViewById(R.id.players_list2);

        final popo_players_adap adapter_sr = new popo_players_adap(getActivity().getBaseContext(), Popo_players,Popo_players_numbers,Popo_players_postion);
        final popo_players_adap adapter_u16 = new popo_players_adap(getActivity().getBaseContext(), Popo_players_u16,Popo_players_u16_numbers,Popo_players_u16_postion);
        final popo_players_adap adapter_u14= new popo_players_adap(getActivity().getBaseContext(), Popo_players,Popo_players_u14_numbers,Popo_players_u14_postion);

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
                Toast.makeText(getContext(),AgeGroup,Toast.LENGTH_SHORT).show();

                if(AgeGroup.equals("Senior"))

                { Popo_players.add(dataSnapshot.getKey().toString());
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
                //else if (AgeGroup.equals("Under 14"))
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
