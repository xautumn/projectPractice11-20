package com.autumn.a12_11testdeletedir;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherEntity weatherEntity = new WeatherEntity();
        //getResources().getDrawable()
        weatherEntity.setAirQuality(null);
        Log.i("wq","weatherEntity = "+weatherEntity.getAirQuality());

        //deleteSourceResource("blueflower");
    }

    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + "/xtc/dial/";
    public static final String DIAL_DEX_PATH = "/data/user/0/com.xtc.i3launcher/files/dial/";


    public static void deleteSourceResource(String sourceName) {
        File apkFile = new File(BASE_PATH + sourceName + "/"+"");
        Log.i("MainActivity","apkFile = " + apkFile);
        if (apkFile.exists()) {
            Log.i("MainActivity","apkFile = exist" );
        }
        if (apkFile.isDirectory()) {
            File[] list = apkFile.listFiles();
            Log.i("MainActivity","list = " + list);
            for (int i = 0; i < list.length; i++) {
                deleteMyFile(list[i].getAbsolutePath());
            }
        }
        deleteMyFile(DIAL_DEX_PATH + sourceName + ".cl");
    }


    private static void deleteMyFile(String path) {
        Log.i("MainActivity","deleteFile = "+path);
        File file = new File(path);
        if (file.exists()) {
            boolean delete = file.delete();
            Log.i("MainActivity","delete = " + delete);
        }
    }
}
