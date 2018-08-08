package holyquran.cls.com.computek_survey.Base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by CLS on 8/1/2018.
 */

public class MyBaseActivity extends AppCompatActivity {

    protected Activity activity;
    protected MaterialDialog dialog;

   public MyBaseActivity(){
     super();
     activity=this;
    }
   public void ShowConfirmationDialoge(String title,String message,String pos,String neg){
       dialog= new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .positiveText(pos)
                .negativeText(neg)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .show();
    }

    public void ShowProgressBar(){
        dialog=new MaterialDialog.Builder(activity)
                .title("Loading")
                .content("please wait")
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    void HideProgressBar(){
        if (dialog!=null)
        dialog.dismiss();
    }
}
