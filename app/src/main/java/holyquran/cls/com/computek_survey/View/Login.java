package holyquran.cls.com.computek_survey.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Arrays;

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
    CallbackManager callbackManager ;
    AdView adView;
    private static final String EMAIL = "email";
    private static final String USER_GENDER = "user_gender";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MobileAds.initialize(this, "ca-app-pub-6399676632787732~6067214600");

        callbackManager= CallbackManager.Factory.create();
        LoginButton faceBookLoginButton = (LoginButton) findViewById(R.id.login_button);

        faceBookLoginButton.setReadPermissions(Arrays.asList(EMAIL,USER_GENDER));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e("facebook",loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.e("facebook","user cancled login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e("facebook",exception.getMessage());
                    }
                });
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

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.e("errorcode",""+errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }



    void Login(final String username, final String password){
        ShowProgressBar();
        APIManager.getServices().Login(username,password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        HideProgressBar();
                        LoginResponse loginResponse=response.body();
                        if (loginResponse.getMyStatus().equals("success")){
//                        ShowMessage("success",loginResponse.getData().getFull_name(),"ok");
                            DataHolder.password=password;
                            DataHolder.loggedInUser=loginResponse.getData();
                            saveData("user_name",username);
                            saveData("password",password);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
