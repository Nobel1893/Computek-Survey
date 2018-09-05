package holyquran.cls.com.computek_survey.OneSignalHandlers;

import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

/**
 * Created by CLS on 9/3/2018.
 */

public class OnNotificationRecieved implements OneSignal.NotificationReceivedHandler {

    @Override
    public void notificationReceived(OSNotification notification) {
        //customize as you wish
        Log.e("oneSignal","new Notification");

    }
}
