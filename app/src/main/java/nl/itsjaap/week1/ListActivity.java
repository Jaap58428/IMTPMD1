package nl.itsjaap.week1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import nl.itsjaap.week1.database.DatabaseHelper;
import nl.itsjaap.week1.database.DatabaseInfo;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getApplicationContext());
        Cursor rs = dbHelper.query(DatabaseInfo.ItemTable.ITEMTABLE, new String[]{"*"}, null, null, null, null, null);

        int listSize = rs.getCount();

        Log.d("Lines amount: ","" + listSize);

        // check if cursor isn't empty
        if (listSize > 0) {
            // clear the placeholder text
            TextView itemList = findViewById(R.id.itemList);
            itemList.setText("");

            rs.moveToFirst();

            // Loop over all the items in the list and print them
            for (int i = 0 ; i < listSize ; i++) {
                // Haalt de name uit de resultset
                String name = (String) rs.getString(rs.getColumnIndex("name"));
                String price = (String) rs.getString(rs.getColumnIndex("price"));

                // put the first item on the screen
                itemList.append("Itemname: " + name + "\nPrice: "+ price + "\n\n");

                rs.moveToNext();

                Log.d("DB list item " +  i, "Deze is geprint: " + name + price);
            }
        }
    }
}
