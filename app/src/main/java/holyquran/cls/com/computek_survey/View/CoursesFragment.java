package holyquran.cls.com.computek_survey.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import holyquran.cls.com.computek_survey.API.APIManager;
import holyquran.cls.com.computek_survey.Adapters.CoursesListAdapter;
import holyquran.cls.com.computek_survey.Base.MyBaseFragment;
import holyquran.cls.com.computek_survey.DataHolder;
import holyquran.cls.com.computek_survey.Model.Course;
import holyquran.cls.com.computek_survey.Model.CoursesResponse;
import holyquran.cls.com.computek_survey.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends MyBaseFragment {


    protected View rootView;
    protected RecyclerView CoursesList;
    CoursesListAdapter adapter;
    ArrayList<Course> courses;
    LinearLayoutManager layoutManager;

    public CoursesFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        initView(rootView);
        layoutManager=new LinearLayoutManager(activity);
        CoursesList.setLayoutManager(layoutManager);
        getCourses(DataHolder.loggedInUser.getId(),DataHolder.password);
        return rootView;
    }


    public void getCourses(int moderator_id ,String password){

        ShowProgressBar();
        APIManager.getServices().getCourses(moderator_id,password).enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                HideProgressBar();
                if (response.body().getStatus().equals("success")){
                    courses=response.body().getCourses();
                    adapter = new CoursesListAdapter(courses);
                    CoursesList.setAdapter(adapter);
                }else ShowMessage("error",response.body().getMessage(),"ok");
            }

            @Override
            public void onFailure(Call<CoursesResponse> call, Throwable t) {
                HideProgressBar();
                ShowMessage("error",t.getLocalizedMessage(),"ok");
            }
        });
    }
    private void initView(View rootView) {
        CoursesList =  rootView.findViewById(R.id.CoursesList);
    }
}
