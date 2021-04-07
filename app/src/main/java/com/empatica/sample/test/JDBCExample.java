package com.empatica.sample.test;

//STEP 1. Import required packages
import android.util.Log;

import com.empatica.sample.MainActivity;

import java.sql.*;

public class JDBCExample {

    public MainActivity activity;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:sqlite://";//mysql://";
    static final String PATH = "/D:/Usuarios/Admin/Desktop/PG/PlatformDDA/Assets/ExplorerDatabase.db";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    public JDBCExample(MainActivity activity) {
        this.activity = activity;
        String db = DB_URL + activity.ipUnity + PATH;
        Log.i("DATABASE", "Initiate " + db);

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("org.sqlite.JDBC");//"com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            Log.i("DATABASE", "Connecting to database...");
            conn = DriverManager.getConnection(db);

            //STEP 4: Execute a query
            Log.i("DATABASE", "Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            Log.i("DATABASE", "Database created successfully...");
        } catch(SQLException se){
            //Handle errors for JDBC
            Log.e("DATABASE JDBC", se.getMessage());
            se.printStackTrace();
        } catch(Exception e){
            //Handle errors for Class.forName
            Log.e("DATABASE E", e.getMessage());
            e.printStackTrace();
        } finally{
            //finally block used to close resources
            try{
                if (stmt!=null)
                    stmt.close();
            } catch(SQLException se2){
                Log.e("DATABASE SE2", se2.getMessage());
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                Log.e("DATABASE SE", se.getMessage());
                se.printStackTrace();
            }//end finally try
        }//end try
        Log.i("DATABASE", "Goodbye!");
    }//end main
}//end JDBCExample
