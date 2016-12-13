package com.gmail.saadbnwhd.popo_2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

public class PlayerEditor extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
TextView tv,tv1;
    public ImageButton b;
    static Dialog d ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_editor);
        tv = (TextView) findViewById(R.id.textView1);
        tv1 = (TextView) findViewById(R.id.text);
     b = (ImageButton) findViewById(R.id.button11);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
    }
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is", "" + newVal);

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
        np.setWrapSelectorWheel(false);
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
