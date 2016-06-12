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

        if(!data.isEmpty()) {
            String text = data.get(0) + "\n" + data.get(1) + "\n";
            for (int i = 2; i < data.size(); i += 2) {
                text = text + "\n" + data.get(i) + "\n" + data.get(i + 1) + "\n";
            }
            TextView promoText = (TextView) findViewById(R.id.textViewPromotions);
            promoText.setText(text);
        }

    }
}
