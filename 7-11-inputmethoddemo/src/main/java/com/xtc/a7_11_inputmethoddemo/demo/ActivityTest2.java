package com.xtc.a7_11_inputmethoddemo.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.xtc.a7_11_inputmethoddemo.R;

import java.util.List;
import java.util.Timer;

public class ActivityTest2 extends Activity {

    private EditText editText;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        editText = findViewById(R.id.et_test2);
        tvResult = findViewById(R.id.tv_result2);
        editText.setInputType(110);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (i == KeyEvent.KEYCODE_ENTER) {
                    Log.i("wq test2","enter result = " + editText.getText().toString());
                    tvResult.setText(editText.getText().toString());
                }
                return false;


            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("wq","beforeTextChanged = " + charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("wq","onTextChanged = " + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("wq","afterTextChanged = " + editText.getText().toString());
                tvResult.setText(editText.getText().toString());
            }
        });
    }

    public void test(View v) {
        Log.i("wq test2", "show input");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
