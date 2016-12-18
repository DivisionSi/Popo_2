package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseException;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * Created by Musab on 12/6/2016.
 */
public class FixturesFragment extends Fragment {
   /* public FixturesFragment() {

//fragment
    }*/

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            popo_fixtures_ref.child(rivals.getText().toString()).child("Date").setValue(date.getText().toString());
                            popo_fixtures_ref.child(rivals.getText().toString()).child("Time").setValue(time.getText().toString());
                            Toast.makeText(getContext(),"Fixture Added", LENGTH_SHORT).show();

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
        });

        return view;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year;
            /// arg2 = month;
            //arg3 = day;

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
}