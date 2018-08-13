package holyquran.cls.com.computek_survey.API;

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
}
