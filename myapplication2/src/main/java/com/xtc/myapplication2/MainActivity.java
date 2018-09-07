package com.xtc.myapplication2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("wq","onCreate");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("wq","onReceive = " + intent.getAction());

            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Android.intent.action.MASTER_CLEAR");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        this.registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("wq","onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.i("wq","onBackPressed");
        //手表先做动Activity画，然后回调onBackPressed，如果截断这个，Activity无法正常退栈，右滑退出这个Activity会变成透明
        super.onBackPressed();
    }
}
