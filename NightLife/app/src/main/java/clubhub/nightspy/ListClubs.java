package clubhub.nightspy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class ListClubs extends AppCompatActivity {
    Vector<LinearLayout> lls = new Vector<>();
    Vector<Vector<String>> database = new Vector<Vector<String>>();
    int orderFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clubs);

        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeView.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        initializeDatabase();
                        initializePage();

                    }
                }, 1000);
            }
        });

        initializeDatabase();
        initializePage();

    }

    private void initializePage() {


        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout1); // The layout everything on ListClub goes into
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_layout_listclub);

        // Reset the layout if there was previously anything in it
        for (int i = (lls.size() - 1); i >= 0; i--) {
            ll.removeView(findViewById(i));
        }
        lls.clear();

        // Set the overall background colour
        // TODO get a drawable or image as the background, single colour is boring
        //rl.setBackgroundColor(0xFFFFFFFF);

        // Dummy variables to use for each club
        ProgressBar prog;
        TextView txt, txt2, txt3, txt4, txt5;
        LinearLayout linlay, linlay2, linlay3;

        String l; // Used as a temp to set texts of views

        for (int i = 0; i < database.size(); i++) {

            // The overall layout for one club
            linlay = new LinearLayout(this);
            linlay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linlay.setGravity(Gravity.CENTER);
            linlay.setClickable(true);
            linlay.setOnClickListener(myhandler);
            linlay.setOrientation(LinearLayout.VERTICAL);

            // The layout for the name and wait time to go in
            linlay2 = new LinearLayout(this);
            linlay2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linlay2.setOrientation(LinearLayout.HORIZONTAL);

            // Set the name of the club
            txt = new TextView(this);
            txt.setText(database.elementAt(i).elementAt(1));
            txt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            txt.setGravity(Gravity.START);
            // Set font of the club name
            txt.setTextSize(30);
            txt.setTypeface(null, Typeface.BOLD);
            linlay2.addView(txt);

            // Set the wait time
            txt2 = new TextView(this);
            l = "Wait " + database.elementAt(i).elementAt(2) + " min";
            txt2.setText(l);
            txt2.setGravity(Gravity.END | Gravity.BOTTOM);
            txt2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linlay2.addView(txt2);

            // The layout for the busy scale text to go into
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
            prog = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            prog.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            prog.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_colour));
            prog.setProgress(Integer.parseInt(database.elementAt(i).elementAt(3)));

            // add all to the overall layout
            linlay.addView(linlay2);
            linlay.addView(linlay3);
            linlay.addView(prog);
            linlay.setId(Integer.parseInt(database.elementAt(i).elementAt(0)));
            linlay.setPadding(0, 20, 0, 20);
            //linlay.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boarder2));
            lls.add(linlay);

        }

        for (int i = 0; i < lls.size(); i++)
            ll.addView(lls.elementAt(i)); // Add all views to the ListClubs page

    }

    public void orderByName(View ve) {
        // Sort clubs alphabetically
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout1);
        for (int i = (lls.size() - 1); i >= 0; i--) {
            ll.removeView(findViewById(i));
        }
        int placeHolder[] = new int[database.size()];
        for (int i = 0; i < placeHolder.length; i++)
            placeHolder[i] = i;

        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (int j = 0; j < placeHolder.length - 1; j++) {
                if (0 > database.elementAt(placeHolder[j]).elementAt(1).compareToIgnoreCase(database.elementAt(placeHolder[j + 1]).elementAt(1)))   // change to > for ascending sort
                {
                    temp = placeHolder[j];                //swap elements
                    placeHolder[j] = placeHolder[j + 1];
                    placeHolder[j + 1] = temp;
                    flag = true;              //shows a swap occurred
                }
            }
        }
        if (1 == orderFlag) {
            orderFlag = 2;
            for (int i = 0; i < lls.size(); i++) {
                ll.addView(lls.elementAt(placeHolder[i]));
            }
        } else {
            orderFlag = 1;
            for (int i = (lls.size() - 1); i >= 0; i--) {
                ll.addView(lls.elementAt(placeHolder[i]));
            }
        }


    }

    public void orderByBusy(View v) {
        // Change the order of the clubs by how busy they are

        // Remove all clubs
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout1);
        for (int i = (lls.size() - 1); i >= 0; i--) {
            ll.removeView(findViewById(i));
        }

        int placeHolder[] = new int[database.size()];
        for (int i = 0; i < placeHolder.length; i++)
            placeHolder[i] = i;

        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (int j = 0; j < placeHolder.length - 1; j++) {
                if (Integer.parseInt(database.elementAt(placeHolder[j]).elementAt(3)) < Integer.parseInt(database.elementAt(placeHolder[j + 1]).elementAt(3)))   // change to > for ascending sort
                {
                    temp = placeHolder[j];                //swap elements
                    placeHolder[j] = placeHolder[j + 1];
                    placeHolder[j + 1] = temp;
                    flag = true;              //shows a swap occurred
                }
            }
        }
        if (3 == orderFlag) {
            orderFlag = 4;
            for (int i = 0; i < lls.size(); i++) {
                ll.addView(lls.elementAt(placeHolder[i]));
            }
        } else {
            orderFlag = 3;
            for (int i = (lls.size() - 1); i >= 0; i--) {
                ll.addView(lls.elementAt(placeHolder[i]));
            }
        }

    }

    public void orderByWaitTime(View v) {
        // Sort clubs by wait time
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout1);
        for (int i = (lls.size() - 1); i >= 0; i--) {
            ll.removeView(findViewById(i));
        }

        int placeHolder[] = new int[database.size()];
        for (int i = 0; i < placeHolder.length; i++)
            placeHolder[i] = i;

        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (int j = 0; j < placeHolder.length - 1; j++) {
                if (Integer.parseInt(database.elementAt(placeHolder[j]).elementAt(2)) < Integer.parseInt(database.elementAt(placeHolder[j + 1]).elementAt(2)))   // change to > for ascending sort
                {
                    temp = placeHolder[j];                //swap elements
                    placeHolder[j] = placeHolder[j + 1];
                    placeHolder[j + 1] = temp;
                    flag = true;              //shows a swap occurred
                }
            }
        }
        if (5 == orderFlag) {
            orderFlag = 6;
            for (int i = 0; i < lls.size(); i++) {
                ll.addView(lls.elementAt(placeHolder[i]));
            }
        } else {
            orderFlag = 5;
            for (int i = (lls.size() - 1); i >= 0; i--) {
                ll.addView(lls.elementAt(placeHolder[i]));
            }
        }

    }

    private void initializeDatabase() {
        database.clear();
        // Get the database
        ListFeed worksheets = null;
        try {
            worksheets = new GetDataAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Fill database from google sheets
        for (ListEntry row : worksheets.getEntries()) {
            Vector<String> data = new Vector<String>();
            for (String tag : row.getCustomElements().getTags()) {
                if (row.getCustomElements().getValue(tag) == null)
                    data.add("NA"); // Checking to see if the cell has something in it
                else
                    data.add(row.getCustomElements().getValue(tag)); // Add the information to the clubs data
            }
            database.add(data); // Add the clubs information to the data list
        }

    }

    private View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int clubID = v.getId(); // The clubs id that was pressed
            Intent intent = new Intent("android.intent.action.ClubPage"); // Change to the clubs page
            intent.putExtra("ClubInformation", database.elementAt(clubID)); // pass the id to the new page
            startActivity(intent);
        }
    };

}
