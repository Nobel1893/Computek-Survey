package holyquran.cls.com.computek_survey.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CLS on 8/13/2018.
 */

public class User {
    int id;
    String full_name;
    @SerializedName("user_name")
    String name;
    String description;
    String active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
