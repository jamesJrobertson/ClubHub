package clubhub.nightspy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James R on 2016-05-27.
 */
public class Promotions extends AppCompatActivity {
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        // Acquire the clubs information
        Intent intent = getIntent();
        data = intent.getExtras().getStringArrayList("ClubInformation");

        String text = " ";
        TextView promoText = (TextView) findViewById(R.id.textViewPromotions);
        for(int i = 0; i < data.size(); i += 2){
            text = text + "\n" + data.get(i) + "\n" + data.get(i+1);
        }
        promoText.setText(text);
    }
}
