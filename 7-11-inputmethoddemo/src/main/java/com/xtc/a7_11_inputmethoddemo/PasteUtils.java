package com.xtc.a7_11_inputmethoddemo;

import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by wuqi on 2018/8/16.
 */
public class PasteUtils {

    public static void setInsertionDisabled(EditText editText) {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(editText);
            Class editorClass = Class.forName("android.widget.Editor");
            // if this view supports selection handles
            Field mSelectionControllerEnabledField = editorClass.getDeclaredField("mSelectionControllerEnabled");
            mSelectionControllerEnabledField.setAccessible(true);
            mSelectionControllerEnabledField.set(editorObject, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
