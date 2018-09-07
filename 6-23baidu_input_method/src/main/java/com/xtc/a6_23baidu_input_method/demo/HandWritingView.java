
package com.xtc.a6_23baidu_input_method.demo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.baidu.input.Global;
import com.baidu.input.PlumCore;
import com.baidu.input.pub.CoreString;

import java.io.File;

public class HandWritingView extends View implements Runnable {
    private final static int POINT_MAX = 1024;

    private final static int MATCH_DELAY = 0x1FF;

    public Paint mPaint;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private Paint mBitmapPaint;

    public Handler runner;

    private boolean toClean;

    private int countPoint;

    private short[] points;

    // private int countPoint2;
    //
    // private int[] points2;

    private String candidateString;

    private Rect rDst;

    private Path mpath;

    public int range;

    private float scale;

    private byte pointGap;

    private int drawX;
    private int drawY;

    public int lastX;
    public int lastY;

    int endX = -1;
    int endY = -1;

    private int brush;

    private Rect drawRect;

    private long drawTime;

    private int hwMode = PlumCore.HW_INPUT_TYPE_HZ;

    int xAxis;
    int yAxis;

    private TextView resultView;

    // int resultCount;

    public HandWritingView(Context c) {
        super(c);

        initHandWritingCore(c, "/data/data/com.xtc.a6_23baidu_input_method/files/iptwt_20151202.bin");

        Resources res = getResources();
        Configuration config = res.getConfiguration();
        DisplayMetrics displayer = res.getDisplayMetrics();

        yAxis = displayer.widthPixels >> 1;
        xAxis = displayer.heightPixels >> 1;

        runner = new Handler();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xFFFF0000);
        // mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        scale = c.getResources().getDisplayMetrics().density;

        brush = (int) (8 * scale);
        mPaint.setStrokeWidth(brush);

        brush <<= 1;

        pointGap = (byte) (4 * scale);

        mBitmapPaint = new Paint();

        countPoint = 0;
        // countPoint2 = 0;

        points = new short[POINT_MAX << 1];
        // points2 = new int[POINT_MAX << 1];

        rDst = new Rect();

        drawRect = new Rect();

        mpath = new Path();

        drawTime = 0;

