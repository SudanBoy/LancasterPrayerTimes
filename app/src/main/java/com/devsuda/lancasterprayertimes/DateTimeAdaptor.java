package com.devsuda.lancasterprayertimes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Abubakr on 07/06/2017.
 */

public class DateTimeAdaptor {

    private String tableName = null;
    private int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

    private void setCurrentMonthTableName() {
        switch (currentMonth) {
            case (0):
                tableName = "jan_Prayers";
                break;
            case (1):
                tableName = "feb_Prayers";
                break;
            case (2):
                tableName = "mar_Prayers";
                break;
            case (3):
                tableName = "apr_Prayers";
                break;
            case (4):
                tableName = "may_Prayers";
                break;
            case (5):
                tableName = "june_Prayers";
                break;
            case (6):
                tableName = "july_Prayers";
                break;
            case (7):
                tableName = "aug_Prayers";
                break;
            case (8):
                tableName = "sep_Prayers";
                break;
            case (9):
                tableName = "oct_Prayers";
                break;
            case (10):
                tableName = "nov_Prayers";
                break;
            case (11):
                tableName = "dec_Prayers";
                break;
        }
    }

    public String getCurrentMonthTableName() {
        setCurrentMonthTableName();
        return tableName;
    }

    public boolean isFriday() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 6) {
            return true;
        } else {
            return false;
        }
    }

    public DateFormat dateformat_1() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm");
        return dateFormat;
    }

    public DateFormat dateformat_2() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat;
    }

    public DateFormat dateformat_3() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        return dateFormat;
    }
}
