package clubhub.nightlife;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;

public class ListClubs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clubs);
        //TODO Load data from server database


        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout1);

        Vector<ProgressBar> progs = new Vector<ProgressBar>();
        Vector<LinearLayout> lls = new Vector<LinearLayout>();
        ProgressBar prog;
        TextView txt, txt2, txt3, txt4, txt5;
        LinearLayout linlay, linlay2, linlay3;

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
                txt.setGravity(Gravity.LEFT);
                linlay2.addView(txt);

                // Set the wait time
                mLine = reader.readLine();
                txt2 = new TextView(this);
                txt2.setText("Line " + mLine + " min");
                txt2.setGravity(Gravity.RIGHT);
                txt2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linlay2.addView(txt2);


                linlay3 = new LinearLayout(this);
                linlay3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linlay3.setOrientation(LinearLayout.HORIZONTAL);

                txt3 = new TextView(this);
                txt3.setText("Not Busy");
                txt3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                linlay3.addView(txt3);

                txt4 = new TextView(this);
                txt4.setText("Average");
                txt4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                linlay3.addView(txt4);

                txt5 = new TextView(this);
                txt5.setText("Busy");
                txt5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                linlay3.addView(txt5);

                mLine = reader.readLine();
                prog = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
                prog.setProgress(Integer.parseInt(mLine));
                prog.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

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
