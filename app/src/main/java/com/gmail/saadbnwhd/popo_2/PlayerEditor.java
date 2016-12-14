package com.gmail.saadbnwhd.popo_2;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

public class PlayerEditor extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
TextView tv;
    public ImageButton b;
   static Button dob,pos;
    Button done,tv1;
    static Dialog p;
    static Dialog d ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_editor);
        tv = (TextView) findViewById(R.id.textView1);
        tv1 = (Button) findViewById(R.id.text);
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
                Intent dob = new Intent("android.intent.action.Datepicker");
                startActivity(dob);

            }
        });
        pos=(Button)findViewById(R.id.position);

        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionshow();
                           }
        });

    }
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is", "" + newVal);

    }
public void positionshow(){
    final Dialog p = new Dialog(this);
    p.setTitle("Position");
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
            pos.setText("Goal Keeper");
            p.dismiss();
        }
    });
    d.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pos.setText("Defender");
            p.dismiss();
        }
    });
    m.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pos.setText("Midfielder");
            p.dismiss();
        }
    });
    s.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pos.setText("Striker");
            p.dismiss();
        }
    });
    w.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pos.setText("Winger");
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
                tv.setText("");
                tv1.setText(String.valueOf(np.getValue()));
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
}
