package com.devsuda.lancasterprayertimes;

import android.database.Cursor;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class GuiInterface {

    public MainActivity mainActivity;
    private DateTimeAdaptor dateTimeAdaptor;

    public GuiInterface(MainActivity _mainActivity) {
        this.mainActivity = _mainActivity;
        this.dateTimeAdaptor = new DateTimeAdaptor();
    }

    public void countdown(long timeDiffInMs, int whichCountDown) {

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

        if (whichCountDown == 1) { // Igama countdown for all prayers except
            // Maghrib
            new CountDownTimer(timeDiffInMs, 1000) {

                public void onTick(long millisUntilFinished) {

                    String hmsH = String.format("%02d", (millisUntilFinished / (60 * 60 * 1000)) % 24);
                    catchUpTimeH1.setText("\t" + hmsH);
                    String hmsM = String.format("%02d", (millisUntilFinished / (60 * 1000)) % 60);
                    catchUpTimeM1.setText(hmsM);
                    String hmsS = String.format("%02d", (millisUntilFinished / 1000) % 60);
                    catchUpTimeS1.setText(hmsS);
                }

                public void onFinish() {
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

        } else if (whichCountDown == 3) { // Maghrib countdown
            new CountDownTimer(timeDiffInMs, 1000) {

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

        } else if (whichCountDown == 2) { // Azan countdown
            new CountDownTimer(timeDiffInMs, 1000) {

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

                        String hmsS = String.format("%02d",
                                (millisUntilFinished / 1000) % 60);

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

        }

    }

    public void get_time_diff(Cursor c, DatabaseAdaptor db_2) {

        long fajr_in_ms;
        long zhor_in_ms;
        long asor_in_ms;
        long magrib_in_ms;
        long isha_in_ms;

        long nextPrayerTimeInMs = 0;
        long nextPrayerAzanTimeInMs = 0;

        long currentTimeInMs;
        long timeDiffInMs;
        long timeDiffInMs2;

        long fajrAzan_in_ms;
        long zhorAzan_in_ms;
        long asorAzan_in_ms;
        long ishaAzan_in_ms;

        Date fajr_time;
        Date zhor_time;
        Date asor_time;
        Date magrib_time;
        Date isha_time;

        Date fajrAzan_time;
        Date zhorAzan_time;
        Date asorAzan_time;
        Date magribAzan_time;
        Date ishaAzan_time;

        Date current_time = new Date();
        Date tomorrow_date = null;

        Calendar calendar_1 = Calendar.getInstance();
        Calendar calendar_2 = Calendar.getInstance();

        String currentDate = current_time.toString();
        String nextDay;

        final TextView DuaaLabel = (TextView) mainActivity.findViewById(R.id.DuaaLabel);

        TextView nextdaylabel = (TextView) mainActivity.findViewById(R.id.myKeys);
        TableLayout nextdaylabel_bg = (TableLayout) mainActivity.findViewById(R.id.t3);

        TextView dateName = (TextView) mainActivity.findViewById(R.id.dateName);
        TextView gregorianTime = (TextView) mainActivity.findViewById(R.id.gregorianTime);

        TextView shehriLbl = (TextView) mainActivity.findViewById(R.id.shehriLbl);
        TextView fajrAzan = (TextView) mainActivity.findViewById(R.id.fajrAzan);
        TextView fajrIgama = (TextView) mainActivity.findViewById(R.id.fajrIgama);
        TextView sunrise = (TextView) mainActivity.findViewById(R.id.sunrise);
        TableLayout fajrBG = (TableLayout) mainActivity.findViewById(R.id.fajrBG);

        TextView zhorAzan = (TextView) mainActivity.findViewById(R.id.zhorAzan);
        TextView zhorTitle = (TextView) mainActivity.findViewById(R.id.zhorTitle);
        TextView zhorIgama = (TextView) mainActivity.findViewById(R.id.zhorIgama);
        TableLayout zhorBG = (TableLayout) mainActivity.findViewById(R.id.zhorBG);

        TextView asorAzanHanafi = (TextView) mainActivity.findViewById(R.id.asorAzanHanafi);
        TextView asorIgama = (TextView) mainActivity.findViewById(R.id.asorIgama);
        TableLayout asorBG = (TableLayout) mainActivity.findViewById(R.id.asorBG);

        TextView magribIgama = (TextView) mainActivity.findViewById(R.id.magribIgama);
        TableLayout magribBG = (TableLayout) mainActivity.findViewById(R.id.magribBG);

        TextView ishaAzan = (TextView) mainActivity.findViewById(R.id.ishaAzan);
        TextView ishaIgama = (TextView) mainActivity.findViewById(R.id.ishaIgama);
        TableLayout ishaBG = (TableLayout) mainActivity.findViewById(R.id.ishaBG);

        Calendar cal1 = Calendar.getInstance();
        int day = cal1.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SATURDAY:
                dateName.setText("Saturday");
                break;
            case Calendar.SUNDAY:
                dateName.setText("Sunday");
                break;
            case Calendar.MONDAY:
                dateName.setText("Monday");
                break;
            case Calendar.TUESDAY:
                dateName.setText("Tuesday");
                break;
            case Calendar.WEDNESDAY:
                dateName.setText("Wednesday");
                break;
            case Calendar.THURSDAY:
                dateName.setText("Thursday");
                break;
            case Calendar.FRIDAY:
                dateName.setText("Friday");
                break;
        }

        gregorianTime.setText(dateTimeAdaptor.dateformat_3().format(new Date()));

        shehriLbl.setText(c.getString(3));
        fajrAzan.setText(c.getString(4));
        fajrIgama.setText(c.getString(5));
        sunrise.setText(c.getString(6));

        zhorAzan.setText(c.getString(7));
        zhorIgama.setText(c.getString(8));

        asorAzanHanafi.setText(c.getString(10));
        asorIgama.setText(c.getString(11));

        magribIgama.setText(c.getString(12));

        ishaAzan.setText(c.getString(13));
        ishaIgama.setText(c.getString(14));

        fajr_in_ms = PrayerRelated.prayerTimeInMs(c, 1, null);
        zhor_in_ms = PrayerRelated.prayerTimeInMs(c, 2, null);
        asor_in_ms = PrayerRelated.prayerTimeInMs(c, 3, null);
        magrib_in_ms = PrayerRelated.prayerTimeInMs(c, 4, null);
        isha_in_ms = PrayerRelated.prayerTimeInMs(c, 5, null);

        fajrAzan_in_ms = PrayerRelated.prayerTimeInMs(c, 7, null);
        zhorAzan_in_ms = PrayerRelated.prayerTimeInMs(c, 8, null);
        asorAzan_in_ms = PrayerRelated.prayerTimeInMs(c, 9, null);
        ishaAzan_in_ms = PrayerRelated.prayerTimeInMs(c, 10, null);

        fajr_time = new Date(fajr_in_ms);
        zhor_time = new Date(zhor_in_ms);
        asor_time = new Date(asor_in_ms);
        magrib_time = new Date(magrib_in_ms);
        isha_time = new Date(isha_in_ms);

        fajrAzan_time = new Date(fajrAzan_in_ms);
        zhorAzan_time = new Date(zhorAzan_in_ms);
        asorAzan_time = new Date(asorAzan_in_ms);
        ishaAzan_time = new Date(ishaAzan_in_ms);

        // ====================================================

        if (current_time.before(fajrAzan_time)) {
            nextPrayerAzanTimeInMs = fajrAzan_in_ms;
        } else if (current_time.before(zhorAzan_time)
                && current_time.after(fajr_time)) {
            nextPrayerAzanTimeInMs = zhorAzan_in_ms;
        } else if (current_time.before(asorAzan_time)
                && current_time.after(zhor_time)) {
            nextPrayerAzanTimeInMs = asorAzan_in_ms;
        } else if (current_time.before(magrib_time)
                && current_time.after(asor_time)) {
            nextPrayerAzanTimeInMs = magrib_in_ms;
        } else if (current_time.before(ishaAzan_time)
                && current_time.after(magrib_time)) {
            nextPrayerAzanTimeInMs = ishaAzan_in_ms;
        } else if (current_time.after(isha_time)) {
            // initialize the calendar with current day
            try {
                calendar_1.setTime(dateTimeAdaptor.dateformat_2().parse(currentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // go to next day in the calendar
            calendar_1.add(Calendar.DATE, 1);

            // Returns the time of this Calendar as a DateTimeAdaptor object.
            nextDay = dateTimeAdaptor.dateformat_2().format(calendar_1.getTime());

            // parse nextDay with format_2 to get the right "date" syntax
            try {
                tomorrow_date = dateTimeAdaptor.dateformat_2().parse(nextDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // set tomorrow's date as toda's date in the calendar
            calendar_2.setTime(tomorrow_date);

            // Query the database
            int dayOfMonth = calendar_2.get(Calendar.DAY_OF_MONTH);
            Cursor a = db_2.getPrayersTimeOnDay(dayOfMonth);

            fajrAzan_in_ms = PrayerRelated.prayerTimeInMs(a, 7, null);
            nextPrayerAzanTimeInMs = fajrAzan_in_ms;
        }

        // ====================================================

        if (current_time.before(fajr_time)) {
            nextPrayerTimeInMs = fajr_in_ms;
            fajrBG.setBackgroundColor(Color.parseColor("#f1c40f"));
        } else if (current_time.before(zhor_time)
                && current_time.after(fajr_time)) {
            if (dateTimeAdaptor.isFriday() == true) {
                zhorBG.setBackgroundColor(Color.parseColor("#f1c40f"));
                nextPrayerTimeInMs = zhor_in_ms;
            } else {
                nextPrayerTimeInMs = zhor_in_ms;
                zhorBG.setBackgroundColor(Color.parseColor("#f1c40f"));
            }
        } else if (current_time.before(asor_time)
                && current_time.after(zhor_time)) {
            nextPrayerTimeInMs = asor_in_ms;
            asorBG.setBackgroundColor(Color.parseColor("#f1c40f"));
        } else if (current_time.before(magrib_time)
                && current_time.after(asor_time)) {
            nextPrayerTimeInMs = magrib_in_ms;
            magribBG.setBackgroundColor(Color.parseColor("#f1c40f"));
        } else if (current_time.before(isha_time)
                && current_time.after(magrib_time)) {
            nextPrayerTimeInMs = isha_in_ms;
            ishaBG.setBackgroundColor(Color.parseColor("#f1c40f"));
        } else if (current_time.after(isha_time)) {

            // initialize the calendar with current day
            try {
                calendar_1.setTime(dateTimeAdaptor.dateformat_2().parse(currentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // go to next day in the calendar
            calendar_1.add(Calendar.DATE, 1);

            // Returns the time of this Calendar as a DateTimeAdaptor object.
            nextDay = dateTimeAdaptor.dateformat_2().format(calendar_1.getTime());

            // parse nextDay with format_2 to get the right "date" syntax
            try {
                tomorrow_date = dateTimeAdaptor.dateformat_2().parse(nextDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // set tomorrow's date as toda's date in the calendar
            calendar_2.setTime(tomorrow_date);

            // Query the database
            int dayOfMonth = calendar_2.get(Calendar.DAY_OF_MONTH);
            Cursor b = db_2.getPrayersTimeOnDay(dayOfMonth);

            // get the next-day's fajr time
            nextPrayerTimeInMs = PrayerRelated.prayerTimeInMs(b, 6, nextDay);

            // =======================================
            DuaaLabel.setText("(All times are NOW set for tomorrow)");

            fajrAzan.setText(b.getString(4));
            fajrIgama.setText(b.getString(5));
            sunrise.setText(b.getString(6));

            zhorAzan.setText(b.getString(7));
            zhorIgama.setText(b.getString(8));

            asorAzanHanafi.setText(b.getString(10));
            asorIgama.setText(b.getString(11));

            magribIgama.setText(b.getString(12));

            ishaAzan.setText(b.getString(13));
            ishaIgama.setText(b.getString(14));

            fajr_in_ms = PrayerRelated.prayerTimeInMs(b, 1, null);
            zhor_in_ms = PrayerRelated.prayerTimeInMs(b, 2, null);
            asor_in_ms = PrayerRelated.prayerTimeInMs(b, 3, null);
            magrib_in_ms = PrayerRelated.prayerTimeInMs(b, 4, null);
            isha_in_ms = PrayerRelated.prayerTimeInMs(b, 5, null);

            fajr_time = new Date(fajr_in_ms);
            zhor_time = new Date(zhor_in_ms);
            asor_time = new Date(asor_in_ms);
            magrib_time = new Date(magrib_in_ms);
            isha_time = new Date(isha_in_ms);

            nextPrayerTimeInMs = fajr_in_ms;
            fajrBG.setBackgroundColor(Color.parseColor("#f1c40f"));

            // =======================================

        }

        currentTimeInMs = Calendar.getInstance().getTimeInMillis();
        timeDiffInMs = nextPrayerTimeInMs - currentTimeInMs;
        timeDiffInMs2 = nextPrayerAzanTimeInMs - currentTimeInMs;

        if (current_time.before(magrib_time) && current_time.after(asor_time)) {
            countdown(timeDiffInMs, 1);
            countdown(timeDiffInMs, 3);
        } else {
            countdown(timeDiffInMs, 1);
            countdown(timeDiffInMs2, 2);
        }

    }

}
