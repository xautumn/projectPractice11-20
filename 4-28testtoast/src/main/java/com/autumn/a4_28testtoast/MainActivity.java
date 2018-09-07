package com.autumn.a4_28testtoast;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Log.i("wq","thread = " +Thread.currentThread());
                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }
}
