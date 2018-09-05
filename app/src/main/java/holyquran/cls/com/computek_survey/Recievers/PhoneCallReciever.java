package holyquran.cls.com.computek_survey.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by CLS on 9/5/2018.
 */

public class PhoneCallReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("phone state Reciever",intent.getAction());

    }
}
