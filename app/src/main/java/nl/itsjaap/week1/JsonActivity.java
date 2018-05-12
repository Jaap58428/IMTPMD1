package nl.itsjaap.week1;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import nl.itsjaap.week1.database.DatabaseHelper;
import nl.itsjaap.week1.database.DatabaseInfo;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        String json = "[{name: 'hammer', price: '345'},{name: 'cat', price: 'priceless'}]";

        Gson gson = new Gson();

        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

        ItemModel[] items = gson.fromJson(json, ItemModel[].class);

        for(ItemModel course : courses) {
            ContentValues values = new ContentValues();
            values.put(DatabaseInfo.CourseColumn.NAME, course.name);
            values.put(DatabaseInfo.CourseColumn.ECTS, course.ects);
            values.put(DatabaseInfo.CourseColumn.GRADE, course.grade);
            dbHelper.insert(DatabaseInfo.CourseTables.COURSE, null, values);
        }

    }
}
