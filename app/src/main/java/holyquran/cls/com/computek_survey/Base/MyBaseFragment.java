package holyquran.cls.com.computek_survey.Base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by CLS on 8/8/2018.
 */

public class MyBaseFragment extends Fragment {

    protected MyBaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(MyBaseActivity) context;

    }

    public void ShowConfirmationDialoge(String title,String message,String pos,String neg){
        activity.ShowConfirmationDialoge(title,message,pos,neg);
    }

    public void ShowProgressBar(){
       activity.ShowProgressBar();
    }

    void HideProgressBar(){
        activity.HideProgressBar();
    }
}
