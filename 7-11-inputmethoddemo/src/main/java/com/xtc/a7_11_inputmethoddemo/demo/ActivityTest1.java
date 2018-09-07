package com.xtc.a7_11_inputmethoddemo.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.xtc.a7_11_inputmethoddemo.PasteUtils;
import com.xtc.a7_11_inputmethoddemo.R;

public class ActivityTest1 extends Activity {
    private EditText editText;

    //删除、setOnKeyListener
    //回车 setOnKeyListener   setOnEditorActionListener
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        editText = findViewById(R.id.et);
        Log.i("wq", "ActivityTest1");

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // setInsertionDisabled when user touches the view
                    PasteUtils.setInsertionDisabled(editText);
                }
                return false;
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Log.i("wq", "setOnKeyListener = " + keyCode);
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ActivityTest1.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //用户主动点击，还是息屏导致关闭的
                    Log.i("wq", "input method hide");
                }
                return false;
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                Log.i("wq","setOnEditorActionListener");
                doWhichOperation(actionId);
                Log.e("wq", "v.getImeActionId(): " + textView.getImeActionId());
                Log.e("wq", "v.getImeOptions(): " + textView.getImeOptions());
                Log.e("wq", "----------------------------------------------");
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
            }
        });
    }

    public void test(View view) {

    }

    private void doWhichOperation(int actionId) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
                Log.e("wq", "IME_ACTION_DONE");
                break;
            case EditorInfo.IME_ACTION_GO:
                Log.e("wq", "IME_ACTION_GO");
                break;
            case EditorInfo.IME_ACTION_NEXT:
                Log.e("wq", "IME_ACTION_NEXT");
                break;
            case EditorInfo.IME_ACTION_NONE:
                Log.e("wq", "IME_ACTION_NONE");
                break;
            case EditorInfo.IME_ACTION_PREVIOUS:
                Log.e("wq", "IME_ACTION_PREVIOUS");
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                Log.e("wq", "IME_ACTION_SEARCH");
                break;
            case EditorInfo.IME_ACTION_SEND:
                Log.e("wq", "IME_ACTION_SEND");
                break;
            case EditorInfo.IME_ACTION_UNSPECIFIED:
                Log.e("wq", "IME_ACTION_UNSPECIFIED");
                break;
            default:
                break;
        }
    }
}
