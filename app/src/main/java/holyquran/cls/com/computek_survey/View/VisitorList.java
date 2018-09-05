package holyquran.cls.com.computek_survey.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import holyquran.cls.com.computek_survey.Adapters.VisitorAdapter;
import holyquran.cls.com.computek_survey.Base.MyBaseActivity;
import holyquran.cls.com.computek_survey.DataBase.VisitorDatabase;
import holyquran.cls.com.computek_survey.Model.Visitor;
import holyquran.cls.com.computek_survey.R;

public class VisitorList extends MyBaseActivity {
    VisitorAdapter adapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    List<Visitor> visitors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        visitors=VisitorDatabase.getINSTANCE(getApplicationContext()).visitorDao().SelectAllVisitor();
        layoutManager = new LinearLayoutManager(activity);
        adapter = new VisitorAdapter(visitors);
        recyclerView = findViewById(R.id.VisitorListview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
