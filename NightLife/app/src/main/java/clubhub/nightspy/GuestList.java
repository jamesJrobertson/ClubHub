package clubhub.nightspy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by James R on 2016-05-27.
 */
public class GuestList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        String data = null;
        // Acquire the clubs information
        Intent intent = getIntent();
        data = intent.getExtras().getString("GuestList");

        TextView text = (TextView) findViewById(R.id.textGuestListView);
        text.setText(data);

    }
}
