package holyquran.cls.com.computek_survey.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CLS on 8/13/2018.
 */

public class LoginResponse {

    @SerializedName("status")
    String myStatus;
    String message;
    User data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(String myStatus) {
        this.myStatus = myStatus;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
