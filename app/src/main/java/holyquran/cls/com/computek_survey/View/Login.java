package holyquran.cls.com.computek_survey.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import holyquran.cls.com.computek_survey.API.APIManager;
import holyquran.cls.com.computek_survey.Model.LoginResponse;
import holyquran.cls.com.computek_survey.Base.MyBaseActivity;
import holyquran.cls.com.computek_survey.DataHolder;
import holyquran.cls.com.computek_survey.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends MyBaseActivity {


    Button loginButton;
    TextInputLayout username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginButton = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String sUserName =username.getEditText().getText().toString();
              String sPassword = password.getEditText().getText().toString();
              if (sUserName.equals("")){
                  ShowMessage("warning","user name required","ok");
                  return;
              }
                if (sPassword.equals("")){
                    ShowMessage("warning","password required","ok");
                    return;
                }
                Login(sUserName,sPassword);
            }
        });
    }



    void Login(String username, final String password){
        ShowProgressBar();
        APIManager.getServices().Login(username,password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        HideProgressBar();

                        LoginResponse loginResponse=response.body();
                        if (loginResponse.getMyStatus().equals("success")){
                        ShowMessage("success",loginResponse.getData().getFull_name(),"ok");
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
                        HideProgressBar();
                        ShowMessage("error",t.getLocalizedMessage(),"ok");
                    }
                });

    }

}
