package dekanat.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class MyDataSourceFactory {

    // Connect to a MySQL Database
    public static DataSource getMySQLDataSource() {


        MysqlDataSource mysqlDS = null;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://localhost:3306/dekanat");
        mysqlDS.setUser("root");
        mysqlDS.setPassword("12345");
        return mysqlDS;

    }
}
