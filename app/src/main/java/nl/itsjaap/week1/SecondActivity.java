package nl.itsjaap.week1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getIntent().hasExtra("userName")) {
            TextView tv = findViewById(R.id.textView);
            String userName = getIntent().getExtras().getString("userName");
            String password = getIntent().getExtras().getString("password");
            tv.setText(userName + password);
            Log.d("Error log", userName);



            Context context = getApplicationContext();
            CharSequence text = "Welkom " + userName + "!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
