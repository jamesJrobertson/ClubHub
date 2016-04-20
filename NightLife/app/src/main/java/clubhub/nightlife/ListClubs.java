package clubhub.nightlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListClubs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clubs);
    }

    public void buttonClub1OnClick(View v)
    {

        Intent intent = new Intent("android.intent.action.ClubPage");
        startActivity(intent);
        //finish();


    }


}
