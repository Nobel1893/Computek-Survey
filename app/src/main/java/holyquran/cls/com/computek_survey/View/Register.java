package holyquran.cls.com.computek_survey.View;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import holyquran.cls.com.computek_survey.API.APIManager;
import holyquran.cls.com.computek_survey.Base.MyBaseActivity;
import holyquran.cls.com.computek_survey.DataHolder;
import holyquran.cls.com.computek_survey.Model.CoursesResponse;
import holyquran.cls.com.computek_survey.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends MyBaseActivity implements View.OnClickListener {


    protected TextInputLayout username;
    protected TextInputLayout phone;
    protected Spinner gender;
    protected Button submit;

    String genderData[]={"male","female"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        genderData); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        gender.setAdapter(spinnerArrayAdapter);


    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            String name= username.getEditText().getText().toString();
            if (name.isEmpty()){
                ShowMessage("error","name is empty","ok");
                return;
            }
            String Sphone=phone.getEditText().getText().toString();
             if (Sphone.isEmpty()){
                 ShowMessage("error","name is empty","ok");
                 return;
             }
             String sGender=null;
             int pos= gender.getSelectedItemPosition();
             if (pos==0)
                 sGender="male";
             else if (pos==1)
                 sGender="female";

             RegisterVisitor(name,Sphone,sGender);


        }
    }
    public void RegisterVisitor(String name,String phone,String gender){

        ShowProgressBar();
        APIManager.getServices().AddVisitor(DataHolder.loggedInUser.getId(),DataHolder.password,
                name,phone,gender).enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                HideProgressBar();
                ShowMessage("success","visitor registered successfuly","ok");

            }

            @Override
            public void onFailure(Call<CoursesResponse> call, Throwable t) {
                HideProgressBar();
                ShowMessage("error",t.getLocalizedMessage(),"ok");
            }
        });
    }
    private void initView() {
        username = (TextInputLayout) findViewById(R.id.username);
        phone = (TextInputLayout) findViewById(R.id.phone);
        gender = (Spinner) findViewById(R.id.gender);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(Register.this);
    }
}
