package holyquran.cls.com.computek_survey.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;

import holyquran.cls.com.computek_survey.API.APIManager;
import holyquran.cls.com.computek_survey.Base.MyBaseActivity;
import holyquran.cls.com.computek_survey.DataHolder;
import holyquran.cls.com.computek_survey.Model.LoginResponse;
import holyquran.cls.com.computek_survey.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends MyBaseActivity {
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

           username=getSavedData("user_name",null);
           password=getSavedData("password",null);

          if (username==null&&password==null){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(activity,Login.class));
                    }
                },2000);
          }else {
              APIManager.getServices().Login(username,password).enqueue(new Callback<LoginResponse>() {
                  @Override
                  public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                      LoginResponse loginResponse=response.body();
                      if (loginResponse.getMyStatus().equals("success")){
                          DataHolder.password=password;
                          DataHolder.loggedInUser=loginResponse.getData();
                          startActivity(new Intent(activity,HomeActivity.class));
                          finish();
                      }else {
                          ShowMessage("error",loginResponse.getMessage(),"ok");
                      }
                  }

                  @Override
                  public void onFailure(Call<LoginResponse> call, Throwable t) {
                      ShowMessage("error",t.getLocalizedMessage(),"ok");
                  }
              });
          }

    }
}
