package nl.itsjaap.week1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getIntent().hasExtra("nl.itsjaap.week1.TEXT")) {
            TextView tv = findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("nl.itsjaap.week1.TEXT");
            tv.setText(text);
        }
    }
}
