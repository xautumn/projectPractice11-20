package com.xtc.a9_18glidepalettetest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;


import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testColorMatrix();

        test();

        String s = Integer.toString(0xffff0000);
        Log.i("wq","转成10进制 = " + s);

    }

    private void testColorMatrix() {
        ColorMatrix cMatrix = new ColorMatrix();
        // 设置饱和度
        //cMatrix.setSaturation((float) (progress / 100.0));
    }

    private void test() {
        /**
         * Palette.VIBRANT
         Palette.VIBRANT_DARK
         Palette.VIBRANT_LIGHT
         Palette.MUTED
         Palette.MUTED_DARK
         Palette.MUTED_LIGHT
         */

        Palette palette =
                Palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.hh))
                        .generate();

        Palette.Swatch swatch = palette.getVibrantSwatch();
        Palette.Swatch dominantSwatch = palette.getDominantSwatch();
        if (swatch != null) {
            dealSwatch(swatch);
            dealSwatch(dominantSwatch);
        } else {
            Log.i("wq","swatch is null " );
        }

    }

    private void dealSwatch(Palette.Swatch swatch) {
        int rgb = swatch.getRgb();
        int mRed = Color.red(rgb);
        int mGreen = Color.green(rgb);
        int mBlue = Color.blue(rgb);
        int rgb1 = Color.rgb(224, 0, 0);
        Log.i("wq","getcolor = " + rgb1);
        Log.i("wq", "rgb = " + rgb);
        Log.i("wq", "mRed = " + mRed);
        Log.i("wq", "mGreen = " + mGreen);
        Log.i("wq", "mBlue = " + mBlue);
    }

/*    public Drawable getHexagonAppIcon(String packageName, Boolean isOpen) {
        LogUtil.i("getHexagonAppIcon = " + roundedRectangleFrameWidth + "--roundedRectangleFrameHeight = " + roundedRectangleFrameHeight);
        Drawable drawable = getAppIcon(packageName);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(roundedRectangleFrameWidth, roundedRectangleFrameHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        float centerX = width / 2;
        float centerY = height / 2;
        float radius = width / 2;
        double radian30 = 30 * Math.PI / 180;
        float a = (float) (radius * Math.sin(radian30));
        float b = (float) (radius * Math.cos(radian30));
        Path localPath = new Path();
        localPath.moveTo(roundedRectangleFrameWidth/4, 0);
        localPath.lineTo(roundedRectangleFrameWidth * 3/4, 0);
        localPath.lineTo(roundedRectangleFrameWidth , roundedRectangleFrameHeight/4);
        localPath.lineTo(roundedRectangleFrameWidth , roundedRectangleFrameHeight* 3/4);
        localPath.lineTo(roundedRectangleFrameWidth * 3/4 , roundedRectangleFrameHeight);
        localPath.lineTo(roundedRectangleFrameWidth /4 , roundedRectangleFrameHeight);
        localPath.lineTo(0 , roundedRectangleFrameHeight* 3/4);
        localPath.lineTo(0 , roundedRectangleFrameHeight/4);
        localPath.moveTo(roundedRectangleFrameWidth/4, 0);

        localPath.close();
        canvas.drawPath(localPath, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);

        return new BitmapDrawable(mContext.getResources(), output);
    }*/
}
