/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piprojdbprog;

import java.util.*;
import java.sql.*;

/**
 * this class does all work that involves direct interaction with the various
 * tables in the database: weatherData, DailyAvgs, and DailyHiLo
 *
 * @author Brendan
 */
public class DB_Controller {

    /**
     * the driver for the SQL linker in this case: MYSQL
     */
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    /**
     * The connection path to the database to be used
     */
    private static final String connectionName
            = "jdbc:mysql://localhost/weather";
    /**
     * The [MY]SQL user to log in as
     */
    private static final String connectionUser = "root";
    /**
     * The password for the abovementioned user in this case an empty
     */
    private static final String connectionPassword = "";

    /**
     * this sets up the database to be used, it MUST be called before any method
     * of this class can be safely used
     *
     * @param indicator 0 if you want to remove old data 1 if you want to not do
     * that if an invalid value is given it will default to 0
     * @throws ClassNotFoundException
     */
    public void createDB(int indicator) throws ClassNotFoundException {
        if (indicator < 0 || indicator > 1) {
            indicator = 0;
        }
        try {
            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            //System.out.println("2");
            Statement st = con.createStatement();
            if (indicator == 0) {
                st.execute("drop table if exists weatherData");
                st.execute("create table weatherData(Date varchar(50),Time varchar(50),  Temp float(2), Humid float(2), Press float(2), Dew_point float(2)) ");
                System.out.println("Database 'weatherdata' created.");
                st.execute("drop table if exists dailyavgs");
                st.execute("create table dailyavgs(Date varchar(50), Temp float(2), Humid float(2), Press float(2) , Dew_point float(2)) ");
                System.out.println("Database: 'dailyavgs' created. ");
                st.execute("drop table if exists dailyHiLow");
                st.execute("create table dailyHiLow(Date varchar(50), LoTemp float(2), HiTemp float(2), LoHum float(2), HiHum float(2), LoPress float(2), HiPress float(2), LoDew float(2), HiDew float(2))");
                System.out.println("Database: 'dailyHiLow' created.");
            }

        } catch (Exception ex) {
            System.err.println("An error ha occured in DB_Controller#CreateDB");
        }
    }
    public String[] DB_Dump() throws ClassNotFoundException, SQLException{
         try {
            int size = getDBSize();
            //System.out.println(size);
            String[] out = new String[size];
            Class.forName(jdbcDriver);
            String qur = "SELECT * from weatherdata";

            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            Statement st = con.createStatement();
            ResultSet ra = st.executeQuery(qur);
            ra.next();
            for (int i = 0 ; i < size; i++){
                out[i] = "" + ra.getObject(1) + " " + ra.getObject(2) + "" + ra.getObject(3) + " " + ra.getObject(4) + " " + ra.getObject(5) + " " + ra.getObject(6);
                //System.out.println(out[i]);
                ra.next();
            
            }
            return out;
            
            
            
            
         }catch(Exception e){
         
         System.err.println("ERROR");
         }
    
    
    return null;
    }

