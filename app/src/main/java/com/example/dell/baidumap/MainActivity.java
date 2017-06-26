package com.example.dell.baidumap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private InfoWindow mInfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapview);

        baiduMap = mapView.getMap();

        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        LatLng point = new LatLng(40.049256,116.306406);

        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding);

        OverlayOptions option = new MarkerOptions()
                .position(point)
                .title(".....")
                .position(point)
                .icon(bitmap);

        baiduMap.addOverlay(option);

        //创建InfoWindow展示的view
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.mipmap.popup);
//定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(39.86923, 116.397428);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        button.setTextColor(Color.BLACK);
        button.setText(point.toString());
        mInfoWindow = new InfoWindow(button, point, -47);
//显示InfoWindow


        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(MainActivity.this, ""+ marker.getTitle()+""+marker.getPosition(), Toast.LENGTH_SHORT).show();
                baiduMap.showInfoWindow(mInfoWindow);
                return true;
            }
        });
    }
}
