package clubhub.nightspy;

import android.os.AsyncTask;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

        // Define the URL to request.  This should never change.

        // Real database
        // URL SPREADSHEET_FEED_URL = new URL(
        //        "https://spreadsheets.google.com/feeds/worksheets/12aVMIIXrZN86LJc2JS1jdJxCUczym3GcvH1-dxidw9g/public/full");

        // Fake Database
        URL SPREADSHEET_FEED_URL = new URL(
                "https://spreadsheets.google.com/feeds/worksheets/1TF8G6RgfFQQNkrdw05ZVxX7XMGx3h7sxxsaLfRGxzHE/public/full");


        WorksheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, WorksheetFeed.class);
        List<WorksheetEntry> worksheets = feed.getEntries();
        WorksheetEntry worksheet = worksheets.get(0);
        URL listFeedUrl = new URL(worksheet.getListFeedUrl().toString());
        listFeed = service.getFeed(listFeedUrl, ListFeed.class);

    }

}
