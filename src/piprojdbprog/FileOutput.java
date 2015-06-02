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
    public boolean textChk(DB_Controller db) throws ClassNotFoundException, SQLException {
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

    public void XMLConverter(DB_Controller db) throws ClassNotFoundException, SQLException {
        if (textChk(db)) {
            System.out.println("Status: Begining XML Parse");
            for (int i = 0 ; i < dataArr.length; i++){
            
            
            }
            

        }else{
        System.err.println("An error has occured in Fileoutput#XMLConverter");
        
        }

    }

}
