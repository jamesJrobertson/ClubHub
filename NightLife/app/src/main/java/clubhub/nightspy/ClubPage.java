package clubhub.nightspy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

        // Set the clubs name on photo
        TextView v = (TextView) findViewById(R.id.textClubsName);
        v.setText(data.get(1)); // Get the clubs name
        // Formatting of the clubs name
        v.setTextSize(50);

        // Set the rating to the average rating of the club
        RatingBar r_Bar = (RatingBar) findViewById(R.id.ratingBar);
        int ratingProgress = Integer.parseInt(data.get(12)); // 1 = half a star
        r_Bar.setProgress(ratingProgress);

        // Set the Image of the club from the url in the database
        ImageView iv = (ImageView) findViewById(R.id.imageClubView);
        if ("NA" != data.get(4))
            Picasso.with(getApplicationContext().getApplicationContext()).load(data.get(4)).into(iv);

        // Temp variable to assign images to according data values
        Drawable drawTemp = null;

        //  Set image of wait time
        TextView waitTimeText = (TextView) findViewById(R.id.textViewWaitTime);
        waitTimeText.setText(data.get(2));

        // Set image of capacity scale
        iv = (ImageView) findViewById(R.id.imageViewBusy);
        int busyScale = Integer.parseInt(data.get(3));
        if (15 > busyScale)
            drawTemp = ContextCompat.getDrawable(getApplicationContext(), R.drawable.capacity10);
        else if (15 <= busyScale && 27 > busyScale)
            drawTemp = ContextCompat.getDrawable(getApplicationContext(), R.drawable.capacity20);
        else if (27 <= busyScale && 40 > busyScale)
            drawTemp = ContextCompat.getDrawable(getApplicationContext(), R.drawable.capacity35);
        else if (40 <= busyScale && 53 > busyScale)
            drawTemp = ContextCompat.getDrawable(getApplicationContext(), R.drawable.capacity45);
        else if (53 <= busyScale && 73 > busyScale)
            drawTemp = ContextCompat.getDrawable(getApplicationContext(), R.drawable.capacity60);
        else if (73 <= busyScale)
            drawTemp = ContextCompat.getDrawable(getApplicationContext(), R.drawable.capacity85);
        iv.setImageDrawable(drawTemp);


        // Set cover charge
        TextView coverText = (TextView) findViewById(R.id.textViewCover);
        waitTimeText.setText("$" + data.get(5));

        // Get the layout of cober, busy, wait time images and change the background
        LinearLayout imageLay = (LinearLayout) findViewById(R.id.linear_layout_image_information);
        //imageLay.setBackgroundColor(0xFFFF00FF);

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

//    public void onClickPromotions(View v) {
//        // On click set the view to display promotions data
//        TextView d = (TextView) findViewById(R.id.textDisplyBox);
//        temp = data.get(9);
//        d.setText(temp);
//    }

    public void onClickGuestList(View V) {
        // On click set the view to display guest list data
        TextView d = (TextView) findViewById(R.id.textDisplyBox);
        if (data.get(11).equals("Y")) //TODO This will probably need to be redone to specific clubs
            temp = "Show this at the door before 11:00pm to get in for free";
        else
            temp = "NA";
        // d.setText(temp);


        Intent intent = new Intent("android.intent.action.GuestList"); // Change to the clubs page
        intent.putExtra("GuestList", temp); // pass the id to the new page
        startActivity(intent);

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