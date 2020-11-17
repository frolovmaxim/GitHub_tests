import org.testng.Assert;

import java.sql.*;

public class DBmain {
    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public Connection setUp(){
        String databaseURL = "jdbc:mysql://localhost:3306/Employees";
        String user = "root";
        String password = "Password";
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public void checkTable(String query){
        this.setUp();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                int perId= rs.getInt("id");
                String perName= rs.getString("Name");
                int perAge= rs.getInt("Age");
                System.out.println(perId + " | " + perName + " | " + perAge);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getName(String query){
        String perName = null;
        this.setUp();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                perName= rs.getString("Name");
                System.out.println(perName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return perName;
    }
}
