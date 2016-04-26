package clubhub.nightlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by James R on 2016-04-20.
 *
 */
public class ClubPage extends AppCompatActivity {
    private static RatingBar r_Bar;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);
        listenerForRatingBar();


        //TODO load data from server database

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("ClubID");
        TextView v = (TextView)findViewById(R.id.textClubsName);
        String clubName = String.valueOf(id); // TODO need to get the name from the database according to id#
        v.setText(clubName);
        v.setTextSize(50);

        r_Bar = (RatingBar) findViewById(R.id.ratingBar);
        int ratingProgress = 4; // 1 = half a star // TODO get average rating from database and set ratingProgress to it
        r_Bar.setProgress(ratingProgress);

        ImageView iv = (ImageView) findViewById(R.id.imageClubView);
        // TODO get the image the club wants displayed and set iv to it

        /*
        * This 3 need to be changed in the layout to imageview right not they are just text for placeholders
        * TODO Get wait time with associated image
        * TODO busy image according to scale
        * TODO cover charge with image
        */

        // Set the starting information to be displayed (What onCLickInformation does)
        // TODO Look at onClickInformation
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = "Information:\n" +
                "Display information and such";
        d.setText(temp);

    }

    public void onClickInformation(View v){
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = "Information:\n" +
                "Display information and such";
        d.setText(temp);
        // TODO gather and display clubs information from the database

    }
    public void onClickSpecials(View v){
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = "Specials";
        d.setText(temp);
        // TODO gather and display the specials data from the database
    }

    public void onClickPromotions(View v){
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = "Promotions: Nothing at this time";
        d.setText(temp);
        // TODO gather and display the promotion data from the database
    }
    public void onClickGuestList(View V){
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = "Guest List: NA";
        d.setText(temp);
        // TODO Guest list (still need to figure this one out correctly) Have fields to fill out with a submit button

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
