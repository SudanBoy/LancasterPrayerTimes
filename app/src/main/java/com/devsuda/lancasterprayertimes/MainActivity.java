package com.devsuda.lancasterprayertimes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final int LANCS_UNI_APP_ID = 1;
    private static final int GOOGLE_MAP_APP_ID = 2;

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

//        extAppsIntf.isAppInstalled("com.google.android.apps.map");
//        String uri = "https://www.google.co.uk/maps/place/54%C2%B002'53.7%22N+2%C2%B048'15.2%22W/@54.048243,-2.8051101,260m/data=!3m2!1e3!4b1!4m5!3m4!1s0x0:0x0!8m2!3d54.048243!4d-2.804207";
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        startActivity(intent);
    }
}
