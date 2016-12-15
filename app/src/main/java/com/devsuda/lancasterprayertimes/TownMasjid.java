package com.devsuda.lancasterprayertimes;

import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devsuda.lancasterprayertimes.R;

public class TownMasjid extends Activity {

	Gui_Manager_Town time_date = new Gui_Manager_Town(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_town_masjid);

		// ------------------

		Db_Manager_Town dba_prayertimes = new Db_Manager_Town(this);
		try {
			dba_prayertimes.create();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			dba_prayertimes.open();
			dba_prayertimes.getWritableDatabase();
		} catch (SQLException sqle) {
			throw sqle;
		}

		// ------------------

		int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Cursor c = dba_prayertimes.getPrayersTimeOnDay(dayOfMonth);

		time_date.get_time_diff(c, dba_prayertimes);

		c.close();

		dba_prayertimes.close();
	}

	public void gotoFacebookApp(View view) {
		if (isConnectedToNetwork() == true) {
			if (isDataAvialble() == true) {
				gotoApp(1);
			}
		} else {
			Toast.makeText(this, "Sorry, there is no network connectivity",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void gotoLancasterMasjidApp(View view) {
		gotoApp(2);
	}

	private boolean isConnectedToNetwork() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	private boolean isDataAvialble() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
		NetworkInfo MobileNwInfo = cm
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiNwInfo = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return ((MobileNwInfo == null ? false : MobileNwInfo.isConnected()) || (wifiNwInfo == null ? false
				: wifiNwInfo.isConnected()));
	}

	private boolean appInstalledOrNot(String uri) {
		PackageManager pm = getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	public void gotoApp(int appChoice) {

		boolean isFbInstalled = appInstalledOrNot("com.facebook.katana");
		boolean isLancsMasjidInstalled = appInstalledOrNot("com.devsuda.luprayertimes");

		if (appChoice == 1) {
			if (isFbInstalled) {
				Toast.makeText(this, "Openning Facebook App",
						Toast.LENGTH_SHORT).show();

				try {
					Intent intent = new Intent(Intent.ACTION_VIEW,
							Uri.parse("fb://page/121498887937290"));
					startActivity(intent);
				} catch (Exception e) {
					startActivity(new Intent(Intent.ACTION_VIEW,
							Uri.parse("fb://page/121498887937290")));
				}

			} else {
				Toast.makeText(this, "Openning Facebook on Browser",
						Toast.LENGTH_SHORT).show();
				String url = "https://www.facebook.com/LancsIsoc";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}

		} else if (appChoice == 2) {
			if (isLancsMasjidInstalled) {
				Intent LaunchIntent = getPackageManager()
						.getLaunchIntentForPackage("com.devsuda.luprayertimes");
				startActivity(LaunchIntent);
			} else {
				installApp();
			}
		}

	}

	public void installApp() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog
				.setMessage("\nLancaster Uni App isn't installed...\nDo you want to install it now?");
		alertDialog.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							startActivity(new Intent(Intent.ACTION_VIEW, Uri
									.parse("market://details?id="
											+ "com.devsuda.luprayertimes")));
						} catch (android.content.ActivityNotFoundException anfe) {
							startActivity(new Intent(
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

	@Override
	public void onResume() {
		super.onResume();
		setContentView(R.layout.activity_town_masjid);

		// ======================================
		Db_Manager_Town dba_prayertimes = new Db_Manager_Town(this);
		try {
			dba_prayertimes.create();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			dba_prayertimes.open();
			dba_prayertimes.getWritableDatabase();
		} catch (SQLException sqle) {
			throw sqle;
		}

		// ------------------

		int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Cursor c = dba_prayertimes.getPrayersTimeOnDay(dayOfMonth);

		time_date.get_time_diff(c, dba_prayertimes);

		c.close();

		dba_prayertimes.close();

		// ======================================

	}

}
