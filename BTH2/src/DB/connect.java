package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

    public static Connection getConnection() {
        Connection conn = null;
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://MSI\\SQL2022EX:1433;databaseName=QLSV;trustServerCertificate=True";

            String username = "sa";
            String password = "123";

            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

   public static void closeConnection(Connection c) {
      try {
          if (c != null) {
             c.close();
          }       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
