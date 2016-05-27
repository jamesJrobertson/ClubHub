package clubhub.nightspy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

/**
 * Created by James R on 2016-05-27.
 */
public class MainPage extends AppCompatActivity {
    Vector<Vector<String>> database = new Vector<Vector<String>>();
    Vector<String> promoList = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initializeDatabase();
        initializePromoList();

    }
    public void onClickClubs(View v){
        Intent intent = new Intent("android.intent.action.ListClubs"); // Change to list clubs page
        startActivity(intent);
    }
    public void onClickEvents(View v){
        Intent intent = new Intent("android.intent.action.Promotions"); // Change to the clubs page
        intent.putExtra("ClubInformation", promoList); // pass the id to the new page
        startActivity(intent);
    }
    public void onClickContacts(View v){
        Intent intent = new Intent("android.intent.action.ContactUs"); // Change to list clubs page
        startActivity(intent);
    }
    private void initializePromoList(){
        for(int i = 0; i < database.size(); i++){
            promoList.add(database.elementAt(i).elementAt(1));
            promoList.add(database.elementAt(i).elementAt(9));
        }
    }
    private void initializeDatabase() {
        database.clear();
        // Get the database
        ListFeed worksheets = null;
        try {
            worksheets = new GetDataAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Fill database from google sheets
        for (ListEntry row : worksheets.getEntries()) {
            Vector<String> data = new Vector<String>();
            for (String tag : row.getCustomElements().getTags()) {
                if (row.getCustomElements().getValue(tag) == null)
                    data.add("NA"); // Checking to see if the cell has something in it
                else
                    data.add(row.getCustomElements().getValue(tag)); // Add the information to the clubs data
            }
            database.add(data); // Add the clubs information to the data list
        }

    }
}
