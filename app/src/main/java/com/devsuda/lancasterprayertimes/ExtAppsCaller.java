package com.devsuda.lancasterprayertimes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class ExtAppsCaller {

    private static final String LANCS_UNI_APP_URI = "com.devsuda.luprayertimes";
    private static final String GOOGLE_MAP_APP_URI = "com.devsuda.luprayertimes";

    private Context context;
    private PackageManager packageManager;
    private boolean appInstalled;
    private AlertDialog.Builder alertDialog;

    public ExtAppsCaller(MainActivity _mainActivity) {
        this.context = _mainActivity;
    }

    public void gotoApp(int appId) {

        switch (appId) {
            case 1:
                if (isAppInstalled(LANCS_UNI_APP_URI)) {
                    Intent LaunchIntent = context.getPackageManager()
                            .getLaunchIntentForPackage("com.devsuda.luprayertimes");
                    context.startActivity(LaunchIntent);
                } else {
                    actionToInstallApp(appId);
                }
                break;
            case 2:
                if (isAppInstalled(GOOGLE_MAP_APP_URI)) {
                    Intent LaunchIntent = context.getPackageManager()
                            .getLaunchIntentForPackage("com.google.android.apps.maps");
                    context.startActivity(LaunchIntent);
                } else {
                    actionToInstallApp(appId);
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

    public void actionToInstallApp(int appId) {
        alertDialog = new AlertDialog.Builder(context);


        switch (appId) {
            case 1:
                alertDialog.setMessage("\nLancaster Uni App isn't installed...\nDo you want to install it now?");

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
                break;
            case 2:
                String uri = "https://www.google.co.uk/maps/place/54%C2%B002'53.7%22N+2%C2%B048'15.2%22W/@54.048243,-2.8051101,260m/data=!3m2!1e3!4b1!4m5!3m4!1s0x0:0x0!8m2!3d54.048243!4d-2.804207";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);

                alertDialog.show();

                break;
        }

    }
}
