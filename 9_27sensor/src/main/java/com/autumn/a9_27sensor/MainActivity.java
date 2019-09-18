package com.autumn.a9_27sensor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private BumblebeeBatteryLeftView bumblebeeStoreBatteryView;
    private BumblebeeBatteryRightView bumblebeeStoreBatteryViewRight;
    int k = 0;
    private String TAG = "wq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("wq", "onCreate");
        getSupportActionBar().hide();
        bumblebeeStoreBatteryView = findViewById(R.id.iv_battery_left);
        bumblebeeStoreBatteryViewRight = findViewById(R.id.iv_battery_right);

        bumblebeeStoreBatteryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wq", "bumblebeeStoreBatteryView click");
                //startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

       /* Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bumblebeeStoreBatteryView.setBatteryNumber(5 * (k % 20));
                bumblebeeStoreBatteryViewRight.setBatteryNumber(5 * (k % 20));
                k++;
            }
        },1000,1000);*/
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        //startActivity(new Intent(MainActivity.this,Main2Activity.class));
        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("wq", "onStart");
        //Thread.sleep(1000);

    }

    @Override
    protected void onResume() {
        Log.i("wq", "onResume");
        super.onResume();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}