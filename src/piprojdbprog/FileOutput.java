/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piprojdbprog;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * this will class will be used to create a XML file to output
 *
 * @author Brendan
 */
public class FileOutput {

    DB_Controller db;
    String fName;
    String[] dataArr;

    public FileOutput(DB_Controller d, String name) {
        db = d;
        fName = name;

    }

    public void DumpAllToXML(DB_Controller db)throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException{
        try {
            XMLConverterMainDB(db);
            XMLConverterAvg(db);
            XMLConverterHiLo(db);
            
            
            
        } catch (Exception e) {
        }

    }
    //=================FOR USE WITH WEATHERDATA=================================

    /**
     * A debug method for testing and verifying that an empty set wasn't handed
     * over
     *
     * @param db
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private boolean textChkMain(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] data = db.DB_Dump();
            if (data.length > 0) {
                dataArr = data;
                System.out.println("Status: Data is valid");
                return true;

            } else {
                System.err.println("ERROR: No data found. cannot parse to XML");
                return false;
            }
        } catch (Exception e) {
            System.err.println("ERROR: cannot parse to XML. This is because of a DB error");
            return false;

        }

    }

    public void XMLConverterMainDB(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
            System.out.println("Status: Procesing Database Weatherdata" );
            if (textChkMain(db)) {
                PrintWriter writer = new PrintWriter("Main_OUT.xml");
                //writer.write("TEST TEST");

                System.out.println("Status: Begining XML Parse");
                writer.println("<XML>");

                for (int i = 0; i < dataArr.length; i++) {
                    //writer.println(dataArr[i]);
                    //writer.println("<arr f=\"1\" g=\"9\"/>");
                    //Step 1: break string into pieces
                    String[] tem = dataArr[i].split(" ");
                    writer.println("<Reading Date=\"" + tem[0] + "\" Time=\"" + tem[1] + "\" Temperature=\"" + tem[2] + "\" Humidity=\"" + tem[3] + "\" Pressure=\"" + tem[4] + "\" DewPoint=\"" + tem[5] + "\" />");

                    //Step 2: put each piece into an xml line         
//LIKE THIS:   <reading date="~~~" time="~~~" temp="~~~" pressure="~~~" humid="~~~" dew="~~~"/> 
                    //Step 3: write it to a file
                }
                writer.println("</XML>");
                writer.close();
                System.out.println("Status: Main_OUT.xml Sucessfully Created");
                 System.out.println();
            } else {
                System.err.println("An error has occured in Fileoutput#XMLConverter");

            }

        } catch (Exception e) {
            System.err.println("ErROR");

        }

    }

    //=======================FOR USE WITH DAILYAVERAGES=========================
    private boolean textChkAvg(DB_Controller db) throws ClassNotFoundException, SQLException {
        try {
            String[] data = db.DailyAvgDump();
            if (data.length > 0) {
                dataArr = data;
                //System.out.println(dataArr[1]);
                System.out.println("Status: Data is valid");
                return true;

            } else {
                System.err.println("ERROR: No data found. cannot parse to XML");
                return false;
            }
        } catch (Exception e) {
            System.err.println("ERROR: cannot parse to XML. This is because of a DB error");
            return false;

        }

    }

    public void XMLConverterAvg(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
             System.out.println("Status: Procesing Database DailyAvgs" );
            if (textChkAvg(db)) {
                PrintWriter writer = new PrintWriter("Avg_OUT.xml");
                //writer.write("TEST TEST");

                System.out.println("Status: Begining XML Parse");
                writer.println("<XML>");

                for (int i = 0; i < dataArr.length; i++) {
                    //writer.println(dataArr[i]);
                    //writer.println("<arr f=\"1\" g=\"9\"/>");
                    //Step 1: break string into pieces
                    String[] tem = dataArr[i].split(" ");
                    //System.out.println(tem.length);
                    writer.println("<Daily_Average Date=\"" + tem[0] + "\" Average_Temperature=\"" + tem[1] + "\" Average_Humidity=\"" + tem[2] + "\" Average_Pressure=\"" + tem[3] + "\" Average_DewPoint=\"" + tem[4] + "\" />");

                    //Step 2: put each piece into an xml line         
//LIKE THIS:   <reading date="~~~" time="~~~" temp="~~~" pressure="~~~" humid="~~~" dew="~~~"/> 
                    //Step 3: write it to a file
                }
                writer.println("</XML>");
                writer.close();
                System.out.println("Status: Avg_OUT.xml Sucessfully Created");
                System.out.println();
            } else {
                System.err.println("An error has occured in Fileoutput#XMLConverter");

            }

        } catch (Exception e) {
            System.err.println("ErROR");

        }

    }

    //=======================FOR USE WITH DAILY HIGH/LOW========================
    private boolean textChkHiLo(DB_Controller db) throws ClassNotFoundException, SQLException {

        try {
            String[] data = db.DailyHiLowDump();
            if (data.length > 0) {
                dataArr = data;
                //System.out.println(dataArr[1]);
                System.out.println("Status: Data is valid");
                return true;

            } else {
                System.err.println("ERROR: No data found. cannot parse to XML");
                return false;
            }
        } catch (Exception e) {
            System.err.println("ERROR: cannot parse to XML. This is because of a DB error");
            return false;

        }

    }

    public void XMLConverterHiLo(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
             System.out.println("Status: Procesing Database DailyHiLow" );
            if (textChkHiLo(db)) {
                PrintWriter writer = new PrintWriter("HiLo_OUT.xml");
                //writer.write("TEST TEST");

                System.out.println("Status: Begining XML Parse");
                writer.println("<XML>");

                for (int i = 0; i < dataArr.length; i++) {
                    //writer.println(dataArr[i]);
                    //writer.println("<arr f=\"1\" g=\"9\"/>");
                    //Step 1: break string into pieces
                    String[] tem = dataArr[i].split(" ");
                    //System.out.println(tem.length);
                    //System.out.println(tem.length);
                    writer.println("<Reading Date=\"" + tem[0] + "\" Low_Temperature=\"" + tem[1] + "\" High_Temperature=\"" + tem[2] + "\" Low_Humidity=\"" + tem[3] + "\" High_Humidity=\"" + tem[4] + "\" Low_Pressure=\"" + tem[5] + "\" High_Pressure=\"" + tem[6] + "\" Low_DewPoint=\"" + tem[7] + "\" High_DewPoint=\"" + tem[8] + "\" />");

                    //Step 2: put each piece into an xml line         
//LIKE THIS:   <reading date="~~~" time="~~~" temp="~~~" pressure="~~~" humid="~~~" dew="~~~"/> 
                    //Step 3: write it to a file
                }
                writer.println("</XML>");
                writer.close();
                System.out.println("Status: HiLo_OUT.xml Sucessfully Created");
                System.out.println();
            } else {
                System.err.println("An error has occured in Fileoutput#XMLConverter");

            }

        } catch (Exception e) {
            System.err.println("ErROR");

        }

    }
}
