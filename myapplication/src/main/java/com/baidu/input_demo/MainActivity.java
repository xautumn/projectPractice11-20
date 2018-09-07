package com.baidu.input_demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.input.Global;
import com.baidu.input.ImeCore;


public class MainActivity extends Activity {

    HandWritingView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化,三个必须都要初始化
        Global.loadResources(this);
        Global.installFile(this);
        initCore();
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(Color.BLACK);
        view = new HandWritingView(this);
        frameLayout.addView(view);

        TextView result = new TextView(this);

        view.setResultView(result);

        frameLayout.addView(result);
        setContentView(frameLayout);
    }

    public void initCore() {
        Global.core = new ImeCore();
        if (Global.core != null) {
            Global.core.init(this);
        }
    }
}
