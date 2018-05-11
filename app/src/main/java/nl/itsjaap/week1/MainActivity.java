package nl.itsjaap.week1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Bundle b = new Bundle();

                EditText userName = findViewById(R.id.userNameEditText);
                EditText password = findViewById(R.id.passwordEditText);

                b.putString("userName", userName.getText().toString());
                b.putString("password", password.getText().toString());

                // Pass info to the new screen as key/value pairs
                startIntent.putExtras(b);

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
