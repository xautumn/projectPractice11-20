package com.autumn.a9_27sensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    private BumblebeeBatteryLeftStepView bumblebeeStoreBatteryView;
    private BumblebeeBatteryRightBatteryView bumblebeeStoreBatteryViewRight;
    int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        RelativeLayout viewById = findViewById(R.id.rl_root);
        LottieAnimationView lottieAnimationView = new LottieAnimationView(this);
        viewById.addView(lottieAnimationView,0);
        lottieAnimationView.setAnimation("bumblebee" + "/voice.json");
        //lottieAnimationView.setImageAssetsFolder("bumblebee" + "/images/");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        bumblebeeStoreBatteryView = findViewById(R.id.iv_battery_left);
        bumblebeeStoreBatteryViewRight = findViewById(R.id.iv_battery_right);

        bumblebeeStoreBatteryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wq", "bumblebeeStoreBatteryView click");
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
                finish();
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //bumblebeeStoreBatteryView.setStepNumber(800 * (k % 10));
                //bumblebeeStoreBatteryViewRight.setBatteryNumber(10 * (k % 10));
                k++;
            }
        },1000,1000);
    }
}
