package com.example.stone.gaodesdk;

import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,LocationSource{

    MapView mMapView = null;
    private AMap aMap;
    private Button standard;
    private Button night;
    private Button startlite;
    private UiSettings mUiSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.interval(2000);
        aMap.setMyLocationStyle(locationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.showIndoorMap(true);
        mUiSetting = aMap.getUiSettings();
        mUiSetting.setCompassEnabled(true);
        aMap.setLocationSource(this);//通过aMap对象设置定位数据源的监听

        mUiSetting.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        mUiSetting.setAllGesturesEnabled (true);
        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置
    }

    private void initEvent() {
        startlite.setOnClickListener(this);
        standard.setOnClickListener(this);
        night.setOnClickListener(this);
    }

    private void initView() {
        standard = (Button) findViewById(R.id.standard);
        startlite = (Button) findViewById(R.id.stalite);
        night = (Button) findViewById(R.id.night);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.night:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);// 设置夜间地图模式
                break;
            case R.id.standard:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 设置标准地图模式
                break;
            case R.id.stalite:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式
                break;
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
