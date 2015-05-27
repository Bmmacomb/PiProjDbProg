/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piprojdbprog;

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
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DB_Controller db = new DB_Controller();
        FileParser S = new FileParser();
        Stats stat = new Stats();
        float[] dataline = new float[3];
        S.popData();
        db.createDB(0);
        int insCnt = 0;
        for (int i = 0; i < S.getSizeofFile(); i++) {
            dataline = S.dataParse(i);
            db.DBIns(dataline, S.time[i], S.date[i]);
            insCnt = i + 1;
        }
        System.out.println(insCnt + " Lines added to the database  (Weather/weatherData)");
        stat.DailyAvgFiller(db);
        stat.OverallAvgs(db);
        stat.OverallHighLowTemp(db);
        stat.OverallHiLow(db);
        // TODO code application logic here
        

    }

}
