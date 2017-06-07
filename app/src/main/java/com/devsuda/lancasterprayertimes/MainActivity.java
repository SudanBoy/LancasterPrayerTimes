package com.devsuda.lancasterprayertimes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final int LANCS_UNI_APP_ID = 1;

    private ExtAppsCaller extAppsIntf;
    private DatabaseAdaptor databaseAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_masjid);

        databaseAdaptor = new DatabaseAdaptor(this);

        databaseAdaptor.prepareDatabase();
    }

    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_town_masjid);

        databaseAdaptor.prepareDatabase();
    }

    public void gotoLancasterMasjidApp(View view) {
        extAppsIntf = new ExtAppsCaller(this);
        extAppsIntf.gotoApp(LANCS_UNI_APP_ID);
    }
}