        this.setFocusable(true);
    }

    public static final int initHandWritingCore(Context con, String hwFile) {
        Log.d("lzk", "initHandWritingCore = " + hwFile);
        int ret = -1;
        if (Global.core != null) {
            synchronized (Global.core) {
                ret = Global.core.PlHandWritingRefresh(hwFile);
                Log.d("lzk", "initHandWritingCore = " + ret);
                if (ret == 0) // 安装成功
                {
                    setConfig4HandWritingCore(0);
                } else {
                    uninstallHandWritingFile(hwFile);
                }
            }
        }
        return ret;
    }

    public final static synchronized void uninstallHandWritingFile(String file) {
        if (Global.core != null) {
            synchronized (Global.core) {
                Global.core.PlHandWritingRefresh(null);
            }
        }
        File f = new File(file);
        f.delete();
    }

    public static final void setConfig4HandWritingCore(int range) {
        if (Global.core != null) {
            if (range == 0) {
                range = PlumCore.HW_FIND_RANGE_ALL;
            }
            int[] config = new int[7];
            config[0] = range; // 识别范围 由HWT_RECO_RANGE构成 默认为
            // HWT_RECO_RANGE_REGULAR
            config[1] = 18; // 最大值18,每个字一笔最多的笔画:默认18;在320以下的机器上用15
            synchronized (Global.core) {
                int ret = Global.core.PlHandWritingSetConfig(config);
                Log.d("lzk", "setConfig4HandWritingCore = " + ret);
            }
        }
    }

    public final void run() {
        if (toClean) {
            toClean = false;
            clean();
        }
    }

    public void findWordsFromKernel(boolean isLastCommit) {
        StringBuilder builder = new StringBuilder();
        if (Global.core != null) {
            synchronized (Global.core) {
                byte[] bytes = new byte[1];
                Global.core.PlFind(bytes, 0, PlumCore.CORE_HW, 0);
                int candnb = Global.core.PlCount(PlumCore.GETTYPE_CAND);
                CoreString[] hwWordShow;
                Log.v("lzk", "findWordsFromKernel candnb = " + candnb);
                if (candnb > 0) {
                    if (!isLastCommit) {
                        // 不是最终结果时最多取8个候选词
                        candnb = Math.min(candnb, 8);
                    }
                    hwWordShow = new CoreString[candnb];
                    for (int i = 0, j = 0; i < candnb; i++) {
                        hwWordShow[j] = new CoreString();
                        Global.core.PlGetStrByCandType(hwWordShow[j], i);

                        if (j < 10) {
                            builder.append(hwWordShow[j].value + '\n');
                            // Log.v("lzk", "findWordsFromKernel = " + hwWordShow[j].value);
                        }
                        j++;
                    }
                } else {
                    if (isLastCommit) {
                        // 防错，去除不会出字的情况
                        cleanHandWritingInfo();
                    }
                    hwWordShow = null;
                }
            }
        }
        // 使用hwWordShow更新界面
        if (resultView != null) {
            resultView.setText("通过menu选择不同的手写模式，手写模式：" + hwMode + '\n' + builder.toString());
        }
    }

    /**
     * 清除手写状态
     */
    public final void cleanHandWritingInfo() {
        if (Global.core != null) {
            synchronized (Global.core) {
                Global.core.PlQueryCmd(0, PlumCore.CMD_HW_CLEAN);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null) {

            mBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(-1);
        }
        // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mpath, mPaint);
    }

    public final boolean dispatchTouchEvent(MotionEvent event) {
        int _x = (int) event.getX();
        int _y = (int) event.getY();
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                toClean = false;
                this.drawLines(action, _x, _y);
                pushPoint(_x, _y);
                runner.removeCallbacks(this);
                break;
            case MotionEvent.ACTION_UP:
                pushPoint(_x, _y);
                this.drawLines(action, _x, _y);
                pushPoint(-1, 0);

                getWordsFromCore();

                endX = _x;
                endY = _y;
                toClean = true;
                runner.postDelayed(this, MATCH_DELAY);
                break;
            default:
                if (System.currentTimeMillis() - drawTime > 0xF) {
                    int absX = Math.abs(_x - lastX);
                    int absY = Math.abs(_y - lastY);
                    if (absX >= pointGap || absY >= pointGap) {
                        pushPoint(_x, _y);
                        drawLines(event.getAction(), _x, _y);
                    }

                }
                break;
        }
        return true;
    }

    public void setHWMode(int mode) {
        hwMode = mode;
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void getWordsFromCore() {
        points[(countPoint << 1) + 0] = -1;
        points[(countPoint << 1) + 1] = -1;

        int maxId = (countPoint << 1) + 1;
        short[] submitPoints = new short[maxId + 1];
        for (int i = 0; i < maxId + 1; i++) {
            submitPoints[i] = points[i];
        }

        countPoint++;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < submitPoints.length; i++) {
            sb.append(submitPoints[i]);
            sb.append(",");
        }
        // Log.v("lzk", "run = " + sb.toString() + "||" + hwMode);
        Global.core.PlHandWritingRecognizeAppand(submitPoints, hwMode);

        if ((hwMode & PlumCore.HW_INPUT_TYPE_HZ) != 0) {
            findWordsFromKernel(true);
        } else {
            findWordsFromKernel(false);
        }

        countPoint = 0;
    }

    private final void setRect(int x1, int y1, int x2, int y2) {
        if (x1 < x2) {
            this.rDst.left = x1;
            this.rDst.right = x2;
        } else {
            this.rDst.left = x2;
            this.rDst.right = x1;
        }

        if (y1 < y2) {
            this.rDst.top = y1;
            this.rDst.bottom = y2;
        } else {
            this.rDst.top = y2;
            this.rDst.bottom = y1;
        }
    }

    public final void drawLines(int action, int _x, int _y) {
        // System.out.println("drawLines :: "+action);
        // long a=System.currentTimeMillis();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mpath.reset();
                mpath.moveTo(_x, _y);
                mpath.quadTo(_x, _y, _x, _y);
                this.setRect(_x, _y, _x, _y);
                drawX = _x;
                drawY = _y;
                break;
            case MotionEvent.ACTION_UP:
                mpath.lineTo(_x, _y);
                this.setRect(_x, _y, drawX, drawY);
                drawRect.left = rDst.left - brush;
                drawRect.top = rDst.top - brush;
                drawRect.right = rDst.right + brush;
                drawRect.bottom = rDst.bottom + brush;
                mCanvas.clipRect(drawRect, Op.UNION);
                mCanvas.drawPath(mpath, mPaint);
                mCanvas.clipRect(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight(), Op.UNION);
                mpath.reset();
                break;
            default:
                int dx = (_x + lastX) >> 1;
                int dy = (_y + lastY) >> 1;
                mpath.quadTo(lastX, lastY, dx, dy);

                // mpath.lineTo(_x, _y);

                this.setRect(drawX, drawY, dx, dy);
                drawX = dx;
                drawY = dy;
                break;
        }

        this.lastX = _x;
        this.lastY = _y;

        drawRect.left = rDst.left - brush;
        drawRect.top = rDst.top - brush;
        drawRect.right = rDst.right + brush;
        drawRect.bottom = rDst.bottom + brush;
        // this.invalidate();
        // this.invalidate(drawRect);

        // System.out.println("test 2 :: "+(b-a));

        drawTime = System.currentTimeMillis();
        this.getParent().invalidateChild(this, drawRect);
        // invalidate(drawRect);
        // long b=System.currentTimeMillis();
        // System.out.println("test 2 :: "+(b-a));
    }

    private final void pushPoint(int _x, int _y) {
        // if (isSecond(_x, _y))
        // {
        // // Log.i("lzk", "fsfsf::"+_x +"||"+ _y);
        // if (countPoint2 + 1 < POINT_MAX)
        // {
        // // Log.i("lzk", "fsfsf::"+_x +"||"+ _y);
        // int os = countPoint2 << 1;
        // points2[os + 0] = _x;
        // points2[os + 1] = _y;
        // countPoint2++;
        // }
        // }
        // else
        {
            if (countPoint + 1 < POINT_MAX) {
                int os = countPoint << 1;
                points[os + 0] = (short) _x;
                points[os + 1] = (short) _y;
                countPoint++;
            }
        }

    }

    // private boolean isSecond(int x, int y)
    // {
    // if (countPoint == 0)
    // {
    // return false;
    // }
    //
    // boolean fourthQuadrant = endX > yAxis && endY < xAxis;
    // // boolean firstOrThirdQuadrant = (lastX > yAxis && lastY > xAxis) || (lastX < yAxis && lastY < xAxis);
    // boolean secondQuadrant = x < yAxis && y > xAxis;
    //
    // endX = -1;
    // endY = -1;
    // // if(x > yAxis)
    // // {
    // // Log.d("lzk", "gyghio::" + lastX + "||" + lastY + "_" + x + "||" + y);
    // // }
    // // else
    // // {
    // // Log.i("lzk", "gyghio::" + lastX + "||" + lastY + "_" + x + "||" + y);
    // // }
    // return secondQuadrant && (fourthQuadrant);
    // }

    public final void clean() {
        mCanvas.drawColor(-1);
        if (candidateString != null) {
            mBitmapPaint.setColor(0xFF000000);
            mBitmapPaint.setTextSize(24 * scale);
            mCanvas.drawText(candidateString, 0, 32, mBitmapPaint);
        }
        // mBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        // mCanvas = new Canvas(mBitmap);
        cleanHandWritingInfo();
        this.postInvalidate();
    }

    public void setResultView(TextView result) {
        resultView = result;
    }

}