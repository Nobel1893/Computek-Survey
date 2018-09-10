package holyquran.cls.com.computek_survey;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import holyquran.cls.com.computek_survey.OneSignalHandlers.OnNotificationRecieved;
import holyquran.cls.com.computek_survey.OneSignalHandlers.OnNotificationsOpenedHandler;

/**
 * Created by CLS on 9/3/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new OnNotificationRecieved())
                .setNotificationOpenedHandler(new OnNotificationsOpenedHandler())
                .init();


    }
}
