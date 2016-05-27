package clubhub.nightspy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by James R on 2016-05-27.
 */
public class MainPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


    }
    public void onClickClubs(View v){
        Intent intent = new Intent("android.intent.action.ListClubs"); // Change to list clubs page
        startActivity(intent);
    }
    public void onClickEvents(View v){

    }
    public void onClickContacts(View v){

    }
}
