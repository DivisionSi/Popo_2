package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;


public class PlayerEditor extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    Firebase ref;
    TextView txt_dob;
    ImageButton b;
    Button done,btn_dob;
    EditText txt_name,txt_jersey,txt_position;
    String name,jersey,position,dob;
    static Dialog p;
    static Dialog d ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_editor);
        Firebase.setAndroidContext(this);
        txt_name=(EditText) findViewById(R.id.txt_name);
        txt_jersey=(EditText) findViewById(R.id.txt_jersey);
        txt_position=(EditText) findViewById(R.id.txt_position);
        txt_dob=(TextView) findViewById(R.id.txt_dob);

        done=(Button) findViewById(R.id.btn_done);
        b = (ImageButton) findViewById(R.id.button11);

        ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");
        ref=ref.child("Popo").child("Players");




        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        btn_dob=(Button) findViewById(R.id.dob);

        btn_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent dob = new Intent("android.intent.action.Datepicker");
                // startActivity(dob);

                showDialog(999);

            }
        });


        txt_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_positionitionshow();
                           }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txt_name.equals("") && !txt_jersey.equals("") && !txt_position.equals("") && !txt_dob.equals("")) {
                    name = txt_name.getText().toString();
                    jersey = txt_jersey.getText().toString();
                    position = txt_position.getText().toString();
                    dob = txt_dob.getText().toString();

                    ref = ref.child(txt_name.getText().toString());
                    ref.child("Name").setValue(name);
                    ref.child("Position").setValue(position);
                    ref.child("Jersey Number").setValue(jersey);
                    ref.child("DoB").setValue(dob);

                    Toast.makeText(getApplicationContext(), "Player Added", Toast.LENGTH_LONG).show();



                }

                else
                    Toast.makeText(getApplicationContext(), "Kindly fill in all the fields!",Toast.LENGTH_LONG).show();


            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is", "" + newVal);

    }
public void txt_positionitionshow(){
    final Dialog p = new Dialog(this);
    p.setTitle("txt_positionition");
    p.setContentView(R.layout.position);

    final Button g, s, d, m, w;
    g = (Button) p.findViewById(R.id.goalkeeper);
    d = (Button) p.findViewById(R.id.defender);
    m = (Button) p.findViewById(R.id.midfielder);
    s = (Button) p.findViewById(R.id.striker);
    w = (Button) p.findViewById(R.id.winger);
    g.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txt_position.setText("Goal Keeper");
            p.dismiss();
        }
    });
    d.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txt_position.setText("Defender");
            p.dismiss();
        }
    });
    m.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txt_position.setText("Midfielder");
            p.dismiss();
        }
    });
    s.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txt_position.setText("Striker");
            p.dismiss();
        }
    });
    w.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txt_position.setText("Winger");
            p.dismiss();
        }
    });
p.show();

}


    public void show()
    {

        final Dialog d = new Dialog(this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(99);
        np.setMinValue(0);

        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_jersey.setText(String.valueOf(np.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {

           Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(this, myDateListener, 2000, month, day);
        }
        return null;
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
       txt_dob.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }




}
