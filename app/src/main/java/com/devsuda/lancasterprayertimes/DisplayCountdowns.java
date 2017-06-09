package com.devsuda.lancasterprayertimes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

        final TextView catchUpTimeTitle = (TextView) mainActivity.findViewById(R.id.catchUpTimeTitle);

        final TextView catchUpTimeH1 = (TextView) mainActivity.findViewById(R.id.catchUpTimeH1);
        final TextView catchUpTimeM1 = (TextView) mainActivity.findViewById(R.id.catchUpTimeM1);
        final TextView catchUpTimeS1 = (TextView) mainActivity.findViewById(R.id.catchUpTimeS1);

        final TextView catchUpTimeH2 = (TextView) mainActivity.findViewById(R.id.catchUpTimeH2);
        final TextView catchUpTimeM2 = (TextView) mainActivity.findViewById(R.id.catchUpTimeM2);
        final TextView catchUpTimeS2 = (TextView) mainActivity.findViewById(R.id.catchUpTimeS2);

        final TextView azanCountdown_lbl1 = (TextView) mainActivity.findViewById(R.id.azanCountdown_lbl1);
        final TextView azanCountdownH1 = (TextView) mainActivity.findViewById(R.id.azanCountdownH1);
        final TextView azanCountdownH2 = (TextView) mainActivity.findViewById(R.id.azanCountdownH2);

        final TextView azanCountdownM1 = (TextView) mainActivity.findViewById(R.id.azanCountdownM1);
        final TextView azanCountdownM2 = (TextView) mainActivity.findViewById(R.id.azanCountdownM2);

        final TextView azanCountdownS1 = (TextView) mainActivity.findViewById(R.id.azanCountdownS1);
        final TextView azanCountdownS2 = (TextView) mainActivity.findViewById(R.id.azanCountdownS2);

        final TextView azanCountdown_lbl2 = (TextView) mainActivity.findViewById(R.id.azanCountdown_lbl2);

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

                    String hmsH = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    if (hmsH.equals("00")) {
                        azanCountdown_lbl1.setText("You've only  ");
                        azanCountdownH2.setText("");
                        String hmsM = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                        if (hmsM.equals("00")) {
                            azanCountdownM1.setText("");
                            azanCountdownM2.setText("");
                        } else {
                            azanCountdownM1.setText(hmsM);
                            azanCountdownM2.setText(" Min\t");
                        }

                        String hmsS = String.format("%02d", (millisUntilFinished / 1000) % 60);

                        azanCountdownS1.setText(hmsS);
                        azanCountdownS2.setText(" Sec\t");
                        azanCountdown_lbl2.setText("to Azan");

                    } else {
                        azanCountdown_lbl1.setText("Relax, Azan ");

                        azanCountdownH1.setText("");
                        azanCountdownH2.setText("");
                        azanCountdownM1.setText("");
                        azanCountdownM2.setText("");
                        azanCountdownS1.setText("");
                        azanCountdownS2.setText("");

                        azanCountdown_lbl2.setText("not yet called");
                    }
                }

                public void onFinish() {

                    showNotification(azanNotiId);

                    azanCountdown_lbl1.setText("");
                    azanCountdownH1.setText("Get ");
                    azanCountdownH2.setText("");
                    azanCountdownM1.setText("READY ");
                    azanCountdownM2.setText("");
                    azanCountdownS1.setText("Now");
                    azanCountdownS2.setText("");
                    azanCountdown_lbl2.setText("");
                }
            }.start();

        } else if (countDownId == igamaCountDownId) {
            new CountDownTimer(timeToNextIgamaِ, countDownInterval) {

                public void onTick(long millisUntilFinished) {

                    String hmsH = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    catchUpTimeH1.setText("\t" + hmsH);
                    String hmsM = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                    catchUpTimeM1.setText(hmsM);
                    String hmsS = String.format("%02d", (millisUntilFinished / 1000) % 60);
                    catchUpTimeS1.setText(hmsS);
                }

                public void onFinish() {

                    showNotification(igamaNotiId);

                    catchUpTimeTitle.setText("Hurry up");
                    catchUpTimeH1.setText("Jamaa has started");
                    catchUpTimeH2.setText("");
                    catchUpTimeM1.setText("");
                    catchUpTimeM2.setText("");
                    catchUpTimeS1.setText("");
                    catchUpTimeS2.setText("");

                    azanCountdown_lbl1.setText("GO ");
                    azanCountdownH1.setText("GO ");
                    azanCountdownH1.setTextColor(Color.parseColor("#2c3e50"));
                    azanCountdownH2.setText("");
                    azanCountdownM1.setText("");
                    azanCountdownM2.setText("");
                    azanCountdownS2.setText("");
                    azanCountdown_lbl2.setText("GO");
                }
            }.start();

        } else if (countDownId == maghribCountDownId) {
            new CountDownTimer(timeToMagribِ, countDownInterval) {

                public void onTick(long millisUntilFinished) {

                    String hmsH = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    if (hmsH.equals("00")) {

                        azanCountdown_lbl1.setText("You've only  ");

                        azanCountdownH2.setText("");

                        String hmsM = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                        if (hmsM.equals("00")) {
                            azanCountdownM1.setText("");
                            azanCountdownM2.setText("");
                        } else {
                            azanCountdownM1.setText(hmsM);
                            azanCountdownM2.setText(" Min\t");
                        }

                        String hmsS = String.format("%02d", (millisUntilFinished / 1000) % 60);
                        azanCountdownS1.setText(hmsS);
                        azanCountdownS2.setText(" Sec\t");

                        azanCountdown_lbl2.setText("to Igama");

                    } else {
                        azanCountdown_lbl1.setText("Relax, Azan ");

                        azanCountdownH1.setText("");
                        azanCountdownH2.setText("");
                        azanCountdownM1.setText("");
                        azanCountdownM2.setText("");
                        azanCountdownS1.setText("");
                        azanCountdownS2.setText("");

                        azanCountdown_lbl2.setText("not yet called");
                    }

                }

                public void onFinish() {

                    showNotification(igamaNotiId);

                    catchUpTimeTitle.setText("Hurry up");
                    catchUpTimeH1.setText("Jamaa has started");
                    catchUpTimeH2.setText("");
                    catchUpTimeM1.setText("");
                    catchUpTimeM2.setText("");
                    catchUpTimeS1.setText("");
                    catchUpTimeS2.setText("");

                    azanCountdown_lbl1.setText("GO ");
                    azanCountdownH1.setText("GO ");
                    azanCountdownH1.setTextColor(Color.parseColor("#2c3e50"));
                    azanCountdownH2.setText("");
                    azanCountdownM1.setText("");
                    azanCountdownM2.setText("");
                    azanCountdownS2.setText("");
                    azanCountdown_lbl2.setText("GO");
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
                myNotification = new Notification(R.drawable.ic_launcher, "Azan is calling!", System.currentTimeMillis());
                notificationTitle = "Azan is calling";
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
