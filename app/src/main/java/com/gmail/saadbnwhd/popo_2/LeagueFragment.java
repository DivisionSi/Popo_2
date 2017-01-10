package com.gmail.saadbnwhd.popo_2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Musab on 12/6/2016.
 */
public class LeagueFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View view= inflater.inflate(R.layout.fragment_league, container, false);
        Button button = (Button) view.findViewById(R.id.teams);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Team=new Intent("android.intent.action.Teams");
                startActivity(Team);
            }
        });

        Button btn_fixtures=(Button) view.findViewById(R.id.btn_fixtures);
        btn_fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Fixtures=new Intent("android.intent.action.Fixtures");
                startActivity(Fixtures);
            }
        });

        Button btn_results=(Button) view.findViewById(R.id.btn_results);
        btn_results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Res=new Intent(getContext(),League_Res_Fix.class);
                startActivity(Res);
            }
        });

        return view;
    }


}