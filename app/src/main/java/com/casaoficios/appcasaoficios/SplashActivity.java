package com.casaoficios.appcasaoficios;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by Jaime on 14/11/2017.
 */

public class SplashActivity extends AppCompatActivity {

  LottieAnimationView lottieAnimationView ;
Button button ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        int tiempo = Integer.parseInt(getString(R.string.timewaitsplash));


        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
//        button = (Button) findViewById(R.id.button);
//        button.setText(getString(R.string.pause));

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, InicialActivity.class));
                finish();
            }
        }, secondsDelayed * tiempo);


    }


//    public void animate(View v) {
//        if (lottieAnimationView.isAnimating()) {
//            lottieAnimationView.cancelAnimation();
//            button.setText(getString(R.string.play));
//        } else {
//            lottieAnimationView.playAnimation();
//            button.setText(getString(R.string.pause));
//        }
//    }

}
