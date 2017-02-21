package com.gmail.saadbnwhd.popo_2.POPO_main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.gmail.saadbnwhd.popo_2.R;

public class SplashScreen extends AppCompatActivity {
ImageView img,img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
       Thread timer =new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {

                } finally {
                  //  FirebaseCrash.report(new Exception("My first Android non-fatal error"));
                    Intent main=new Intent("android.intent.action.mainmenu");
                    startActivity(main);
                }
            }
        };
        timer.start();
    };

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
