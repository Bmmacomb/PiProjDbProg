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
 * this is done in two steps;
 * 1) "Gatekeeper" textchk methods, these verify the data is in the expected format when it is 
 *  fetched from the database before moving on to create an xml file.
 * 2) Actually outputting the data as an xml, many spreadsheet programs can recognize this format
 *  and the data can be presented in a more appealing way as a result.
 *
 * @author Brendan
 */
public class FileOutput {

    
    /**
     * A placeholder to hold the data between running the textChk methods and the actual parsers
     */
    String[] dataArr;
    
 

    /**
     * Just as the name suggests: this will create an XML of every table in the
     * database
     *
     * @param db the DB_Controller that the CALLING OBJECT is using
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void DumpAllToXML(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
            XMLConverterMainDB(db);
            XMLConverterAvg(db);
            XMLConverterHiLo(db);

        } catch (Exception e) {
            System.err.println("An error has occured in FileOutput#DumpAllToXML");
        }

    }
    //=================FOR USE WITH WEATHERDATA=================================

    /**
      * An error-check method for testing and verifying that an empty or invalid set wasn't handed
     * over
     *
     * @param db the caller's instance of DB_Controller
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
            System.err.println("An error has occured in FileOutput#textChkMain");
            System.err.println("ERROR: cannot parse to XML. This is because of a DB error");
            return false;

        }

    }
    /**
     * creates a XML file of Weatherdata
     * @param db the caller's instance of DB_Controller
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void XMLConverterMainDB(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
            System.out.println("Status: Procesing Database Weatherdata");
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
                System.err.println("Error: Invalid data given to FileOutput#XMLConverterMainDB ");

            }

        } catch (Exception e) {
            System.err.println("An error has occured in FileOutput#XMLCouverterMainDB");

        }

    }

    //=======================FOR USE WITH DAILYAVERAGES=========================
    /**
     * An error-check method for testing and verifying that an empty or invalid set wasn't handed
     * over
     *
     * @param db the caller's instance of DB_Controller
     * @throws ClassNotFoundException
     * @throws SQLException
     */
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
            System.err.println("An error has occured in FileOutput#TextChkAvg");
            System.err.println("ERROR: cannot parse to XML. This is because of a DB error");
            return false;

        }

    }
    /**
     * Creates an xml file of DailyAvgs database
     * @param db use the DB_Controller in the caller
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void XMLConverterAvg(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
            System.out.println("Status: Procesing Database DailyAvgs");
            if (textChkAvg(db)) {
                PrintWriter writer = new PrintWriter("Avg_OUT.xml");
                System.out.println("Status: Begining XML Parse");
                writer.println("<XML>");

                for (int i = 0; i < dataArr.length; i++) {
                    String[] tem = dataArr[i].split(" ");
                    //System.out.println(tem.length);
                    writer.println("<Daily_Average Date=\"" + tem[0] + "\" Average_Temperature=\"" + tem[1] + "\" Average_Humidity=\"" + tem[2] + "\" Average_Pressure=\"" + tem[3] + "\" Average_DewPoint=\"" + tem[4] + "\" />");
                }
                writer.println("</XML>");
                writer.close();
                System.out.println("Status: Avg_OUT.xml Sucessfully Created");
                System.out.println();
            } else {
                System.err.println("Error: Invalid data given to FileOutput#XMLConverterAvg ");

            }

        } catch (Exception e) {
            System.err.println("An error has occured in Fileoutput#XMLConverterAvg");

        }

    }

    //=======================FOR USE WITH DAILY HIGH/LOW========================
    /**
     * An error-check method for testing and verifying that an empty or invalid set wasn't handed
     * over
     *
     * @param db the caller's instance of DB_Controller
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private boolean textChkHiLo(DB_Controller db) throws ClassNotFoundException, SQLException {

        try {
            String[] data = db.DailyHiLowDump();
            if (data.length > 0) {
                dataArr = data;
                //System.out.println(dataArr[1]);
                System.out.println("Status: Data is valid");
                return true;

            } else {
                System.err.println("An error has occured in Fileoutput#textChkHiLo");
                System.err.println("ERROR: No data found. cannot parse to XML");
                return false;
            }
        } catch (Exception e) {
            System.err.println("ERROR: cannot parse to XML. This is because of a DB error");
            return false;

        }

    }
    /**
     * Creates a XML file of the Dailyhilow database
     * @param db the caller's instance of DB_Controller
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void XMLConverterHiLo(DB_Controller db) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        try {
            System.out.println("Status: Procesing Database DailyHiLow");
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
                System.err.println("Error: Invalid data given to FileOutput#XMLConverterHiLo");

            }

        } catch (Exception e) {
            System.err.println("An error has occured in FileOutput#XMLConverterHiLo");

        }

    }
}
