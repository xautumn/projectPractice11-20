package com.xtc.a9_18glidepalettetest;

import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SVGTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svgtest);
        ImageView viewById = findViewById(R.id.iv_test);
        viewById.setImageResource(R.drawable.hh);
       /* VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.test_01, getTheme());
        vectorDrawableCompat.setTint(getResources().getColor(R.color.colorPrimary));
        viewById.setImageDrawable(vectorDrawableCompat);*/
    }
}
