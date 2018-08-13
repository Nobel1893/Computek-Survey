package holyquran.cls.com.computek_survey.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by CLS on 8/13/2018.
 */

public class CoursesResponse  {
    String status;
    String message;
    @SerializedName("data")
    ArrayList<Course> courses;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
