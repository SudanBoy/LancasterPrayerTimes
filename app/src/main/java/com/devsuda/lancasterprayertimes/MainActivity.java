package com.devsuda.lancasterprayertimes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final int LANCS_UNI_APP_ID = 1;

    private ExtAppsCaller extAppsIntf;
    private DatabaseAdaptor databaseAdaptor;
    private DisplayPrayerTimes displayPrayerTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAdaptor = new DatabaseAdaptor(this);
        databaseAdaptor.prepareDatabase();

        displayPrayerTimes = new DisplayPrayerTimes(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        databaseAdaptor.prepareDatabase();
    }

    public void gotoLancasterMasjidApp(View view) {
        extAppsIntf = new ExtAppsCaller(this);
        extAppsIntf.gotoApp(LANCS_UNI_APP_ID);
    }
}
