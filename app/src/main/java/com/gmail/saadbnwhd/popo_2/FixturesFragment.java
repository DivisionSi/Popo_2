package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import java.util.Calendar;


/**
 * Created by Musab on 12/6/2016.
 */
public class FixturesFragment extends Fragment {
   /* public FixturesFragment() {

//fragment
    }*/

    FloatingActionButton add;
    EditText rivals;
    TextView date;
    EditText time;

    @Override
    public void onCreate(Bundle savedInstanceState) {

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
                Dialog a = new Dialog(getContext());
                a.setContentView(R.layout.add_fixtures);
                rivals = (EditText)a.findViewById(R.id.t_name);
                date = (TextView)a.findViewById(R.id.t_date);
                time= (EditText)a.findViewById(R.id.t_time);
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

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Toast.makeText(getContext(), "Please Fill all fields", Toast.LENGTH_LONG).show();
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

    private void setDate(int year, int month, int day) {
        date.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
}