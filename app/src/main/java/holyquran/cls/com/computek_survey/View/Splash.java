package holyquran.cls.com.computek_survey.View;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import holyquran.cls.com.computek_survey.Base.MyBaseActivity;
import holyquran.cls.com.computek_survey.R;

public class Splash extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(activity,Login.class));
            }
        },2000);
    }
}
