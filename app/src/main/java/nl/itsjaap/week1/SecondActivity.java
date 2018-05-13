package nl.itsjaap.week1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import nl.itsjaap.week1.database.DatabaseHelper;
import nl.itsjaap.week1.database.DatabaseInfo;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getIntent().hasExtra("userName")) {
            TextView tv = findViewById(R.id.textView);
            String userName = getIntent().getExtras().getString("userName");
            String password = getIntent().getExtras().getString("password");
            tv.setText(userName + "'s list");

            Context context = getApplicationContext();
            CharSequence text = "Welkom " + userName + "!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        Button addBtn = findViewById(R.id.addThingBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newThing = findViewById(R.id.thingNameEditText);
                String newThingName = newThing.getText().toString();

                EditText newThingPrice = findViewById(R.id.thingPriceEditText);
                String newThingPriceValue = newThingPrice.getText().toString();

                if (newThingName.length() > 0 && newThingPriceValue.length() > 0) {

                    // put items in the DB
                    // make instance of DB helper
                    DatabaseHelper dbHelper = DatabaseHelper.getHelper(getApplicationContext());
                    // create class of DB values and assign values to every column
                    ContentValues values = new ContentValues();
                    values.put(DatabaseInfo.ItemColumn.NAME, newThingName);
                    values.put(DatabaseInfo.ItemColumn.PRICE, newThingPriceValue);
                    // insert the VALUES object into the DB
                    dbHelper.insert(DatabaseInfo.ItemTable.ITEMTABLE, null, values);

                    // show toast with success of save
                    Context context = getApplicationContext();
                    CharSequence text = newThingName + " is saved.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter both a name and a price!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

        Button viewBtn = findViewById(R.id.seeThingsBtn);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ListActivity.class);
                // start extra screen
                startActivity(startIntent);
            }
        });

        Button gsonBtn = findViewById(R.id.showJsonBtn);
        gsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = "[{name: 'hammer', price: '345'},{name: 'cat', price: 'priceless'}]";

                Gson gson = new Gson();

                DatabaseHelper dbHelper = DatabaseHelper.getHelper(getApplicationContext());

                // create instances of ItemModels
                ItemModel[] items = gson.fromJson(json, ItemModel[].class);

                // loop over the models and set them to the DB
                for(ItemModel item : items) {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseInfo.ItemColumn.NAME, item.getName());
                    values.put(DatabaseInfo.ItemColumn.PRICE, item.getPrice());
                    dbHelper.insert(DatabaseInfo.ItemTable.ITEMTABLE, null, values);
                }
            }
        });


    }
}
