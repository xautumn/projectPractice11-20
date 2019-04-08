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
public class BumblebeeBatteryLeftStepView extends View {

    private final String TAG = "BumblebeeStoreBatteryView";
    private static String path;
    private Context context;
    private Resources resources;
    private Bitmap leftBitmap;
    private Drawable rightDrawable;
    private Paint maskPaint;
    private int currentstepNumber;
    private int pathParam;

    public BumblebeeBatteryLeftStepView(Context context) {
        this(context, null);
    }

    public BumblebeeBatteryLeftStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BumblebeeBatteryLeftStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.resources = initResources(context);
        initView();
    }

    private void initView() {
        leftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_step_mask);
        maskPaint = new Paint();
        maskPaint.setAntiAlias(true);
        mPath = new Path();
    }

    public void setStepNumber(int number) {
        Log.i("wq", "setstepNumber = " + number);
        currentstepNumber = number;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMask(canvas, currentstepNumber, leftBitmap);
    }

    int maxW = 320;
    int maxH = 360;
    private Path mPath;

    private void drawMask(Canvas canvas, int stepNumber, Bitmap bitmap) {
        canvas.save();
        mPath.reset();
        if (stepNumber >= 7200 && stepNumber < 8000) {
            setPathParam(9);
        } else if (stepNumber >= 6400 && stepNumber < 7200) {
            setPathParam(8);
        } else if (stepNumber >= 5600 && stepNumber < 6400) {
            setPathParam(7);
        } else if (stepNumber >= 4800 && stepNumber < 5600) {
            setPathParam(6);
        } else if (stepNumber >= 4000 && stepNumber < 4800) {
            setPathParam(5);
        } else if (stepNumber >= 3200 && stepNumber < 4000) {
            setPathParam(4);
        } else if (stepNumber >= 2400 && stepNumber < 3200) {
            setPathParam(3);
        } else if (stepNumber >= 1600 && stepNumber < 2400) {
            setPathParam(2);
        } else if (stepNumber >= 800 && stepNumber < 1600) {
            setPathParam(1);
        } else if (stepNumber >= 0 && stepNumber < 800) {
            mPath.lineTo(0, 135);
            mPath.lineTo(0, 0);
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
        Log.i("wq", "flag = " + flag);
        mPath.moveTo(0, 135);
        mPath.lineTo(16, 135);
        mPath.lineTo(16, (int)(135 - (8.5 * flag + 5 * (flag - 1))));
        mPath.lineTo(0, (int)(135 - (8.5 * flag + 5 * (flag - 1)) -9));
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
        BumblebeeBatteryLeftStepView.path = path;
    }


}
