package dekanat.controller.schedule;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbController {
    public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dekanat","root","root");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
