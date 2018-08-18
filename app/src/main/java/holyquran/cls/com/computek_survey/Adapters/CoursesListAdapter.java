package holyquran.cls.com.computek_survey.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import holyquran.cls.com.computek_survey.Model.Course;
import holyquran.cls.com.computek_survey.R;

/**
 * Created by CLS on 8/8/2018.
 */

public class CoursesListAdapter extends RecyclerView.Adapter<CoursesListAdapter.ViewHolder> {

    ArrayList<Course> AllCourses;
    OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public CoursesListAdapter(ArrayList<Course> allCourses) {
        AllCourses = allCourses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_course,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Course course= AllCourses.get(position);
//        holder.coursDate.setText(course.getDate());
        holder.coursName.setText(course.getName());
        holder.coursInstructor.setText(course.getDescription());
        holder.coursName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListner!=null)
                    onItemClickListner.onItemClick(course,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AllCourses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView coursName;
        public final TextView coursInstructor;
        public final TextView coursDate;

        public ViewHolder(View view){
            super(view);
            coursName = view.findViewById(R.id.course_name);
            coursInstructor = view.findViewById(R.id.instructor_name);
            coursDate = view.findViewById(R.id.date);
        }

    }

    public static interface OnItemClickListner{
        void onItemClick(Course course,int pos);
    }


}
