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
public class BumblebeeBatteryRightBatteryView extends View {

    private final String TAG = "BumblebeeStoreBatteryView";
    private static String path;
    private Context context;
    private Resources resources;
    private Bitmap leftBitmap;
    private Drawable rightDrawable;
    private Paint maskPaint;
    private int currentBatteryNumber;
    private int pathParam;

    public BumblebeeBatteryRightBatteryView(Context context) {
        this(context, null);
    }

    public BumblebeeBatteryRightBatteryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BumblebeeBatteryRightBatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.resources = initResources(context);
        initView();
    }

    private void initView() {
        leftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_battery_mask);
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
    
    private Path mPath;

    private void drawMask(Canvas canvas, int batteryNumber, Bitmap bitmap) {
        canvas.save();
        mPath.reset();
        if (batteryNumber >= 90 && batteryNumber < 100) {
            setPathParam(9);
        } else if (batteryNumber >= 80 && batteryNumber < 90) {
            setPathParam(8);
        } else if (batteryNumber >= 70 && batteryNumber < 80) {
            setPathParam(7);
        } else if (batteryNumber >= 60 && batteryNumber < 70) {
            setPathParam(6);
        } else if (batteryNumber >= 50 && batteryNumber < 60) {
            setPathParam(5);
        } else if (batteryNumber >= 40 && batteryNumber < 50) {
            setPathParam(4);
        } else if (batteryNumber >= 30 && batteryNumber < 40) {
            setPathParam(3);
        } else if (batteryNumber >= 20 && batteryNumber < 30) {
            setPathParam(2);
        } else if (batteryNumber >= 10 && batteryNumber < 20) {
            setPathParam(1);
        } else if (batteryNumber >= 0 && batteryNumber < 10) {
            mPath.moveTo(0, 135);
            mPath.lineTo(16, 135);
            mPath.lineTo(16, (int)(135 - (8 * 0.5 +9)));
            mPath.lineTo(0, (int)(135 - (8 * 0.5)));
            mPath.lineTo(0, 135);
        } else {
            mPath.lineTo(0, 135);
            mPath.lineTo(16, 135);
            mPath.lineTo(16, 0);
            mPath.lineTo(0, 0);
        }
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap, 0, 0, maskPaint);
        canvas.restore();
    }

    public void setPathParam(int flag) {
        mPath.moveTo(0, 135);
        mPath.lineTo(16, 135);
        mPath.lineTo(16, (int)(135 - (8.5 * flag + 5 * (flag - 1)) -9));
        mPath.lineTo(0, (int)(135 - (8.5 * flag + 5 * (flag - 1))));
        mPath.lineTo(0, 135);
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
        BumblebeeBatteryRightBatteryView.path = path;
    }


}
