package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.gmail.saadbnwhd.popo_2.FixtureListView;
import com.gmail.saadbnwhd.popo_2.R;

//import java.sql.Date;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * Created by Musab on 12/6/2016.
 */
public class FixturesFragment extends Fragment {
   /* public FixturesFragment() {

//fragment
    }*/
   ListView list;
    ProgressBar pb;

    ArrayList<String> team1 = new ArrayList<String>(); //String array for Team A
    ArrayList<String> team2 = new ArrayList<String>(); //String array for Team B
    ArrayList<String> DateTime = new ArrayList<String>(); //String array for DateTime of Fixture
    ArrayList<Image> img1 = new ArrayList<Image>();
    Integer[] imgid1 = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3
    };
    Integer[] imgid2 = {
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2,
            R.drawable.logo2,
            R.drawable.logo3,
            R.drawable.logo2
    };

    FloatingActionButton add;
    EditText rivals;
    TextView date,time;
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
        list = (ListView) view.findViewById(R.id.Fxlist);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Create();
            }
        });


        Start();
        return view;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            setDate(arg1, arg2 + 1, arg3);
        }

    };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            setTime(hourOfDay, minute);
        }

    };

    private void setTime(int hour, int min) {
        String AM_PM ;
        if(hour < 12) {
            AM_PM = "AM";
        } else {
            hour=hour-12;
            AM_PM = "PM";
        }
        time.setText(new StringBuilder().append(hour).append(":").append(min).append(" ").append(AM_PM));
    }

    private void setDate(int year, int month, int day) {
        date.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    public void Create(){
        final Dialog a = new Dialog(getContext());
        a.setContentView(R.layout.add_fixtures);
        rivals = (EditText)a.findViewById(R.id.t_name);
        date = (TextView)a.findViewById(R.id.t_date);
        time=(TextView) a.findViewById(R.id.t_time);

        final Button done = (Button)a.findViewById(R.id.done);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp = new DatePickerDialog(getContext(), myDateListener, year, month, day);

                dp.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog tp = new TimePickerDialog(getContext(), myTimeListener, hour,minute,false);

                tp.show();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Firebase popo_fixtures_ref = ref.child("Popo").child("Fixtures");
                    String key=popo_fixtures_ref.push().getKey();

                    popo_fixtures_ref.child(key).child("Rival").setValue(rivals.getText().toString());
                    popo_fixtures_ref.child(key).child("Date").setValue(date.getText().toString());
                    popo_fixtures_ref.child(key).child("Time").setValue(time.getText().toString());
                    Toast.makeText(getContext(),"Fixture Added", LENGTH_SHORT).show();


                    DateFormat dateFormatTS = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateTS = null;
                    try {
                        dateTS = dateFormatTS.parse(date.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long TS = dateTS.getTime();

                    //java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(date.getText().toString());
                    popo_fixtures_ref.child(key).child("TimeStamp").setValue(TS);

                }
                catch (FirebaseException i)
                {
                    Toast.makeText(getContext(), i.toString(), LENGTH_SHORT).show();
                }
                a.dismiss();
            }
        });

        a.show();
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
        FixturesRef=ref.child("Popo").child("Fixtures");  //Traversing to Fixtures

        final FixtureListView adapter = new FixtureListView(getActivity(), team1,team2,DateTime,imgid1,imgid2);
        //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
        list.setAdapter(adapter);

        try {
            FixturesRef.orderByChild("TimeStamp").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Map<String, String> map = dataSnapshot.getValue(Map.class);
                    //  Toast.makeText(getApplicationContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_LONG).show();
                    team1.add("Popo FC");
                    team2.add(dataSnapshot.child("Rival").getValue().toString());
                    DateTime.add(dataSnapshot.child("Date").getValue().toString() + " | " +
                            dataSnapshot.child("Time").getValue().toString());


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
        catch (Exception e)
        {
            Log.e("Exception",e.toString());
        }
    }
}