    /**
     *
     * @return the number of lines in the db
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getDBSize() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(jdbcDriver);
            String qur = "SELECT * from weatherdata";

            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            Statement st = con.createStatement();
            ResultSet ra = st.executeQuery(qur);
            int cnt = 0;
            while (ra.next()) {
                cnt++;

            }
            return cnt;
        } catch (Exception e) {
            System.err.println("An error has occured in DB_Controller#getDBSize");
            return -1;

        }

    }
    /**
     * This method inserts data into the 'dailyAvgs' table in the database.
     * NOTE: DO NOT HARDCODE ANY OF THEESE VALUES ALWAYS GET THEM FROM AVERAGING METHODS IN Stats
     * @param date the date string: (YYYY-MM-DD)
     * @param hum the average humidity value (%)
     * @param tem the average temperature value (deg F)
     * @param press the average barometric pressure value (Pa)
     * @param dew the average dew point value
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void AvgDBIns(String date, float hum, float tem, float press, float dew) throws ClassNotFoundException, SQLException {
        try {

            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            //System.out.println("2");
            Statement st = con.createStatement();
            //System.out.println(time);

            st.execute("Insert into dailyavgs values( " + "'" + date + "'" + "," + tem + "," + hum + "," + press + "," + dew + ")");
            //System.out.println("DATA inserted");

        } catch (Exception ex) {
            System.err.println("An eror has occured in DB_controller#AvgDBIns");
        }

    }
    /**
     * inserts data into the 'dailyHiLow' table in the database.
     * all array params are of this form X[0] = [daily] minimum value X[1] = [daily] Maximum value
     * @param date The date on which the data was filed under
     * @param Hum min/max humidity values (%)
     * @param Temp min/max temperature values (F)
     * @param press min/max Pressure values (Pa)
     * @param dew min/max Dew Point values (F)
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void HiLoIns(String date, float[] Hum, float[] Temp, float[] press, float[] dew) throws ClassNotFoundException, SQLException {
        try {
            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            //System.out.println("2");
            Statement st = con.createStatement();
            //System.out.println(Temp[0] + " " + Temp[1]);
            st.execute("insert into dailyHiLow values( " + "'" + date + "'" + "," + Temp[0] + "," + Temp[1] + "," + Hum[0] + "," + Hum[1] + "," + press[0] + "," + press[1] + "," + dew[0] + "," + dew[1] + ")");

        } catch (Exception e) {
            System.err.println("ERROR IN DB_Controller#HiLoIns");
        }
    }

    /**
     * inserts one line into the database
     *
     * @param data the output from
     * {@link piprojdbprog.FileParser#dataParse(int index)}
     */
    public void DBIns(float[] data, String time, String date) {
        try {

            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            //System.out.println("2");
            Statement st = con.createStatement();
            //System.out.println(time);

            st.execute("Insert into weatherData values( " + "'" + date + "'," + "'" + time + " ', " + data[1] + " , " + data[0] + " , " + data[2] + " , " + data[3] + " )");
            //System.out.println("DATA inserted");

        } catch (Exception ex) {
            System.err.println("An error has occured in DB_Controller#DBIns");
        }

    }
    /**
     * 
     * @param val the type of data to return (Temp, Humid, dew_point, or press)
     * @param date the date to retrieve data from
     * @return All data of the specified type for the given day
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public float[] GetDailyData(String val, String date) throws ClassNotFoundException, SQLException {
        try {
            Class.forName(jdbcDriver);
            //System.out.println(val);
            String qur = "SELECT " + val + " from weatherdata where date = " + "'" + date + "'";
            //System.out.println(qur);
            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            Statement st = con.createStatement();
            ResultSet ra = st.executeQuery(qur);
            LinkedList<Float> li = new LinkedList<>();
            while (ra.next()) {
                li.add(ra.getFloat(1));
            }
            //System.out.println(li.size());
            float[] ret = new float[li.size()];
            for (int i = 0; i < li.size(); i++) {
                ret[i] = li.get(i);
            }
            return ret;
            // System.out.println(ra.getFloat(1));
        } catch (Exception e) {
            System.err.println("An error has occured in DB_Controller#GetDailyData");
            return null;
        }

    }
/**
 * Used to return a list of distinct days from the 'weatherdata' database
 * @return a list of distinct days; to be used in daily averages and daily hi/lo methods
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public String[] DistDays() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(jdbcDriver);
            String qur = "SELECT distinct date from weatherdata ";

            Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
            Statement st = con.createStatement();
            ResultSet ra = st.executeQuery(qur);
            LinkedList<String> str = new LinkedList<>();
            while (ra.next()) {
                // System.out.println(ra.getString(1));
                str.add(ra.getString(1));
            }
            String[] arr = new String[str.size()];
            for (int i = 0; i < str.size(); i++) {
                arr[i] = str.get(i);
            }
            System.out.println("Info: "+arr.length+" distinct days found in database");

            return arr;
        } catch (Exception e) {
            System.err.println("An error has occured in DB_Controller#DistDays");
            return null;
        }
    }

    /**
     * searches for all data of a given type (Temp,Press,Dew_point,Humid,Time,Date)
     *
     * @param querry the field in the DB to return a list of
     * @return an array of floats of the specified field
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[] dbSearch(String querry) throws ClassNotFoundException, SQLException {
        try {
            if ("Temp".equals(querry) || "Press".equals(querry) || "Dew_point".equals(querry) || "Humid".equals(querry) || "Time".equals(querry) || "Date".equals(querry)) {
                // Setup and initialization
                Class.forName(jdbcDriver);
                String qur = "SELECT count(*) from weatherdata";

                Connection con = DriverManager.getConnection(connectionName, connectionUser, connectionPassword);
                Statement st = con.createStatement();
                ResultSet ra = st.executeQuery(qur);
                ra.next();
                //  System.out.println("query worked");
                String ct = ra.getString(1);
                //System.out.println(ct);
                int numrows = Integer.parseInt(ct);

                // return list of temps-----------------------------------------
                if ("Temp".equals(querry)) {
                    String[] ret = new String[numrows];
                    st = con.createStatement();
                    qur = "Select Temp from weatherdata";
                    ra = st.executeQuery(qur);
                    ra.next();
                    for (int i = 0; i < numrows; i++) {
                        Float tem = ra.getFloat(1);
                        ret[i] = tem.toString();
                        // System.out.println(i);
                        // System.out.println(ra.getFloat(1));
                        ra.next();

                    }
                    return ret;
                }

                // return list of humidity readings-----------------------------
                if ("Humid".equals(querry)) {
                    String[] ret = new String[numrows];
                    st = con.createStatement();
                    qur = "Select Humid from weatherdata";
                    ra = st.executeQuery(qur);
                    ra.next();
                    for (int i = 0; i < numrows; i++) {
                        Float tem = ra.getFloat(1);
                        ret[i] = tem.toString();
                        //System.out.println(i);
                        // System.out.println(ra.getFloat(1));
                        ra.next();

                    }
                    return ret;
                }

                // return list of pressure readings-----------------------------
                if ("Press".equals(querry)) {
                    String[] ret = new String[numrows];
                    st = con.createStatement();
                    qur = "Select Press from weatherdata";
                    ra = st.executeQuery(qur);
                    ra.next();
                    for (int i = 0; i < numrows; i++) {
                        Float tem = ra.getFloat(1);
                        ret[i] = tem.toString();
                        // System.out.println(i);
                        // System.out.println(ra.getFloat(1));
                        ra.next();

                    }
                    return ret;
                }

                //-----------------------------
                if ("Dew_point".equals(querry)) {
                    String[] ret = new String[numrows];
                    st = con.createStatement();
                    qur = "Select Dew_point from weatherdata";
                    ra = st.executeQuery(qur);
                    ra.next();
                    for (int i = 0; i < numrows; i++) {
                        Float tem = ra.getFloat(1);
                        ret[i] = tem.toString();
                        // System.out.println(i);
                        // System.out.println(ra.getFloat(1));
                        ra.next();

                    }
                    return ret;
                }
                // System.out.println(sna);
            } else {
                System.err.println("Invalid value provided to DB_Controller#dbSearch");

            }
        } catch (Exception e) {
            System.out.println("An error has occured in DB_Controller#dbSearch");
        }
        return null;

    }

}
