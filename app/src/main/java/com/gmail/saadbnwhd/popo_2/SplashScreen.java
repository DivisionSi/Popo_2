package com.gmail.saadbnwhd.popo_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                    sleep(3000);
                } catch (InterruptedException e) {

                } finally {
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
