package com.gmail.saadbnwhd.popo_2;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * Created by Musab on 12/6/2016.
 */

public class HomeFragment extends Fragment {
    ImageView img;

    public HomeFragment() {





    }

    EditText txt_username,txt_password;
    String username,password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
}
