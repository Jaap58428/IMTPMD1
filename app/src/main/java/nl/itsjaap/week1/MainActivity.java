package nl.itsjaap.week1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivityBtn = findViewById(R.id.secectActivityBtn);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent so the button knows what to go to
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);

                // Pass info to the new screen as key/value pairs
                startIntent.putExtra("nl.itsjaap.week1.TEXT", "Hello Jacob!");

                // start extra screen
                startActivity(startIntent);
            }
        });

        Button externalBtn = findViewById(R.id.internetBtn);
        externalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String portfolio = "https://itsjaap.nl/";
                Uri webaddress = Uri.parse(portfolio);

                Intent goOnline = new Intent(Intent.ACTION_VIEW, webaddress);

                if (goOnline.resolveActivity(getPackageManager()) != null) {
                    startActivity(goOnline);
                }
            }
        });
    }
}
