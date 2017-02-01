package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_Results extends Activity {
ArrayList<String> Goal_Scorers=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__results);
        Bundle bundle = getIntent().getExtras();

        Goal_Scorers = bundle.getStringArrayList("goals");

        TextView text=(TextView) findViewById(R.id.txt_result_goals);

        text.setText(Goal_Scorers.get(0));
    }
}
