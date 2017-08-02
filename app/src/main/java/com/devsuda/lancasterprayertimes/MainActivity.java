package com.devsuda.lancasterprayertimes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final int LANCS_UNI_APP_ID = 3;
    private static final int GOOGLE_MAP_APP_ID = 1;
    private static final int MASJID_WEBSITE = 2;

    private ExtAppsCaller extAppsIntf;
    private DatabaseAdaptor databaseAdaptor;
    private DisplayPrayerTimes displayPrayerTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAdaptor = new DatabaseAdaptor(this);
        databaseAdaptor.prepareDatabase();
        extAppsIntf = new ExtAppsCaller(this);

        displayPrayerTimes = new DisplayPrayerTimes(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        databaseAdaptor.prepareDatabase();
    }

    public void gotoLancasterMasjidApp(View view) {
        extAppsIntf.gotoApp(LANCS_UNI_APP_ID);
    }

    public void gotoMasjidLocation(View view) {
        extAppsIntf.gotoApp(GOOGLE_MAP_APP_ID);
    }

    public void gotoMasjidWebsite(View view) {
        extAppsIntf.gotoApp(MASJID_WEBSITE);
    }
}
