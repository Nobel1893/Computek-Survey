package holyquran.cls.com.computek_survey.Model;

/**
 * Created by CLS on 7/30/2018.
 */

public class Course {
    String courseName;
    String courseInstructor;
    String date;

    public Course(String courseName, String courseInstructor, String date) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
