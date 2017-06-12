package com.devsuda.lancasterprayertimes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.widget.TextView;

public class DisplayCountdowns {

    public MainActivity mainActivity;
    private static final int igamaCountDownId = 1;
    private static final int azanCountDownId = 2;
    private static final int maghribCountDownId = 3;

    public DisplayCountdowns(MainActivity _mainActivity) {
        this.mainActivity = _mainActivity;
    }

    public void countdown(long timeToNextAzanOrIgamaِ, int countDownId) {

        final TextView hourOfIgamaCdTv = (TextView) mainActivity.findViewById(R.id.hourOfIgamaCdTv);
        final TextView minOfIgamaCdTv = (TextView) mainActivity.findViewById(R.id.minOfIgamaCdTv);
        final TextView secOfIgamaCdTv = (TextView) mainActivity.findViewById(R.id.secOfIgamaCdTv);

        final TextView hourOfIgamaCdSign = (TextView) mainActivity.findViewById(R.id.hourOfIgamaCdSign);
        final TextView minOfIgamaCdSign = (TextView) mainActivity.findViewById(R.id.minOfIgamaCdSign);

        final TextView minOfAzanCdTv = (TextView) mainActivity.findViewById(R.id.minOfAzanCdTv);
        final TextView minOfAzanCdSign = (TextView) mainActivity.findViewById(R.id.minOfAzanCdSign);

        long timeToNextAzan = 0;
        long timeToNextIgamaِ = 0;
        long timeToMagribِ = 0;

        final int azanNotiId = 1;
        final int igamaNotiId = 2;

        int countDownInterval = 1000;

        switch (countDownId) {
            case azanCountDownId:
                timeToNextAzan = timeToNextAzanOrIgamaِ;
                break;
            case igamaCountDownId:
                timeToNextIgamaِ = timeToNextAzanOrIgamaِ;
                break;
            case maghribCountDownId:
                timeToMagribِ = timeToNextAzanOrIgamaِ;
                break;
        }

        if (countDownId == azanCountDownId) {
            new CountDownTimer(timeToNextAzan, countDownInterval) {

                public void onTick(long millisUntilFinished) {
                    String hourOfAzanCd = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    if (hourOfAzanCd.equals("00")) {
                        String minOfAzanCd = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                        if (minOfAzanCd.equals("00")) {
                            minOfAzanCdTv.setText("Azan is calling");
                        } else {
                            minOfAzanCdTv.setText(minOfAzanCd);
                            minOfAzanCdSign.setText(" minutes to azan\t");
                        }

                    } else {
                        minOfAzanCdSign.setText("Relax, azan not yet called");
                    }
                }

                public void onFinish() {
                    showNotification(azanNotiId);
                    minOfAzanCdTv.setText("Azan has been called ");
                }
            }.start();

        } else if (countDownId == igamaCountDownId) {
            new CountDownTimer(timeToNextIgamaِ, countDownInterval) {

                public void onTick(long millisUntilFinished) {
                    String hourOfIgamaCd = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    hourOfIgamaCdTv.setText("\t" + hourOfIgamaCd);
                    String minOfIgamaCd = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                    minOfIgamaCdTv.setText(minOfIgamaCd);
                    String secOfIgamaCd = String.format("%02d", (millisUntilFinished / 1000) % 60);
                    secOfIgamaCdTv.setText(secOfIgamaCd);
                }

                public void onFinish() {

                    showNotification(igamaNotiId);
                    minOfAzanCdTv.setText("Jamaa started, hurry up!");
                }
            }.start();

        } else if (countDownId == maghribCountDownId) {
            new CountDownTimer(timeToMagribِ, countDownInterval) {

                public void onTick(long millisUntilFinished) {

                    String hmsH = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    if (hmsH.equals("00")) {

                        String minOfMagribCd = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                        if (minOfMagribCd.equals("00")) {
                            minOfAzanCdTv.setText("Mind the Igama for Magrib");
                        } else {
                            minOfAzanCdTv.setText(minOfMagribCd);
                            minOfAzanCdSign.setText(" minutes to azan\t");
                        }

                    } else {
                        minOfAzanCdSign.setText("Relax, azan not yet called");
                    }
                }

                public void onFinish() {
                    showNotification(igamaNotiId);
                    minOfAzanCdTv.setText("Azan has been called ");
                }
            }.start();
        }
    }

    private void showNotification(int notiId) {
        Notification myNotification = null;
        String notificationTitle = null;
        String notificationBody = null;

        switch (notiId) {
            case 1:
                myNotification = new Notification(R.drawable.ic_launcher, "Time for praying!", System.currentTimeMillis());
                notificationTitle = "Time for praying";
                notificationBody = "Prepare yourself!";

                break;
            case 2:
                myNotification = new Notification(R.drawable.ic_launcher, "Jamaa started!", System.currentTimeMillis());
                notificationTitle = "Jamaa started!";
                notificationBody = "May Allah accept";
                break;
        }
        NotificationManager notificationManager = (NotificationManager) mainActivity.getSystemService(Context.NOTIFICATION_SERVICE);

        Context context = mainActivity.getApplicationContext();


        Intent myIntent = new Intent(mainActivity, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mainActivity, 0, myIntent, Intent.FILL_IN_ACTION);
        myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        myNotification.sound = Uri.parse("android.resource://" + mainActivity.getPackageName() + "/" + R.raw.azan);
        myNotification.setLatestEventInfo(context, notificationTitle, notificationBody, pendingIntent);
        notificationManager.notify(1, myNotification);
    }


}
