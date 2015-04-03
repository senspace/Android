package com.example.notefan.myapplication.app;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.example.notefan.myapplication.bean.City;
import com.example.notefan.myapplication.db.CityDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by notefan on 15/4/2.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyAPP";

    private CityDB mCityDB;
    private ArrayList<City> mCityList;

    private static Application mApplication;
    @Override
    public void onCreate(){
        super.onCreate();

        Log.d(TAG, "MyApplication->Oncreate");
        mApplication = this;

        mCityDB = openCityDB();
        initCityList();
    }

    public static Application getInstance(){
        return mApplication;
    }


    private CityDB openCityDB() {
        String path = "/data"
                + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + getPackageName()
                + File.separator + "databases"
                + File.separator
                + CityDB.CITY_DB_NAME;
        File db = new File(path);
        Log.d(TAG, path);
        if (!db.exists()) {
            Log.i("MyApp", "db is not exists");
            try {
                InputStream is = getAssets().open("city.db");
                FileOutputStream fos = new FileOutputStream(db);
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return new CityDB(this, path);
    }

    private void initCityList(){
        mCityList = new ArrayList<City>();
        new Thread (new Runnable() {
            @Override
            public void run() {
                //TODO Auto-genetated method stub
                prepareCityList();
            }
        }).start();
    }

    private boolean prepareCityList() {
        mCityList = mCityDB.getAllCity();
        for (City city : mCityList) {
            String cityName = city.getCity();
            Log.d(TAG, cityName);
        }
        return true;
    }

}
