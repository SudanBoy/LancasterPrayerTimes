package com.devsuda.lancasterprayertimes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Locale;

public class ExtAppsCaller {

    private static final String LANCS_UNI_APP_URI = "com.devsuda.luprayertimes";
    private static final String GOOGLE_MAP_APP_URI = "com.google.android.apps.maps";
    private static final String FACEBOOK_APP_ID = "com.facebook.katana";


    private Context context;
    private PackageManager packageManager;
    private boolean appInstalled;
    private AlertDialog.Builder alertDialog;
    private LocationHelper locationAdaptor;
    double currentLocLat;
    double currentLocLon;
    double prayerRoomLat = 54.048243;
    double prayerRoomLon = -2.8051101;

    public ExtAppsCaller(MainActivity _mainActivity) {
        this.context = _mainActivity;
        locationAdaptor = new LocationHelper(_mainActivity);
    }

    public void gotoApp(int appId) {

        switch (appId) {
            case 1:
                if (isAppInstalled(GOOGLE_MAP_APP_URI)) {

                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", prayerRoomLat, prayerRoomLon);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);

                    intent.setPackage("com.google.android.apps.maps");

                    context.startActivity(intent);

                } else {
                    String uri = "https://www.google.co.uk/maps/place/54%C2%B002'53.7%22N+2%C2%B048'15.2%22W/@54.04825,-2.8045786,19z/data=!4m5!3m4!1s0x0:0x0!8m2!3d54.04825!4d-2.8042222";

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);

                    alertDialog.show();
                }
                break;

            case 2:

                Toast.makeText(context, "Openning a browser", Toast.LENGTH_SHORT).show();
                String url = "http://www.lancasterislamicsociety.co.uk/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);

                break;

            case 3:

                if (isAppInstalled(LANCS_UNI_APP_URI)) {
                    Intent LaunchIntent = context.getPackageManager()
                            .getLaunchIntentForPackage("com.devsuda.luprayertimes");
                    context.startActivity(LaunchIntent);
                } else {
                    alertDialog.setMessage("\nLancaster Univesrity Prayer-Times App isn't installed...\nDo you want to install it now?");

                    alertDialog.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                                                .parse("market://details?id="
                                                        + "com.devsuda.luprayertimes")));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        context.startActivity(new Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse("http://play.google.com/store/apps/details?id"
                                                        + "com.devsuda.luprayertimes")));
                                    }
                                }
                            });

                    alertDialog.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                    alertDialog.show();
                }
                break;
        }
    }

    public boolean isAppInstalled(String uri) {
        packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            appInstalled = false;
        }
        return appInstalled;
    }

    private boolean isLocationEnabled() {
        boolean locationEnabled = false;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("GPS Not Found");  // GPS not found
            builder.setMessage("Want to ernable"); // Want to enable?
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setNegativeButton("No", null);
            builder.create().show();
        } else {
            locationEnabled = true;
        }
        return locationEnabled;
    }
}
