package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.gmail.saadbnwhd.popo_2.Logos;
import com.gmail.saadbnwhd.popo_2.R;

public class SplashScreen extends AppCompatActivity {
ImageView img,img1;
    public Logos logos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logos=new Logos();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        img=(ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.poponfa);
        img1=(ImageView) findViewById(R.id.image1);
        img1.setImageResource(R.drawable.football);
        RotateAnimation anim;
        anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(2000);//timer
        img1.startAnimation(anim);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Connection();
                    }
                }, 2000);
    }


    public void Connection(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            Intent main=new Intent("android.intent.action.mainmenu");
            startActivity(main);
            finish();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
            builder.setMessage("Please Check Internet Connectivity")
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                            startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));

                           // startActivity(in);
                        }
                    })
                    .create()
                    .show();
        }
    }
}
