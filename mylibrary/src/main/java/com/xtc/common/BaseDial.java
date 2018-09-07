package com.xtc.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class BaseDial extends RelativeLayout {

    public BaseDial(Context context) {
        this(context,null);
    }

    public BaseDial(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseDial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * host获取当前插件包的所有方法
     * @return
     */
    public abstract String[] getMethodString();

    /**
     * 当前view是否处于用户可见
     *
     * @param isShow
     */
    public abstract void setShow(boolean isShow);


    /**
     * 息屏处理
     */
    public void dealScreenOff() {

    }

    /**
     * 亮屏处理
     */
    public void dealScreenOn() {

    }

    /**
     * 天气预警在表盘中的位置
     *
     * @return
     */
    public int getWeatherAlarmType() {
        return 0;
    }


    /**
     * 更新时间
     */
    public abstract void updateTime();

    /**
     * 更新计步
     *
     * @param count
     */
    public void updateStep(int count) {

    }

    /**
     * 更新电量
     *
     * @param battery
     */
    public void updateBattery(int battery) {

    }

    /**
     * 更新等级
     *
     * @param level
     */
    public void updateLevel(int level) {

    }

    /**
     * 更新积分
     *
     * @param score
     */
    public void updateScore(int score) {

    }

    /**
     * 处理天气变化
     *
     * @param result String json
     */
    public void dealUpdateWeather(String result) {

    }

}
