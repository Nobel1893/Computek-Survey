package holyquran.cls.com.computek_survey.API;

import holyquran.cls.com.computek_survey.Model.CoursesResponse;
import holyquran.cls.com.computek_survey.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CLS on 8/13/2018.
 */

public interface  SurveyServices {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> Login(@Field("user_name") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("entities")
    Call<CoursesResponse> getCourses(@Field("moderator_id") int id, @Field("password") String password);
//?moderator_id=1&password=123456&visitor_name=nabil&visitor_mobile=123456&entity_id=32&visitor_gender=male

    @FormUrlEncoded
    @POST("visitors/create")
    Call<CoursesResponse> AddVisitor(@Field("moderator_id") int id, @Field("password") String password
            , @Field("visitor_name") String visitor_name,@Field("visitor_mobile") String visitor_mobile,
                                     @Field("visitor_gender") String visitor_gender);
}
