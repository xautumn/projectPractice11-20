package com.xtc.common;

public interface DialInterface {

    int RIGHT_BOTTOM = 0;
    int RIGHT_MEDIUM = 1;

    public boolean test();
    /**
     * 当前表盘是否需要天气数据
     *
     * @return
     */
    public boolean getNeedWeather();

    /**
     * 是否需要步数信息
     * @return
     */
    public boolean getNeedStep();

    /**
     * 是否需要电量信息
     * @return
     */
    public boolean getNeedBattery();

    public boolean getNeedLevel();

    public boolean getNeedScore();

    /**
     * 天气预警在表盘中的位置
     *
     * @return
     */
    public int getWeatherAlarmType();


    /**
     * 更新时间
     */
    public void updateTime();

    /**
     * 更新计步
     *
     * @param count
     */
    public void updateStep(int count);

    /**
     * 更新电量
     *
     * @param battery
     */
    public void updateBattery(int battery);

    /**
     * 更新等级
     *
     * @param level
     */
    public void updateLevel(int level);

    /**
     * 更新积分
     *
     * @param score
     */
    public void updateScore(int score);

    /**
     * 处理天气变化
     *
     * @param result String json
     */
    public void dealUpdateWeather(String result);

    /**
     * 息屏处理
     */
    public void dealScreenOff();

    /**
     * 亮屏处理
     */
    public void dealScreenOn();

    /**
     * 当前view是否在手表上处于可见
     *
     * @param isShow
     */
    public void setShow(boolean isShow);

    /**
     * 预留接口
     */
    public void setOther();

    /**
     * 预留接口
     * @return
     */
    public Object getOther();
}
