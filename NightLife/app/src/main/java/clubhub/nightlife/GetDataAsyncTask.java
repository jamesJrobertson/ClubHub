package clubhub.nightlife;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Created by James R on 2016-05-12.
 */
public class GetDataAsyncTask extends AsyncTask<Void, Void, ListFeed>
{
    ListFeed listFeed;
    protected ListFeed doInBackground(Void... params){
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return listFeed;
    }
    private void getData() throws AuthenticationException, MalformedURLException, IOException, ServiceException {
        SpreadsheetService service =
                new SpreadsheetService("MySpreadsheetIntegration-v1");
        service.setProtocolVersion(SpreadsheetService.Versions.V3);
        // TODO: Authorize the service object for a specific user (see other sections)

        // Define the URL to request.  This should never change.
        URL SPREADSHEET_FEED_URL = new URL(
                "https://spreadsheets.google.com/feeds/worksheets/12aVMIIXrZN86LJc2JS1jdJxCUczym3GcvH1-dxidw9g/public/full");

        WorksheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, WorksheetFeed.class);
        List<WorksheetEntry> worksheets = feed.getEntries();

        WorksheetEntry worksheet = worksheets.get(0);

        Log.d("DATA", "Worksheet name " + worksheet.getTitle().getPlainText());
        URL listFeedUrl = new URL(worksheet.getListFeedUrl().toString());
        Log.d("DATA", "URL is " + listFeedUrl.toString());
        listFeed = service.getFeed(listFeedUrl, ListFeed.class);

    }

}
