package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.utilities.Utilities;

import java.util.ArrayList;

public class PlayersFragment extends Fragment {
    ListView list,list1,list2;
    ArrayList<String> teams = new ArrayList<String>();

    private ArrayAdapter<String> listAdapter ;

    String[] TN = {
            "Fahad","SBW","Musab","Fahad","SBW","Musab","Fahad","SBW","Musab"
    };

    public PlayersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_players, container, false);

       FloatingActionButton fab=(FloatingActionButton) view.findViewById(R.id.playersfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamseditor = new Intent("android.intent.action.PlayerEditor");
                startActivity(teamseditor);
            }
        });

        list = (ListView) view.findViewById(R.id.players_list);
        list1 = (ListView) view.findViewById(R.id.players_list1);
        list2 = (ListView) view.findViewById(R.id.players_list2);
        popo_players_adap adapter = new popo_players_adap(getActivity().getBaseContext(), TN);

        list.setAdapter(adapter);
        ListUtils.setDynamicHeight(list);
        list1.setAdapter(adapter);
        ListUtils.setDynamicHeight(list1);
        list2.setAdapter(adapter);
        ListUtils.setDynamicHeight(list2);
        return view;
    }
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

}
