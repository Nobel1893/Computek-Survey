package holyquran.cls.com.computek_survey.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import holyquran.cls.com.computek_survey.Model.Visitor;

/**
 * Created by CLS on 8/27/2018.
 */
@Database(entities = {Visitor.class}, version = 1, exportSchema = false)
public abstract class VisitorDatabase extends RoomDatabase{

    private static VisitorDatabase INSTANCE;

    public abstract VisitorDAO visitorDao();

    public static VisitorDatabase getINSTANCE(Context context){

            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), VisitorDatabase.class, "user-database")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();
            }
            return INSTANCE;
    }

}
