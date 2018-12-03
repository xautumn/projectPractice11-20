package com.xtc.a7_11_inputmethoddemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest1;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest2;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest3;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest4;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest5;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest6;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest7;
import com.xtc.a7_11_inputmethoddemo.demo.ActivityTest8;

/**
 * 输入法demo
 */
public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

    }

    private void initView() {

    }

    public void test(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(this,ActivityTest1.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(this,ActivityTest2.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(this,ActivityTest3.class));
                break;
            case R.id.btn_4:
                startActivity(new Intent(this,ActivityTest4.class));
                break;
            case R.id.btn_5:
                startActivity(new Intent(this,ActivityTest5.class));
                break;
            case R.id.btn_6:
                startActivity(new Intent(this,ActivityTest6.class));
                break;
            case R.id.btn_7:
                startActivity(new Intent(this,ActivityTest7.class));
                break;
            case R.id.btn_8:
                startActivity(new Intent(this,ActivityTest8.class));
                break;
            default:
                break;
        }
    }


}
