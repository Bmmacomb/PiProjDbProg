/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piprojdbprog;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Brendan
 */
public class PiProjDbProg {

    public static DB_Controller db = new DB_Controller();
    public static FileParser file = new FileParser();
    public static Stats stat = new Stats();
    public static FileOutput OFile = new FileOutput();

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException, Exception {
     // Initalize all classes   

        // Database creation   
        float[] dataline = new float[3];
        file.popData(args[0]);
        db.createDB(0);
        int insCnt = 0;
        for (int i = 0; i < file.getSizeofFile(); i++) {
            dataline = file.dataParse(i);
            db.DBIns(dataline, file.time[i], file.date[i]);
            insCnt = i + 1;
        }
        System.out.println("Status: WeatherData now contains " + insCnt + " entries");
        // Statistics
        stat.DailyAvgFiller(db);
        stat.DailyHiLoFiller(db);
        stat.OverallAvgs(db);
        stat.OverallHiLow(db);
        // create xml out-files UNFINISHED
        OFile.DumpAllToXML(db);
        // TODO code application logic here
    }

}
