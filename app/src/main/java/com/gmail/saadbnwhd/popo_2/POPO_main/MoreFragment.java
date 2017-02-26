package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.saadbnwhd.popo_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MoreFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        }

    EditText txt_username,txt_password;
    Button Signin,Signout;
    String username,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_more, container, false);

        txt_username=(EditText) view.findViewById(R.id.txt_username);
        txt_password=(EditText) view.findViewById(R.id.txt_password);
        Signin=(Button) view.findViewById(R.id.btn_signin);
        Signout=(Button) view.findViewById(R.id.btn_signout);

        firebaseAuth=firebaseAuth.getInstance();
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
           Signout.setVisibility(View.VISIBLE);


        return view;
    }

    private void userLogin()
    {
        username=txt_username.getText().toString();
        password=txt_password.getText().toString();


        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if ( task.isSuccessful())
                {

                    Toast.makeText(getActivity(),"Logged in",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}