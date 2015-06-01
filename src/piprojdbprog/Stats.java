/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piprojdbprog;

import java.sql.SQLException;

/**
 * This class is for averaging stats: both overall and daily averages
 *
 * @author Brendan
 */
public class Stats {

    //----------------------Overall Averages------------------------------------
    /**
     *
     * @param db Just hand in whatever DB_Controller() object the calling class
     * is using
     * @return the average Pressure across the WHOLE database (in Pa).
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public float PressureOverallAvg(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] pressArr = db.dbSearch("Press");
            int sum = 0;
            float[] fArr = new float[pressArr.length];
            for (int i = 0; i < pressArr.length; i++) {

                fArr[i] = Float.parseFloat(pressArr[i]);
                //System.out.println(i +" " + fArr[i]);

            }
            for (int i = 0; i < fArr.length; i++) {
                sum += fArr[i];

            }

            return sum / (float) fArr.length;

        } catch (Exception e) {
            System.err.println(" an error has occured in Stats#PressureOverallAvg");
            return 0;
        }
    }

    /**
     *
     * @param db Just hand in whatever DB_Controller() object the calling class
     * is using
     * @return the average temperature across the Whole database (in F).
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public float TempOverallAvg(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] temArr = db.dbSearch("Temp");
            int sum = 0;
            float[] tArr = new float[temArr.length];
            for (int i = 0; i < temArr.length; i++) {

                tArr[i] = Float.parseFloat(temArr[i]);
                //System.out.println(i +" " + fArr[i]);

            }
            for (int i = 0; i < tArr.length; i++) {
                sum += tArr[i];

            }

            return sum / (float) tArr.length;

        } catch (Exception e) {
            System.err.println(" an error has occured in Stats#TempOverallAvg");
            return 0;
        }
    }

    /**
     *
     * @param db Just hand in whatever DB_Controller() object the calling class
     * is using
     * @return the average humidity across the whole database (in %).
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public float HumOverallAvg(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] humArr = db.dbSearch("Humid");
            int sum = 0;
            float[] hArr = new float[humArr.length];
            for (int i = 0; i < humArr.length; i++) {

                hArr[i] = Float.parseFloat(humArr[i]);
                //System.out.println(i +" " + fArr[i]);

            }
            for (int i = 0; i < hArr.length; i++) {
                sum += hArr[i];

            }

            return sum / (float) hArr.length;

        } catch (Exception e) {
            System.err.println(" an error has occured in Stats#TempOverallAvg");
            return 0;
        }
    }

    /**
     *
     * @param db Just hand in whatever DB_Controller() object the calling class
     * is using
     * @return the average dew point across the whole database (in F).
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public float DewOverallAvg(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] dewArr = db.dbSearch("Dew_point");
            int sum = 0;
            float[] dArr = new float[dewArr.length];
            for (int i = 0; i < dewArr.length; i++) {

                dArr[i] = Float.parseFloat(dewArr[i]);

            }
            for (int i = 0; i < dArr.length; i++) {
                sum += dArr[i];

            }

            return sum / (float) dArr.length;

        } catch (Exception e) {
            System.err.println(" an error has occured in Stats#TempOverallAvg");
            return 0;
        }
    }

    //-----------------------Daily Averages-------------------------------------
    //=============Pressure=========
    public float dailyPresAvg(float[] readings) {
        float sum = 0;
        for (int i = 0; i < readings.length; i++) {
            sum += readings[i];

        }
        sum /= readings.length;

        return sum;
    }

    //============Temp==============
    public float dailyTempAvg(float[] readings) {
        float sum = 0;
        for (int i = 0; i < readings.length; i++) {
            sum += readings[i];

        }
        sum /= readings.length;

        return sum;
    }

    //===========Humid==============
    public float dailyHumAvg(float[] readings) {
        float sum = 0;
        for (int i = 0; i < readings.length; i++) {
            sum += readings[i];

        }
        sum /= readings.length;

        return sum;
    }

    //===========Dew pt==============
    public float dailyDewAvg(float[] readings) {
        float sum = 0;
        for (int i = 0; i < readings.length; i++) {
            sum += readings[i];

        }
        sum /= readings.length;

        return sum;
    }

    //-----------------------Monthly Averages-----------------------------------
    // ====================these are stretch goals==============================
    //===========Pressure===========
    //======Temp===================
    //=========humid================
    //========dew pt================
    //-----------------------Overall high/low-----------------------------------
    //===========Pressure===========
    public float[] OverallHighLowPressure(DB_Controller db) throws ClassNotFoundException, SQLException {

        float[] result = new float[2];// 0 will be low/ 1 is high
        String[] rawDat = db.dbSearch("Press");

        float[] data = new float[rawDat.length];
        for (int i = 0; i < rawDat.length; i++) {
            data[i] = Float.parseFloat(rawDat[i]);
        }
        float min = minimum(data);
        float max = maximum(data);
        //System.out.println(max);
        result[0] = min;
        result[1] = max;
        return result;
    }

    //======Temp====================
    public float[] OverallHighLowTemp(DB_Controller db) throws ClassNotFoundException, SQLException {

        float[] result = new float[2];// 0 will be low/ 1 is high
        String[] rawDat = db.dbSearch("Temp");

        float[] data = new float[rawDat.length];
        for (int i = 0; i < rawDat.length; i++) {
            data[i] = Float.parseFloat(rawDat[i]);
        }
        float min = minimum(data);
        float max = maximum(data);
        result[0] = min;
        result[1] = max;
        return result;
    }

    //=========humid================
    public float[] OverallHighLowHumid(DB_Controller db) throws ClassNotFoundException, SQLException {

        float[] result = new float[2];// 0 will be low/ 1 is high
        String[] rawDat = db.dbSearch("Humid");

        float[] data = new float[rawDat.length];
        for (int i = 0; i < rawDat.length; i++) {
            data[i] = Float.parseFloat(rawDat[i]);
        }
        float min = minimum(data);
        float max = maximum(data);
        result[0] = min;
        result[1] = max;
        return result;
    }

    //=========Dew pt===============
    public float[] OverallHighLowDew(DB_Controller db) throws ClassNotFoundException, SQLException {

        float[] result = new float[2];// 0 will be low/ 1 is high
        String[] rawDat = db.dbSearch("Dew_point");

        float[] data = new float[rawDat.length];
        for (int i = 0; i < rawDat.length; i++) {
            data[i] = Float.parseFloat(rawDat[i]);
        }
        float min = minimum(data);
        float max = maximum(data);
        result[0] = min;
        result[1] = max;
        return result;
    }

    //-----------------------daily high/low-------------------------------------
    //===========Pressure===========
    public float[] DailyHiLoPressure(float[] readings) {

        float min = minimum(readings);
        float max = maximum(readings);
        if (min != Float.MAX_VALUE && max != Float.MIN_VALUE) {
            float[] ret = {min, max};
            return ret;
        }

        return null;
    }

    //======Temp===================
    public float[] DailyHiLoTemp(float[] readings) {

        float min = minimum(readings);
        float max = maximum(readings);
        if (min != Float.MAX_VALUE && max != Float.MIN_VALUE) {
            float[] ret = {min, max};
            return ret;
        }

        return null;
    }

    //=========humid================
    public float[] DailyHiLoHum(float[] readings) {

        float min = minimum(readings);
        float max = maximum(readings);
        if (min != Float.MAX_VALUE && max != Float.MIN_VALUE) {
            float[] ret = {min, max};
            return ret;
        }

        return null;
    }

    //=========Dew pt===============
    public float[] DailyHiLoDew(float[] readings) {

        float min = minimum(readings);
        float max = maximum(readings);
        if (min != Float.MAX_VALUE && max != Float.MIN_VALUE) {
            float[] ret = {min, max};
            return ret;
        }

        return null;
    }

    //-----------------------monthly high/low-----------------------------------
    // ====================these are stretch goals==============================
    //===========Pressure===========
    //======Temp===================
    //=========humid================
    //=========Dew pt===============
    //-----------------------helper method(s)-----------------------------------
    public void DailyHiLoFiller(DB_Controller db) throws SQLException, ClassNotFoundException {
        try {
            String[] days = db.DistDays();
            for (int i = 0; i < days.length; i++) {
                float[] dailyP = db.GetDailyData("press", days[i]);
                float[] dailyT = db.GetDailyData("temp", days[i]);
                float[] dailyH = db.GetDailyData("humid", days[i]);
                float[] dailyD = db.GetDailyData("dew_point", days[i]);
                float[] P = DailyHiLoPressure(dailyP);
                float[] T = DailyHiLoTemp(dailyT);
                float[] H = DailyHiLoHum(dailyH);
                float[] D = DailyHiLoDew(dailyD);
                db.HiLoIns(days[i], H, T, P, D);

                //System.out.println(D[0] + " " + D[1]);
            }

        } catch (Exception e) {

        }
    }

    /**
     * This sets up the data in the daily averages database
     *
     * @param db
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void DailyAvgFiller(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] days = db.DistDays();

            for (int i = 0; i < days.length; i++) {
                //   System.out.println(days[i]);
                float[] dailyP = db.GetDailyData("press", days[i]);
                float[] dailyT = db.GetDailyData("Temp", days[i]);
                float[] dailyH = db.GetDailyData("humid", days[i]);
                float[] dailyD = db.GetDailyData("dew_point", days[i]);
                float P = dailyPresAvg(dailyP);
                float T = dailyTempAvg(dailyT);
                float H = dailyHumAvg(dailyH);
                float D = dailyDewAvg(dailyD);
                db.AvgDBIns(days[i], H, T, P, D);

            }
        } catch (Exception e) {
            System.err.println("AN ERROR HAS OCCURED: ALL YOUR BASE ARE BELONG TO US!");
        }

    }

    public void OverallAvgs(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            System.out.println();
            System.out.println("=======Overall Averages=========");
            System.out.println("Average Pressure (overall): " + PressureOverallAvg(db));
            System.out.println("Average Temp (overall): " + TempOverallAvg(db));
            System.out.println("Average Hum (overall): " + HumOverallAvg(db));
            System.out.println("Average Dew Point (overall): " + DewOverallAvg(db));
            System.out.println();
        } catch (Exception e) {
            System.err.println("An Error has occured in Stats#OverallAvgs");
        }
    }

    public void OverallHiLow(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            System.out.println();
            System.out.println("=====Overall High/Low Values===========");
            float[] press = OverallHighLowPressure(db);
            System.out.println("------Pressure--------");
            System.out.println("Overall High Pressure: " + press[1]);
            System.out.println("Overall Low Pressure: " + press[0]);
            float[] temp = OverallHighLowTemp(db);
            System.out.println("------Temperature--------");
            System.out.println("Overall High Temp: " + temp[1]);
            System.out.println("Overall Low Temp: " + temp[0]);
            float[] hum = OverallHighLowHumid(db);
            System.out.println("------Humidity--------");
            System.out.println("Overall High Humidity: " + hum[1]);
            System.out.println("Overall Low Humidity: " + hum[0]);
            float[] dew = OverallHighLowDew(db);
            System.out.println("------Dew Point--------");
            System.out.println("Overall High Dew Point: " + dew[1]);
            System.out.println("Overall Low Dew Point: " + dew[0]);
            System.out.println();

        } catch (Exception e) {
            System.err.println("An error has occurred in Stats#OverallHiLow");

        }

    }

    private float minimum(float[] values) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (min > values[i]) {
                min = values[i];
            }

        }
        return min;
    }

    private float maximum(float[] values) {
        float max = Float.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (max < values[i]) {
                max = values[i];
            }

        }
        return max;
    }

}
