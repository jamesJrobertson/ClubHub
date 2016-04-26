package clubhub.nightlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;


public class ListClubs extends AppCompatActivity {
    Vector<LinearLayout> lls = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clubs);
        //TODO Load data from server database

        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout1);
        ProgressBar prog;
        TextView txt, txt2, txt3, txt4, txt5;
        LinearLayout linlay, linlay2, linlay3;
        String l;

        BufferedReader reader = null;
        int id = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("ClubsDatabase")));
            String mLine;
            while ((mLine = reader.readLine()) != null) {

                linlay = new LinearLayout(this);
                linlay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linlay.setGravity(Gravity.CENTER);
                linlay.setClickable(true);
                linlay.setOnClickListener(myhandler);
                linlay.setOrientation(LinearLayout.VERTICAL);

                linlay2 = new LinearLayout(this);
                linlay2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linlay2.setOrientation(LinearLayout.HORIZONTAL);

                // Set the name of the club
                txt = new TextView(this);
                txt.setText(mLine);
                txt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                txt.setGravity(Gravity.START);
                linlay2.addView(txt);

                // Set the wait time
                mLine = reader.readLine();
                txt2 = new TextView(this);
                l = "Line " + mLine + " min";
                txt2.setText(l);
                txt2.setGravity(Gravity.END);
                txt2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linlay2.addView(txt2);


                linlay3 = new LinearLayout(this);
                linlay3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linlay3.setOrientation(LinearLayout.HORIZONTAL);

                // Noy busy text to be added
                l = "Not Busy";
                txt3 = new TextView(this);
                txt3.setText(l);
                txt3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                linlay3.addView(txt3);

                // Average text to be added
                txt4 = new TextView(this);
                l = "Average";
                txt4.setText(l);
                txt4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                linlay3.addView(txt4);

                // Busy text to be added
                txt5 = new TextView(this);
                l = "Busy";
                txt5.setText(l);
                txt5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                linlay3.addView(txt5);

                // Progress bar to be added
                mLine = reader.readLine();
                prog = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
                prog.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                prog.setProgressDrawable(getResources().getDrawable(R.drawable.progress_colour));
                prog.setProgress(Integer.parseInt(mLine));

                // add all to the overall layout
                linlay.addView(linlay2);
                linlay.addView(linlay3);
                linlay.addView(prog);
                linlay.setId(id);
                lls.add(linlay);
                id++;
            }
        } catch (IOException e) {
            Log.d("TEST", "Exception thrown file not read");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d("TEST", "Exception thrown file not read");
                }
            }
        }

        for (int i = 0; i < lls.size(); i++ )
        {
            ll.addView(lls.elementAt(i));
        }
    }

    public void orderByName(View ve){
        // TODO reorganize clubs by alphabetical order

        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout1);
        for (int i = (lls.size()-1); i >= 0; i-- ) {
            ll.removeView(findViewById(i));
        }

        for (int i = (lls.size()-1); i >= 0; i-- ) {
            ll.addView(lls.elementAt(i));
        }

    }
    public void orderByBusy(View v){
        // TODO reorganize clubs by how busy they are

        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout1);
        for (int i = (lls.size()-1); i >= 0; i-- ) {
            ll.removeView(findViewById(i));
        }

        for (int i = 0; i < lls.size(); i++ ) {
            ll.addView(lls.elementAt(i));
        }
    }
    public void orderByWaitTime(View v){
        // TODO reorganize clubs by how much wait time there is
        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout1);
        for (int i = (lls.size()-1); i >= 0; i-- ) {
            ll.removeView(findViewById(i));
        }

        for (int i = (lls.size()-1); i >= 0; i-- ) {
            ll.addView(lls.elementAt(i));
        }
    }

    private View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int clubID = v.getId();
            Intent intent = new Intent("android.intent.action.ClubPage");
            intent.putExtra("ClubID", clubID);
            startActivity(intent);
        }
    };
}
