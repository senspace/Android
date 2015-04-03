package com.example.notefan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notefan.myapplication.bean.TodayWeather;
import com.example.notefan.myapplication.util.NetUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.zip.GZIPInputStream;


public class MainActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "MyApp";;
    private ImageView mUpdateBtn;
    public TodayWeather todayWeather;
    private ImageView mCitySelect;

    private static final int UPDATE_TODAY_WEATHER = 1;

    //定义控件对象
    private TextView cityTv, timeTv, humidityTv, weekTv, pmDataTv, pmQualityTv,
            temperatureTv, climateTv, windTv;
    private ImageView weatherImg, pmImg;

    //初始化控件
    void initView(){
        cityTv = (TextView) findViewById(R.id.city);
        timeTv = (TextView) findViewById(R.id.time);
        humidityTv = (TextView) findViewById(R.id.humidity);
        weekTv = (TextView) findViewById(R.id.week);
        pmDataTv = (TextView) findViewById(R.id.na_big);
        pmQualityTv = (TextView) findViewById(R.id.na_small);
        temperatureTv = (TextView) findViewById(R.id.temperature);
        climateTv = (TextView) findViewById(R.id.climate);
        windTv = (TextView) findViewById(R.id.wind);
        weatherImg = (ImageView) findViewById(R.id.weather_picture);

        cityTv.setText("N/A");
        timeTv.setText("N/A");
        humidityTv.setText("N/A");
        weekTv.setText("N/A");
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        temperatureTv.setText("N/A");
        climateTv.setText("N/A");
        windTv.setText("N/A");
//        weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);
    }

    void updateTodayWeather(TodayWeather todayWeather){
        Log.d("myapp3", todayWeather.toString());
        cityTv.setText(todayWeather.getCity());
        timeTv.setText(todayWeather.getUpdatetime()+"发布");
        humidityTv.setText("湿度："+todayWeather.getShidu());
        pmDataTv.setText(todayWeather.getPm25());
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getData());
        temperatureTv.setText(todayWeather.getLow()+"~"+todayWeather.getHigh());
        climateTv.setText(todayWeather.getType());
        switch (todayWeather.getType())
        {
            case "晴":
//                weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);      //覆盖
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_qing));
                break;
            case "暴雪":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_baoxue));
                break;
            case "暴雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_baoyu));
                break;
            case "大暴雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_dabaoyu));
                break;
            case "大雪":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_daxue));
                break;
            case "大雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_dayu));
                break;
            case "多云":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_duoyun));
                break;
            case "大于300":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_greater_300));
                break;
            case "雷阵雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_leizhenyu));
                break;
            case "雷阵雨冰雹":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_leizhenyubingbao));
                break;
            case "沙尘暴":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_shachenbao));
                break;
            case "shenzhen_bg":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_shenzhen_bg));
                break;
            case "特大暴雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_tedabaoyu));
                break;
            case "雾":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_wu));
                break;
            case "小雪":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_xiaoxue));
                break;
            case "阴":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_yin));
                break;
            case "雨夹雪":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_yujiaxue));
                break;
            case "阵雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_zhenxue));
                break;
            case "中雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_zhongyu));
                break;
            case "小雨":
                weatherImg.setImageDrawable(getResources().getDrawable(R.drawable.biz_plugin_weather_xiaoyu));
                break;
            default:
                break;
        }
        windTv.setText("风力："+todayWeather.getFengli());
        Toast.makeText(MainActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case UPDATE_TODAY_WEATHER:
                    updateTodayWeather((TodayWeather)msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"MainActivity->Oncreate");

        requestWindowFeature(Window.FEATURE_NO_TITLE);      /* 隐藏标题栏 */
        setContentView(R.layout.weather_info);              /* 调用weather_info.xml */
        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        mUpdateBtn.setOnClickListener(this);
        mCitySelect = (ImageView) findViewById(R.id.title_city_manager);
        mCitySelect.setOnClickListener(this);

        initView();
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

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.title_update_btn){
            SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            String cityCode = sharedPreferences.getString("main_city_code","101010100");
            Log.d("myweather",cityCode);
            if (NetUtil.getNetworkState(this) != NetUtil.NETWORK_NONE){
                Log.d("myweather","网络ok");
                queryWeatherCode(cityCode);
            }else
            {
                Log.d("myweather","网络挂了");
                Toast.makeText(MainActivity.this,"网络挂了！",Toast.LENGTH_LONG).show();
            }
        }

        if(view.getId() == R.id.title_city_manager){
            Intent i = new Intent(this, SelectCity.class);
            startActivity(i);
        }
    }

    /**
     * 根据城市编号查询所对应的天气信息
     * @param cityCode
     */
    private void queryWeatherCode(String cityCode){

        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("myweather",address);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(address);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity entity = httpResponse.getEntity();

                        InputStream responseStream = entity.getContent();
                        responseStream = new GZIPInputStream(responseStream);

                        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
                        StringBuilder response = new StringBuilder();
                        String  str;
                        while((str=reader.readLine()) != null){
                            response.append(str);
                        }
                        String responseStr = response.toString();
                        Log.d("myweather", responseStr);
                        todayWeather= praseXML(responseStr);
                        if(todayWeather != null){
                            //Log.d("myapp2", todayWeather.toString());
                            //发送消息，由主线程更新UI
                            Message msg = new Message();
                            msg.what = UPDATE_TODAY_WEATHER;
                            msg.obj = todayWeather;
                            mHandler.sendMessage(msg);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //xml解析函数
    private TodayWeather praseXML(String xmldata){
        TodayWeather todayWeather = null;
        try{
            int fengxiangCount=0;
            int fengliCount=0;
            int dateCount=0;
            int highCount=0;
            int lowCount=0;
            int typeCount=0;

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmldata));
            int eventType = xmlPullParser.getEventType();
            Log.d("myapp2","praseXML");
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    //判断当前事件是否为文档开始事件
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    //判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:

                        if(xmlPullParser.getName().equals("resp")){
                            eventType = xmlPullParser.next();
                            todayWeather = new TodayWeather();
                        }
                        if(todayWeather != null){
                            if(xmlPullParser.getName().equals("city")){
                                eventType = xmlPullParser.next();
                                todayWeather.setCity(xmlPullParser.getText());
                                //Log.d("myapp2","city: "+xmlPullParser.getText());
                            }
                            else if(xmlPullParser.getName().equals("updatetime")){
                                eventType = xmlPullParser.next();
                                todayWeather.setUpdatetime(xmlPullParser.getText());
                                //Log.d("myapp2","updatetime: "+xmlPullParser.getText());
                            }
                            else if(xmlPullParser.getName().equals("shidu")){
                                eventType = xmlPullParser.next();
                                todayWeather.setShidu(xmlPullParser.getText());
                                //Log.d("myapp2","shidu: "+xmlPullParser.getText());
                            }
                            else if(xmlPullParser.getName().equals("wendu")){
                                eventType = xmlPullParser.next();
                                todayWeather.setWendu(xmlPullParser.getText());
                                //Log.d("myapp2","wendu: "+xmlPullParser.getText());
                            }
                            else if(xmlPullParser.getName().equals("pm25")){
                                eventType = xmlPullParser.next();
                                todayWeather.setPm25(xmlPullParser.getText());
                                //Log.d("myapp2","pm2.5: "+xmlPullParser.getText());
                            }
                            else if(xmlPullParser.getName().equals("quality")){
                                eventType = xmlPullParser.next();
                                todayWeather.setQuality(xmlPullParser.getText());
                                //Log.d("myapp2","quality: "+xmlPullParser.getText());
                            }
                            else if(xmlPullParser.getName().equals("fengxiang")&&fengxiangCount==0){
                                eventType = xmlPullParser.next();
                                todayWeather.setFengxiang(xmlPullParser.getText());
                                //Log.d("myapp2","fengxiang: "+xmlPullParser.getText());
                                fengxiangCount++;
                            }
                            else if(xmlPullParser.getName().equals("fengli")&&fengliCount==0){
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli(xmlPullParser.getText());
                                //Log.d("myapp2","fengli: "+xmlPullParser.getText());
                                fengliCount++;
                            }
                            else if(xmlPullParser.getName().equals("date")&&dateCount==0){
                                eventType = xmlPullParser.next();
                                todayWeather.setData(xmlPullParser.getText());
                                //Log.d("myapp2","date: "+xmlPullParser.getText());
                                dateCount++;
                            }
                            else if(xmlPullParser.getName().equals("high")&&highCount==0){
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh(xmlPullParser.getText().substring(2).trim());
                                //Log.d("myapp2","high: "+xmlPullParser.getText().substring(2));
                                highCount++;
                            }
                            else if(xmlPullParser.getName().equals("low")&&lowCount==0){
                                eventType = xmlPullParser.next();
                                todayWeather.setLow(xmlPullParser.getText().substring(2).trim());
                                //Log.d("myapp2","low: "+xmlPullParser.getText().substring(2));
                                lowCount++;
                            }
                            else if(xmlPullParser.getName().equals("type")&&typeCount==0){
                                eventType = xmlPullParser.next();
                                todayWeather.setType(xmlPullParser.getText());
                                //Log.d("myapp2","type: "+xmlPullParser.getText());
                                typeCount++;
                            }
                        }
                        break;
                    //判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:
                        break;
                }
                //进入下一个元素并触发相应事件
                eventType = xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return todayWeather;
    }

}
