package com.xrxsh.lyk.gpsdemo;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    //位置服务
    private LocationManager lm;
    private MyLocationListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();
        //给位置提供者设置条件
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //criteria.setPowerRequirement(); 可以设置电量要求
        //criteria.setAltitudeRequired();不要求海拔
        //criteria.setBearingRequired();不要求方位
        String provider = lm.getBestProvider(criteria, true);
        lm.requestLocationUpdates(provider,0,0,listener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消监听
        lm.removeUpdates(listener);
        listener =null;
    }

    class MyLocationListener implements LocationListener{
        //当位置改变的时候回调
        @Override
        public void onLocationChanged(Location location) {
            String longitude = "经度："+location.getLongitude();
            String latitude = "纬度："+location.getLatitude();
            String accuracy = "精确度"+location.getAccuracy();
            TextView textview = new TextView(MainActivity.this);
            textview.setText(longitude+"\n"+latitude+"\n"+accuracy);
            setContentView(textview);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
