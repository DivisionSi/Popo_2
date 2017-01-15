package com.gmail.saadbnwhd.popo_2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by Musab on 12/6/2016.
 */

public class HomeFragment extends Fragment {
    ImageView img;

    public HomeFragment() {





    }

    EditText txt_username,txt_password;
    Button Signin;
    String username,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth=firebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        txt_username=(EditText) view.findViewById(R.id.txt_username);
        txt_password=(EditText) view.findViewById(R.id.txt_password);
        Signin=(Button) view.findViewById(R.id.btn_signin);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
                return view;
    }

    private void userLogin()
    {
        String username=txt_username.getText().toString();
        String password=txt_password.getText().toString();

        progressDialog.setMessage("Logging in please wait.. " + username);
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if ( task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Logged in",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
