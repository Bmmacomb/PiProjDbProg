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

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
     // Initalize all classes   
        DB_Controller db = new DB_Controller();
        FileParser file = new FileParser();
        Stats stat = new Stats();
        FileOutput OFile = new FileOutput(db, "outFile");
     // Database creation   
        float[] dataline = new float[3];
        file.popData();
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
        stat.OverallHighLowTemp(db);
        stat.OverallHiLow(db);
        // create xml out-files UNFINISHED
        OFile.XMLConverterMainDB(db);

        // TODO code application logic here
    }

}
