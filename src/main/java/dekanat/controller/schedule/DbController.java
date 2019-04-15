package dekanat.controller.schedule;

import dekanat.dao.MyDataSourceFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbController {
    public static Connection getConnection() {

        Connection conn;
        try {
            conn = MyDataSourceFactory.getMySQLDataSource().getConnection();
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
