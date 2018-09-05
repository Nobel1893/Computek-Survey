package holyquran.cls.com.computek_survey.Recievers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import holyquran.cls.com.computek_survey.LocationProvider.MyLocationProvider;
import holyquran.cls.com.computek_survey.R;

/**
 * Created by CLS on 9/3/2018.
 */

public class MyLocationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        locationProvider = new MyLocationProvider(context);
        UpdateLocation(context);
    }


    MyLocationProvider locationProvider;


    public void UpdateLocation(Context context){
        if (locationProvider!=null&&locationProvider.CanGetLocation()){
            //also you can send it to server
            Log.e("lat",locationProvider.getLatitude()+ " ");
            Log.e("long",locationProvider.getLongitude()+ " ");
            CreateNotification(context,"location update",locationProvider.getLatitude()+ "\n"+locationProvider.getLongitude());

        }
    }

    public void CreateNotification(Context context,String name,String desc){
        int requestCode=10;
        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
     //   Uri alarm2=Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.plucky);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(name)
                        .setContentText(desc)
                        .setContentIntent(pendingIntent)
                       .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestCode, mBuilder.build());
    }
}
