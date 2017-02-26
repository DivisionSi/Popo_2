package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TimePicker;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.FixtureListView;
import com.gmail.saadbnwhd.popo_2.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Musab on 2/20/2017.
 */
public class ResultsFragment extends Fragment {
    ListView list;

    ArrayList<String> team1 = new ArrayList<String>(); //String array for Team A
    ArrayList<String> team2 = new ArrayList<String>(); //String array for Team B
    ArrayList<String> DateTime = new ArrayList<String>(); //String array for DateTime of Fixture

    Integer[] imgid1 = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
    };
    Integer[] imgid2 = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
    };

    FloatingActionButton add;

    Firebase ref;
    private TimePicker timePicker1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(getContext());  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_fixtures, container, false);



        add = (FloatingActionButton) view.findViewById(R.id.fix_add);
     //   add.setVisibility(INVISIBLE);
        list = (ListView) view.findViewById(R.id.Fxlist);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getContext(),POPO_Results_Edit.class);
                i.putExtra("TEAM1",team1);
                i.putExtra("TEAM2",team2);
                i.putExtra("DateTime",DateTime);
                startActivity(i);
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
            add.setVisibility(View.VISIBLE);


        Start();
        return view;
    }

    public void Start(){
        team1.clear();
        team2.clear();
        DateTime.clear();

        Firebase.setAndroidContext(getActivity());  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");



        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = team1.get(+position);
                //    Toast.makeText(getApplicationContext(), Slecteditem, LENGTH_SHORT).show();
            }
        });


        Firebase FixturesRef; //Reference to Teams node
        FixturesRef=ref.child("Popo").child("Results");  //Traversing to Fixtures

        final FixtureListView adapter = new FixtureListView(getActivity(), team1,team2,DateTime,imgid1,imgid2);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);


        FixturesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map=dataSnapshot.getValue(Map.class);
                //  Toast.makeText(getApplicationContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_LONG).show();
                team1.add("Popo FC " + dataSnapshot.child("Popo Score").getValue());
                team2.add(dataSnapshot.child("Rival Score").getValue() + " " + dataSnapshot.child("Rival").getValue().toString());
                DateTime.add(dataSnapshot.child("DateTime").getValue().toString());


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
}
