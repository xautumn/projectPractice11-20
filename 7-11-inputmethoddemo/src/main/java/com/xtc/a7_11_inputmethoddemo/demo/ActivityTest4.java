package com.xtc.a7_11_inputmethoddemo.demo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.xtc.a7_11_inputmethoddemo.R;

public class ActivityTest4 extends Activity {
    private EditText editText;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        editText = findViewById(R.id.et_test4);
        tvResult = findViewById(R.id.tv_result4);

        editText.setInputType(111);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    Log.i("wq test4","enter result = " + editText.getText().toString());
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
        Log.i("wq test4", "show input");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
