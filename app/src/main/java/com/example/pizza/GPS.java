package com.example.pizza;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Criteria;
import android.location.*;
import android.os.Bundle;

import java.io.IOException;
import java.lang.String;
import java.util.Locale;

/**
 * Created by 44 on 2015/11/14.
 */
public class GPS  implements LocationListener {

    private String provider = "";
    private LocationManager mLocationManager = null;
    private Context mcontext;
    public GPS(Context context){
        mcontext = context;
        // LocationManagerを取得
       mLocationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Criteriaオブジェクトを生成
        Criteria criteria = new Criteria();

        // Accuracyを指定(低精度)
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        // PowerRequirementを指定(低消費電力)
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        // ロケーションプロバイダの取得
        provider = mLocationManager.getBestProvider(criteria, true);


        // LocationListenerを登録
        mLocationManager.requestLocationUpdates(provider, 0, 0, this);

    }
    public String getAddress(){
        double latitude = mLocationManager.getLastKnownLocation(provider).getLatitude();
        double longitude = mLocationManager.getLastKnownLocation(provider).getLongitude();
        Geocoder gcoder = new Geocoder(mcontext, Locale.getDefault());
        String address = "";
        try {
            address = gcoder.getFromLocation(latitude, longitude, 1).get(0).getAddressLine(1);
        } catch (IOException e){
            e.printStackTrace();
        }
        return address;
    }
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}

