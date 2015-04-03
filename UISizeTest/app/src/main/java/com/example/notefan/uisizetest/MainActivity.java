package com.example.notefan.uisizetest;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        float density = getResources().getDisplayMetrics().density;
        float densityDpi = getResources().getDisplayMetrics().densityDpi;
        float heightPixels = getResources().getDisplayMetrics().heightPixels;
        float widthPixels = getResources().getDisplayMetrics().widthPixels;

        Log.d("MainActivity", "xdpi is " + xdpi);
        Log.d("MainActivity", "ydpi is " + ydpi);
        Log.d("MainActivity", "density is " + density);            // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        Log.d("MainActivity", "densityDpi is " + densityDpi);
        Log.d("MainActivity", "heightPixels is " + heightPixels);
        Log.d("MainActivity", "widthPixels is " + widthPixels);

            double x = Math.pow(heightPixels / xdpi, 2);
            double y = Math.pow(widthPixels  / ydpi, 2);
            double screenInches = Math.sqrt(x + y);
            Log.d("MainActivity", "Screen inches : " + screenInches);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
