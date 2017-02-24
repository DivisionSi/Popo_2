package com.gmail.saadbnwhd.popo_2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class PlayerEditor extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    Firebase ref;
    TextView txt_dob;
    ImageButton b;
    Button done,btn_dob;
    EditText txt_name,txt_jersey,txt_position;
    String name,jersey,position,dob,AgeGroup,passingTeamName;
    RadioGroup rad_AgeGroup;
    static Dialog p;
    static Dialog d ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_player_editor);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.newclr1));
        }

       Boolean isPopo=getIntent().getBooleanExtra("isPopo",false);

        if (!isPopo)
            passingTeamName=getIntent().getStringExtra("passingTeamName");


        Firebase.setAndroidContext(this);
        txt_name=(EditText) findViewById(R.id.txt_name);
        txt_jersey=(EditText) findViewById(R.id.txt_jersey);
        txt_position=(EditText) findViewById(R.id.txt_position);
        txt_dob=(TextView) findViewById(R.id.txt_dob);
        rad_AgeGroup=(RadioGroup) findViewById(R.id.AgeGroup);


        AgeGroup="";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newclr)));
        getSupportActionBar().setTitle("Add Player");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        done=(Button) findViewById(R.id.btn_done);
        b = (ImageButton) findViewById(R.id.button11);

        if(isPopo) {
            ref = new Firebase("https://poponfa-8a11a.firebaseio.com/");
            ref = ref.child("Popo").child("Players");
        }
        else{
            ref = new Firebase("https://poponfa-8a11a.firebaseio.com/");
        ref = ref.child("League").child("Teams").child(passingTeamName).child("Players");}



        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        btn_dob=(Button) findViewById(R.id.dob);


        txt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });
        txt_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_positionitionshow();
                           }
        });

        txt_jersey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });



        rad_AgeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id=rad_AgeGroup.getCheckedRadioButtonId();
                Toast.makeText(getApplicationContext(), Integer.toString(id), Toast.LENGTH_LONG).show();

                if(id==2131493026)
                    AgeGroup="Senior";
                else if(id==2131493028)
                    AgeGroup="Under 16";
                else
                    AgeGroup="Under 14";
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txt_name.equals("") && !txt_jersey.equals("") && !txt_position.equals("") && !txt_dob.equals("") && !AgeGroup.equals("")) {


                    name = txt_name.getText().toString();
                    jersey = txt_jersey.getText().toString();
                    position = txt_position.getText().toString();
                    dob = txt_dob.getText().toString();

                    Map<String,String> testmap=new HashMap<String,String>();
                    testmap.put("Name",name);
                    testmap.put("Position",position);
                    testmap.put("Jersey Number",jersey);
                    testmap.put("DoB",dob);
                    testmap.put("Age Group",AgeGroup);
                    testmap.put("Goals",String.valueOf(0));
                    testmap.put("Apps",String.valueOf(0));

                    String Key=ref.push().getKey();

                    ref.child(Key).setValue(testmap);


                    /* ref.child("Name").setValue(name);
                    ref.child("Position").setValue(position);
                    ref.child("Jersey Number").setValue(jersey);
                    ref.child("DoB").setValue(dob);
                    ref.child("Age Group").setValue(AgeGroup);*/


                    Toast.makeText(getApplicationContext(), "Player Added", Toast.LENGTH_LONG).show();



                }

                else
                    Toast.makeText(getApplicationContext(), "Kindly fill in all the fields!",Toast.LENGTH_LONG).show();


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
    public boolean onSupportNavigateUp(){
        finish();
        return true;
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

    @Override
    protected void onStart()
    {

        super.onStart();


    }
    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }



}
