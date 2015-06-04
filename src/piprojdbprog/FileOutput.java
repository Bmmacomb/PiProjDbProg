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

    /**
     * A debug method for testing and verifying that an empty set wasn't handed
     * over
     *
     * @param db
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean textChkMain(DB_Controller db) throws ClassNotFoundException, SQLException {
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
                    writer.println("<Reading Date=\""+tem[0]+"\" Time=\""+tem[1]+"\" Temperature=\""+tem[2]+"\" Humidity=\""+tem[3]+"\" Pressure=\""+tem[4]+"\" DewPoint=\""+tem[5]+"\" />");
                    
            //Step 2: put each piece into an xml line         
//LIKE THIS:   <reading date="~~~" time="~~~" temp="~~~" pressure="~~~" humid="~~~" dew="~~~"/> 
                    //Step 3: write it to a file
                }
                writer.println("</XML>");
                writer.close();
                System.out.println("Status: Main_OUT.xml Sucessfully Created");
            } else {
                System.err.println("An error has occured in Fileoutput#XMLConverter");

            }

        } catch (Exception e) {
            System.err.println("ErROR");

        }

    }
}
