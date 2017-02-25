package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.gmail.saadbnwhd.popo_2.Adapters.League_Add_Fixture_Adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddFixture extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView txt_t1,txt_t2,date,time;
    Button Done;
    Firebase ref; //Reference to our DB
    ListView fixtureteamlist;
    League_Add_Fixture_Adapter fixtureadapter;
    ArrayList<String> team1 = new ArrayList<String>(); //String array for Team1 Names
    ArrayList<String> team2 = new ArrayList<String>(); //String array for Team2 Names
    ArrayList<String> DateTime=new ArrayList<String>();
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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("ADD FIXTURE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_fixture);
        Firebase.setAndroidContext(this);  //Setting up Firebase
        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        txt_t1=(TextView) findViewById(R.id.T1);
        txt_t2=(TextView) findViewById(R.id.T2);
        date = (TextView) findViewById(R.id.fix_date);
        time = (TextView) findViewById(R.id.fix_time);
        Done = (Button) findViewById(R.id.btn_done);
        fixtureteamlist = (ListView) findViewById(R.id.fixturelist);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date();
            }
        });
        txt_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_teamshow1();
            }
        });
        txt_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_teamshow2();
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Finalise();
            }
        });


        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams

        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                team1.add(dataSnapshot.getKey().toString());
                team2.add(dataSnapshot.getKey().toString());


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void txt_teamshow1() {
       // onStart();
        fixtureadapter = new League_Add_Fixture_Adapter(this, team1, imgid1,imgid2);
        final Dialog p = new Dialog(this);
      //  p.setTitle("txt_Team");
        p.setContentView(R.layout.activity_team);
        fixtureteamlist = (ListView) p.findViewById(R.id.list);
        fixtureteamlist.setAdapter(fixtureadapter);
        fixtureteamlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {

                txt_t1.setText(team1.get(position));
                p.dismiss();

            }
        });
        p.show();
    }
    public void txt_teamshow2() {
     //   onStart();
        fixtureadapter = new League_Add_Fixture_Adapter(this, team2, imgid1,imgid2);
        final Dialog p = new Dialog(this);
        //  p.setTitle("txt_Team");
        p.setContentView(R.layout.activity_team);
        fixtureteamlist = (ListView) p.findViewById(R.id.list);
        fixtureteamlist.setAdapter(fixtureadapter);
        fixtureteamlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view,
                                    int position, long id) {

                txt_t2.setText(team2.get(position));
                p.dismiss();

            }
        });
        p.show();
    }
    public void Date(){
        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                      //  Log.d(APIContanst.LOG_APP, dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                      date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void Time(){
        final Calendar c = Calendar.getInstance();
        mHour            = c.get(Calendar.HOUR_OF_DAY);
        mMinute          = c.get(Calendar.MINUTE);
        //launch timepicker modal
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       // Log.d(APIContanst.LOG_APP, "TIME SELECTED "+hourOfDay + "-" + minute + "-");
                        if(hourOfDay>12){
                            hourOfDay = hourOfDay-12;
                            time.setText(hourOfDay + ":" + minute +" pm");
                        }
                        else if(hourOfDay==0){
                            // hourOfDay = hourOfDay-12;
                            time.setText("12" + ":" + minute +" am");
                        }
                        else if(hourOfDay <12){
                            // hourOfDay = hourOfDay-12;
                            time.setText(hourOfDay + ":" + minute +" am");
                        }

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void Finalise(){
        if(!txt_t1.getText().equals("Team 1") && !txt_t2.getText().equals("Team 2")) {
            if (!txt_t1.getText().equals(txt_t2.getText())) {
                if (!date.getText().equals("Date")) {
                    if (!time.getText().equals("Time")) {


                        // SEND DATA TO DB
                        ref=ref.child("League").child("Fixtures");
                        String key=ref.push().getKey();

                        ref.child(key).child("Date").setValue(date.getText().toString());
                        ref.child(key).child("Team1").setValue(txt_t1.getText().toString());
                        ref.child(key).child("Team2").setValue(txt_t2.getText().toString());
                        ref.child(key).child("Time").setValue(time.getText().toString());

                        DateFormat dateFormatTS = new SimpleDateFormat("dd/MM/yyyy");
                        Date dateTS = null;
                        try {
                            dateTS = dateFormatTS.parse(date.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long TS = dateTS.getTime();

                        //java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(date.getText().toString());
                        ref.child(key).child("TimeStamp").setValue(TS);




                    } else {
                        Toast.makeText(this, "Set Time for Fixture", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Set Date for Fixture", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Select Separate Teams", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Select Teams Please", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onStart() {

        super.onStart();


    }





}