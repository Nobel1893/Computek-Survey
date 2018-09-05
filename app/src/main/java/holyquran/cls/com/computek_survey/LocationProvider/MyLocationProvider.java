package holyquran.cls.com.computek_survey.LocationProvider;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

import holyquran.cls.com.computek_survey.Recievers.MyLocationReciever;

/**
 * Created by CLS on 8/18/2018.
 */

public class MyLocationProvider implements LocationListener{

    double latitude;
    double longitude;
    Location location;
    LocationManager locationManager;
    Context context;
    boolean canGetLocation;
    boolean isNetworkEnabled;
    boolean isGpsEnabled;
    long MIN_TIME_BTWEEN_UPDATES = 5*1000*60;
    long MIN_DISTANCE_BTWEEN_UPDATES = 10;

    public MyLocationProvider(Context context) {
        this.context = context;
        latitude = 0.0f;
        longitude = 0.0f;
        location = null;
        canGetLocation=false;
        isGpsEnabled=false;
        isNetworkEnabled = false;
        getLocation();
    }

    public boolean CanGetLocation() {
        return canGetLocation;
    }


    @SuppressLint("MissingPermission")
    public  Location getLocation(){
        String provider="";
        locationManager= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!isGpsEnabled && !isNetworkEnabled){
            canGetLocation=false;
            location=null;
            latitude=0.0f;
            longitude=0.0f;
            Log.e("locationProvider","gps and network not enabled");
        }
        else {
            if (isNetworkEnabled){
                provider=LocationManager.NETWORK_PROVIDER;
                 Log.e("locationProvider"," network ");

            }
            else{
                provider=LocationManager.GPS_PROVIDER;
                Log.e("locationProvider","gps");

            }

            locationManager.requestLocationUpdates(provider,
                    MIN_TIME_BTWEEN_UPDATES,
                    MIN_DISTANCE_BTWEEN_UPDATES,this);


           location= locationManager.getLastKnownLocation(provider);
           if (location!=null){
               latitude=location.getLatitude();
               longitude=location.getLongitude();
               canGetLocation=true;
               Log.e("locationProvider","not null");


           }

        }
        return location;

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location=location;
        this.latitude=location.getLatitude();
        this.longitude= location.getLongitude();
        Log.e("locationChanged",latitude+"  "+ longitude);
        updateLocation();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("provider changed",provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e("provider Enabled",provider);

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void updateLocation( ){
        Calendar cal = Calendar.getInstance();
        // add alarmTriggerTime seconds to the calendar object
        // cal.add(Calendar.SECOND, alarmTriggerTime);
        cal.add(Calendar.SECOND, 1);
        Intent i=new Intent(context,MyLocationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 132, i, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);//set alarm manager with entered timer by converting into milliseconds
        //  Toast.makeText(this, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();


    }

}
