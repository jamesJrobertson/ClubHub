package clubhub.nightlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by James R on 2016-04-20.
 */
public class ClubPage extends AppCompatActivity {
    private static RatingBar r_Bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);
        listenerForRatingBar();
        //TODO load data from server database
        Intent intent = getIntent();

        int id = intent.getExtras().getInt("ClubID");
        Button v = (Button) findViewById(R.id.button_Promotins);
        v.setText(String.valueOf(id));

    }
    public void listenerForRatingBar()
    {
        r_Bar = (RatingBar) findViewById(R.id.ratingBar);

        r_Bar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener(){
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean t){

                    }
                }
        );
    }
}
