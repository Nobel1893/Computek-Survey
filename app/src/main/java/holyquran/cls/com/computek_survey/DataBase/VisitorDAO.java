package holyquran.cls.com.computek_survey.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import holyquran.cls.com.computek_survey.Model.User;
import holyquran.cls.com.computek_survey.Model.Visitor;

/**
 * Created by CLS on 8/27/2018.
 */
@Dao
public interface VisitorDAO {

    @Query("select * from Visitor;")
    List<Visitor> SelectAllVisitor();

    @Insert
    void AddVisitor(Visitor visitor);

}
