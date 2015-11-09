/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piprojdbprog;

import java.util.*;
import java.io.*;

/**
 * this class is used to parse the data from the datafile into a format conducive to being inserted into the databases
 * @author Brendan
 */
public class FileParser {
    /**
     * the array that holds the data from the file
     */
    public static String[] dataArr;
    /**
     * A temporary list to hold the data until all entries are processed, (arrays are not easy to extend, so we need this)
     */
    public static LinkedList data = new LinkedList<String>();

    /**
     *this is only used in this#averages to hold all the average values (Temp,Hum,DewPT,Press) for return as an array
     */
    public static float[][] dataLine;
    /**
     * the array of time entries from the file
     */
    public static String[] time;
    /**
     * the array of date entries from the file
     */
    public static String[] date;

    /**
     * This method sets up an array of strings from the linked list: this method
     * MUST be used in conjunction with
     * {@link piprojdbprog.FileParser#ParseFile(String filename)}
     */
    public void popData(String fname) {
        ParseFile(fname);

        for (int i = 0; i < data.size(); i++) {
            dataArr[i] = data.get(i).toString();
             //System.out.println(dataArr[i]);
            //System.out.println(dataArr[i]);

            // System.out.println(dataArr.length);
            //System.out.println(data.size());
        }
    }

    /**
     * this method creates a linked list (type String) from a data file, it MUST
     * be the first method used from this class because it sets up several
     * global Varibles, {@link piprojdbprog.FileParser#popData()} MUST be used
     * next
     *
     * @param filename the name of the file to be used; should be "dataFile.txt"
     */
    public void ParseFile(String filename) {

        try {
            //File file = new File("test.txt");
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            int x = 0;

            while ((line = bufferedReader.readLine()) != null) {// first runcount lines
                stringBuffer.append(line);
                stringBuffer.append("\n");
                if (!"".equals(line)) {
                    data.add(line);
                    //System.out.println(line);

                }
                x++;
            }
            fileReader.close();
            //System.out.println("Contents of file:");
            //System.out.println(stringBuffer.toString());
            //System.out.println(x);
            dataArr = new String[x];
            time = new String[x];
            date = new String[x];

            // fileReader = new FileReader("dataFile.txt");
            // bufferedReader = new BufferedReader(fileReader);
            //stringBuffer = new StringBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Preps data for insertion into the database
     * @param index the index of the line to parse
     * @return an array of floats for insertion into the database via
     * {@link piprojdbprog.DB_Controller#DBIns(float[] data, String time, String date)}
     */
    public float[] dataParse(int index) {
        float[] LData = new float[4];
        String d[] = dataArr[index].split(" ");
        //System.out.println(d[3]);
        LData[0] = Float.parseFloat(d[2]);
        LData[1] = Float.parseFloat(d[3]);
        LData[2] = Float.parseFloat(d[4]);
        LData[3] = Float.parseFloat(d[7]);
        time[index] = d[1];
        //System.out.println(d[1]);
        date[index] = d[0];

        //System.out.println(dataArr[0]);
        return LData;
    }

    /**
     * This will return the number of lines in the file
     *
     * @return
     */
    public int getSizeofFile() {
        return dataArr.length;

    }

    /**
     * Debug Method: NOT TO BE USED IN FINAL VERSION, this is for checking the
     * accuracy of averaging methods later on
     *
     * @return
     */
    public float[] GetAverages() {
        int entry;
        float avgTem, avgPress, avgHum;
        float subTem, subPress, subHum;
        subTem = subPress = subHum = 0;
        dataLine = new float[dataArr.length][3];
        for (entry = 0; entry < dataArr.length - 1; entry++) {
            String d[] = dataArr[entry].split(" ");
            // for(int i = 0; i<d.length;i++)
            // System.out.println(d[i]);
            float q1 = Float.parseFloat(d[0]);
            subHum += q1;

            float q2 = Float.parseFloat(d[1]);
            subTem += q2;

            float q3 = Float.parseFloat(d[2]);
            subPress += q3;
            //System.out.printf("ENTRY(%d) humidity: %.2f%% , Temp: %.2f \u00b0f, Pressure: %.2f Pa \n", entry, q1, q2, q3);

        }
        avgTem = subTem / dataArr.length - 1;
        avgHum = subHum / dataArr.length - 1;
        avgPress = subPress / dataArr.length - 1;
        float[] output = new float[3];
        output[0] = avgTem;
        output[1] = avgHum;
        output[2] = avgPress;
        return output;
        //System.out.printf("Averages for given data: Temp: %.2f \u00b0f,  humidity: %.2f%%, Pressure %.2f Pa \n", avgTem, avgHum, avgPress);

    }

}
