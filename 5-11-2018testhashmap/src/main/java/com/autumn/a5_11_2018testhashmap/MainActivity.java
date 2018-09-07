package com.autumn.a5_11_2018testhashmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = findViewById(R.id.tv);
        viewById.setText(null);
        Object object = null;
        Map<String, Object> data = new HashMap<>();
        Object o = data.get("we");
        Log.i("wq","o = " + o);
        Integer i = (Integer) data.get("we");
        Log.i("wq","i = " + i);
        //强转就会报
        //java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.Integer.intValue()' on a null object reference
        //int i1 = (int) data.get("we");
    }
}
