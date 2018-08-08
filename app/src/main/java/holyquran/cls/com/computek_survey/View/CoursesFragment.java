package holyquran.cls.com.computek_survey.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import holyquran.cls.com.computek_survey.Adapters.CoursesListAdapter;
import holyquran.cls.com.computek_survey.Base.MyBaseFragment;
import holyquran.cls.com.computek_survey.Model.Course;
import holyquran.cls.com.computek_survey.R;


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
        AddDummyCourses();
        layoutManager=new LinearLayoutManager(activity);
        adapter = new CoursesListAdapter(courses);
        CoursesList.setAdapter(adapter);
        CoursesList.setLayoutManager(layoutManager);
        return rootView;
    }

    public void AddDummyCourses(){
        courses=new ArrayList<>();
        courses.add(new Course("Android","Mohamed Nabil","1/8/2020"));
        courses.add(new Course("IOS","Sleem","1/8/2019"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
        courses.add(new Course("IOS-Android","sayed","1/8/202017"));
    }

    private void initView(View rootView) {
        CoursesList =  rootView.findViewById(R.id.CoursesList);
    }
}
