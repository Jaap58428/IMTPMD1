package nl.itsjaap.week1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the name of the shared prefs file from resources
        final String PREFS_NAME = getResources().getString(R.string.prefs_name);

        // get the value of the username from shared prefs if present
        SharedPreferences credentials = getSharedPreferences(PREFS_NAME, 0);
        String userName = credentials.getString("Username", "");   // DFLT null

        // check if a username is present
        if (userName.length() > 0) {
            // greet the user
            Context context = getApplicationContext();
            CharSequence text = "Welcome back " + userName;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            // set the username in the input
            EditText userNameInput = findViewById(R.id.userNameEditText);
            userNameInput.setText(userName, TextView.BufferType.EDITABLE);
        }

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

                // Pass info to the new screen as key/value pairs
                startIntent.putExtras(b);

                // never store private info in SHARED prefs!!!
                SharedPreferences credentials = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = credentials.edit();
                editor.putString("Username",userName.getText().toString());
                editor.apply();

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
