package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddFixture extends AppCompatActivity  {
    TextView txt_t1,txt_t2;
    TextView Date, TIME;
    Calendar myCalendar;
    TimePicker time_pick;
    Button done;
    Firebase ref; //Reference to our DB
    ListView fixtureteamlist;
    DatePickerDialog.OnDateSetListener date;
    ArrayList<String> TEAMS = new ArrayList<String>(); //String array for Team1 Names

    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<String> DateTime=new ArrayList<String>();
    String AmPm,str_team1,str_team2,str_date,str_time;
    Integer fixture_id=0;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixture);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        myCalendar = Calendar.getInstance();

        txt_t1=(TextView) findViewById(R.id.T1);
        txt_t2=(TextView) findViewById(R.id.T2);
        done = (Button) findViewById(R.id.btn_done);
        Date = (TextView) findViewById(R.id.fix_date);
        TIME = (TextView) findViewById(R.id.fix_time);


        final Firebase FixturesRef=ref.child("League").child("Fixtures");

        FixturesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fixture_id++;

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


       Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams

        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);
                TEAMS.add(dataSnapshot.getKey().toString());

                locations.add(dataSnapshot.child("Location").getValue().toString());

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

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date.setText(sdf.format(myCalendar.getTime()));
                str_date=sdf.format(myCalendar.getTime());
            }

        };


        Date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddFixture.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });



        txt_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog p = new Dialog(AddFixture.this);
                p.setTitle("Team 1");
                p.setContentView(R.layout.leaguefixture);
                fixtureteamlist = (ListView) p.findViewById(R.id.fixturelist);
                final CustomListView adapter = new CustomListView(AddFixture.this, TEAMS,locations, imgid1);
                //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
                fixtureteamlist.setAdapter(adapter);
                p.show();

                fixtureteamlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        txt_t1.setText(TEAMS.get(i));
                        str_team1=TEAMS.get(i);
                       // Toast.makeText(AddFixture.this, TEAMS.get(i), Toast.LENGTH_SHORT).show();
                        p.dismiss();
                    }
                });
            }
        });

        txt_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog p = new Dialog(AddFixture.this);
                p.setTitle("Team 1");
                p.setContentView(R.layout.leaguefixture);
                fixtureteamlist = (ListView) p.findViewById(R.id.fixturelist);
                final CustomListView adapter = new CustomListView(AddFixture.this, TEAMS,locations, imgid1);
                //final ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,teams,locations);
                fixtureteamlist.setAdapter(adapter);
                p.show();

                fixtureteamlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        txt_t2.setText(TEAMS.get(i));
                        str_team2=TEAMS.get(i);
                        //Toast.makeText(AddFixture.this, TEAMS.get(i), Toast.LENGTH_SHORT).show();
                        p.dismiss();
                    }
                });
            }
        });




        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txt_t1.getText().equals("Team 1") && !txt_t2.getText().equals("Team 2") && !txt_t1.getText().equals(txt_t2.getText()) && !Date.getText().equals("")
                        && !TIME.getText().equals("")){



                    FixturesRef.child(Integer.toString(fixture_id)).child("Team1").setValue(str_team1);
                    FixturesRef.child(Integer.toString(fixture_id)).child("Team2").setValue(str_team2);
                    FixturesRef.child(Integer.toString(fixture_id)).child("Date").setValue(str_date);
                    FixturesRef.child(Integer.toString(fixture_id)).child("Time").setValue(str_time);
                        Toast.makeText(AddFixture.this, "Fixture Added", Toast.LENGTH_SHORT).show();
                    }


                else{
                    Toast.makeText(AddFixture.this, "Error in One or More Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TIME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddFixture.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String h = String.valueOf(selectedHour);
                        String m = String.valueOf(selectedMinute);

                       // if(sele)
                        if(h.length()<2)
                        {
                            h = "0"+h;
                        }
                        if(m.length()<2)
                        {
                            m = "0"+m;
                        }

                        if (selectedHour<12){
                            AmPm="AM";}
                        else {
                            int hr_int;
                            hr_int=Integer.parseInt(h);
                            hr_int=hr_int-12;
                            AmPm = "PM";
                            h=Integer.toString(hr_int);
                        }

                        TIME.setText( h+ " : " + m + " " + AmPm);
                        str_time=h+ ":" + m + " " + AmPm;
                    }
                }, hour, minute,false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }
}