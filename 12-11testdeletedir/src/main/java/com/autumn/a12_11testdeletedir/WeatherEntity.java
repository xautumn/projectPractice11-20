package com.autumn.a12_11testdeletedir;

import android.os.Parcel;

public class WeatherEntity {

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 当前温度
     */
    private String temperature;

    /**
     * 预报天气代码
     */
    private String code;

    /**
     * 预报最低温度
     */
    private String low;

    /**
     * 预报最高温度
     */
    private String high;

    /**
     * 预报时间
     */
    private String date;

    /**
     * 空气质量
     */
    private String airQuality;

    /**
     * 穿衣指数
     */
    private String dressIndex;

    /**
     * 紫外线指数
     */
    private String ultravioletRadiation;

    /**
     * 感冒指数
     */
    private String coldIndex;

    /**
     * 运动指数
     */
    private String exerciseIndex;

    public WeatherEntity() {

    }

    public WeatherEntity(Parcel source) {
        cityName = source.readString();
        temperature = source.readString();
        code = source.readString();
        low = source.readString();
        high = source.readString();
        date = source.readString();
        airQuality = source.readString();
        dressIndex = source.readString();
        ultravioletRadiation = source.readString();
        coldIndex = source.readString();
        exerciseIndex = source.readString();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getDressIndex() {
        return dressIndex;
    }

    public void setDressIndex(String dressIndex) {
        this.dressIndex = dressIndex;
    }

    public String getUltravioletRadiation() {
        return ultravioletRadiation;
    }

    public void setUltravioletRadiation(String ultravioletRadiation) {
        this.ultravioletRadiation = ultravioletRadiation;
    }

    public String getColdIndex() {
        return coldIndex;
    }

    public void setColdIndex(String coldIndex) {
        this.coldIndex = coldIndex;
    }

    public String getExerciseIndex() {
        return exerciseIndex;
    }

    public void setExerciseIndex(String exerciseIndex) {
        this.exerciseIndex = exerciseIndex;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "cityName='" + cityName + '\'' +
                ", temperature='" + temperature + '\'' +
                ", code='" + code + '\'' +
                ", low='" + low + '\'' +
                ", high='" + high + '\'' +
                ", date='" + date + '\'' +
                ", airQuality='" + airQuality + '\'' +
                ", dressIndex='" + dressIndex + '\'' +
                ", ultravioletRadiation='" + ultravioletRadiation + '\'' +
                ", coldIndex='" + coldIndex + '\'' +
                ", exerciseIndex='" + exerciseIndex + '\'' +
                '}';
    }
}
