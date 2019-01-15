package com.example.hp.allyseek;

import android.content.Intent;
import android.graphics.PixelFormat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hp.allyseek.registration.Registrationp3;

public class SplashScreen extends AppCompatActivity
{
    Animation anim;
    LinearLayout l;
    ImageView logoImage;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logoImage = (ImageView) findViewById(R.id.logo);
        anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animalpha);
        logoImage.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

                Intent intent=new Intent(SplashScreen.this,Registrationp3.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}
