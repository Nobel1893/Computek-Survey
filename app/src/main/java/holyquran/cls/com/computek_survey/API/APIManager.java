package holyquran.cls.com.computek_survey.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CLS on 8/13/2018.
 */

public class APIManager {

    public static Retrofit retrofitInstance;

    private static Retrofit getInstance(){

        if (retrofitInstance==null){
            retrofitInstance = new Retrofit.Builder()
                .baseUrl("http://hambozo.com/survey/api/moderator/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofitInstance;

    }

   public static SurveyServices getServices(){
        if (retrofitInstance==null)
            getInstance();

        return getInstance().create(SurveyServices.class);
    }
}
