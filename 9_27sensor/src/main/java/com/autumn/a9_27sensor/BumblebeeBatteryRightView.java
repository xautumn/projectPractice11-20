package com.autumn.a9_27sensor;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by wuqi on 2018/12/12.
 */
public class BumblebeeBatteryRightView extends View {

    private final String TAG = "BumblebeeStoreBatteryView";
    private static String path;
    private Context context;
    private Resources resources;
    private Bitmap leftBitmap;
    private Drawable rightDrawable;
    private Paint maskPaint;
    private int currentBatteryNumber;
    private int pathParam;

    public BumblebeeBatteryRightView(Context context) {
        this(context, null);
    }

    public BumblebeeBatteryRightView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BumblebeeBatteryRightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.resources = initResources(context);
        initView();
    }

    private void initView() {
        leftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_battery_right);
        maskPaint = new Paint();
        maskPaint.setAntiAlias(true);
        mPath = new Path();
    }

    public void setBatteryNumber(int number) {
        Log.i("wq","setBatteryNumber = " + number);
        currentBatteryNumber = number;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMask(canvas, currentBatteryNumber, leftBitmap);
    }

    int maxW = 320;
    int maxH = 360;
    private Path mPath;

    private void drawMask(Canvas canvas, int batteryNumber, Bitmap bitmap) {
        canvas.save();
        mPath.reset();
        if (batteryNumber >= 81 && batteryNumber <= 85) {
            setPathParam(17);
        } else if (batteryNumber >= 76 && batteryNumber <= 80) {
            setPathParam(16);
        } else if (batteryNumber >= 71 && batteryNumber <= 75) {
            setPathParam(15);
        } else if (batteryNumber >= 66 && batteryNumber <= 70) {
            setPathParam(14);
        } else if (batteryNumber >= 61 && batteryNumber <= 65) {
            setPathParam(13);
        } else if (batteryNumber >= 56 && batteryNumber <= 60) {
            setPathParam(12);
        } else if (batteryNumber >= 51 && batteryNumber <= 55) {
            setPathParam(11);
        } else if (batteryNumber >= 46 && batteryNumber <= 50) {
            setPathParam(10);
        } else if (batteryNumber >= 41 && batteryNumber <= 45) {
            setPathParam(9);
        } else if (batteryNumber >= 36 && batteryNumber <= 40) {
            setPathParam(8);
        } else if (batteryNumber >= 31 && batteryNumber <= 35) {
            setPathParam(7);
        } else if (batteryNumber >= 26 && batteryNumber <= 30) {
            setPathParam(6);
        } else if (batteryNumber >= 21 && batteryNumber <= 25) {
            setPathParam(5);
        } else if (batteryNumber >= 16 && batteryNumber <= 20) {
            setPathParam(4);
        } else if (batteryNumber >= 11 && batteryNumber <= 15) {
            setPathParam(3);
        } else if (batteryNumber >= 6 && batteryNumber <= 10) {
            setPathParam(2);
        } else if (batteryNumber >= 0 && batteryNumber <= 5) {
            setPathParam(1);
        } else {
            mPath.lineTo(0, 264);
            mPath.lineTo(16, 264);
            mPath.lineTo(16, 0);
            mPath.lineTo(0, 0);
        }
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap, 0, 0, maskPaint);
        canvas.restore();
    }

    public void setPathParam(int flag) {
        mPath.moveTo(0, 264);
        mPath.lineTo(16, 264);
        mPath.lineTo(16, 264 - (int)(8.5 * flag + 6 * (flag - 1)) - 13);
        mPath.lineTo(0, 264 - (int)(8.5 * flag + 6 * (flag - 1)));
        mPath.lineTo(0, 264);
    }

    @Override
    public Resources getResources() {
        return resources == null ? super.getResources() : resources;
    }

    private Resources initResources(Context context) {
        try {
            AssetManager e = AssetManager.class.newInstance();
            Method addAssetPath = e.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(e, path);
            Resources superRes = context.getResources();
            return new Resources(e, superRes.getDisplayMetrics(), superRes.getConfiguration());
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return null;
    }

    public static void setPath(String path) {
        BumblebeeBatteryRightView.path = path;
    }


}
