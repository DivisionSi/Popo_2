package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import java.util.Calendar;


public class PlayerEditor extends AppCompatActivity implements NumberPicker.OnValueChangeListener {




    TextView txt_dob;
    public ImageButton b;

    Button done,dob;
    EditText txt_jersey,txt_position;
    static Dialog p;
    static Dialog d ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_editor);

        txt_jersey=(EditText) findViewById(R.id.txt_jersey);
        txt_position=(EditText) findViewById(R.id.txt_position);
        txt_dob=(TextView) findViewById(R.id.txt_dob);

     b = (ImageButton) findViewById(R.id.button11);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        dob=(Button) findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
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
        np.setMaxValue(100);
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

            return new DatePickerDialog(this, myDateListener, year, month, day);
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
