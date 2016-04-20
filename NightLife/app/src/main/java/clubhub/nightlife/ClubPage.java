package clubhub.nightlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;

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
