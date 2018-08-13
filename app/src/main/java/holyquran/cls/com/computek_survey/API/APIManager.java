package holyquran.cls.com.computek_survey.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CLS on 8/13/2018.
 */

public class APIManager {

    public static Retrofit retrofit;

    private static Retrofit getInstance(){

        if (retrofit==null){
         retrofit = new Retrofit.Builder()
                .baseUrl("http://hambozo.com/survey/api/moderator/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;

    }

   public static SurveyServices getServices(){
        if (retrofit==null)
            getInstance();

        return getInstance().create(SurveyServices.class);
    }
}
