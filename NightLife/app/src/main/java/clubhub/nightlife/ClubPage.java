package clubhub.nightlife;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

/**
 * Created by James R on 2016-04-20.
 */
public class ClubPage extends AppCompatActivity {


    // Used as a temp to set texts of views
    private String temp;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);

        listenerForRatingBar(); // Acts on the changes of the rating bar

        // Acquire the clubs information
        Intent intent = getIntent();
        data = intent.getExtras().getStringArrayList("ClubInformation");

        // Set the clubs name uder the photo
        TextView v = (TextView) findViewById(R.id.textClubsName);
        v.setText(data.get(1)); // Get the clubs name
        // Formatting of the clubs name
        v.setTextSize(50);

        // Set the rating to the average rating of the club
        RatingBar r_Bar = (RatingBar) findViewById(R.id.ratingBar);
        int ratingProgress = 4; // 1 = half a star // TODO get average rating from database and set ratingProgress to it
        r_Bar.setProgress(ratingProgress);

        // Set the Image of the club from the url in the database
        ImageView iv = (ImageView) findViewById(R.id.imageClubView);
        if("NA" != data.get(4))
            Picasso.with(getApplicationContext().getApplicationContext()).load(data.get(4)).into(iv);

        //  Set image of wait time
        // TODO Get wait time with associated image, need to read in a value and select proper image to display
        iv = (ImageView) findViewById(R.id.imageViewWait);
        iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wait_image10));

        // Set image of busy scale
        // TODO busy image according to scale, need to read in a value and select proper image to display
        iv = (ImageView) findViewById(R.id.imageViewBusy);
        iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.busy_image50));

        // Set cover charge image
        // TODO cover charge with image, need to read in a value and select proper image to display
        iv = (ImageView) findViewById(R.id.imageViewCover);
        iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.cover_image10));

        LinearLayout imageLay = (LinearLayout) findViewById(R.id.linear_layout_image_information);
        imageLay.setBackgroundColor(0xFFFF00FF);

        // Set the starting information to be displayed (What onCLickInformation does)
        setClubInformation();
    }

    public void setClubInformation() {
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = "Description: " + data.get(6) + "\n\nAddress: " + data.get(7) + "\n\nPhone: " + data.get(8);
        d.setText(temp);
    }

    public void onClickInformation(View v) {
        // On click set the view to display information data
        setClubInformation();
    }

    public void onClickSpecials(View v) {
        // On click set the view to display specials data
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = data.get(10);
        d.setText(temp);
    }

    public void onClickPromotions(View v) {
        // On click set the view to display promotions data
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        temp = data.get(9);
        d.setText(temp);
    }

    public void onClickGuestList(View V) {
        // On click set the view to display guest list data
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        if (data.get(11).equals("Y")) //TODO This will probably need to be redone to specific clubs
            temp = "Show this at the door before 11:00pm to get in for free";
        else
            temp = "NA";
        d.setText(temp);
    }

    public void listenerForRatingBar() {
        RatingBar r_Bar = (RatingBar) findViewById(R.id.ratingBar);

        r_Bar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean t) {
                        // TODO do something once the user sets the rating
                    }
                }
        );
    }
}