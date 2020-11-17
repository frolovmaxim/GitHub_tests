import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.*;

import static org.testng.Assert.*;


public class DBTesting {
    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    DBmain main;
    @BeforeClass
    public void setUp() {
        main = new DBmain();
        main.setUp();
/*        String databaseURL = "jdbc:mysql://localhost:3306/Employees";
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
        }*/
    }
    @Test //(enabled = false)
    public void getEmployeesFromDataBase() {
        main.checkTable();

/*        try {
            String query = "select * from persons";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                int perId= rs.getInt("id");
                String perName= rs.getString("Name");
                int perAge= rs.getInt("Age");
                System.out.println(perId + " | " + perName + " | " + perAge);
                Assert.assertTrue(perAge <= 38);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }

    @Test //(enabled = false)
    public void getSpecificEmployee(){
        String perName = null;
        try {
            String query = "select * from Employees.persons where id = 6";
            statement = main.setUp().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                int perId= rs.getInt("id");
                perName= rs.getString("Name");
                int perAge= rs.getInt("Age");
                System.out.println(perId + " | " + perName + " | " + perAge);
            }
            System.out.println(perName);
            Assert.assertEquals(perName, "John");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Test //(enabled = false)
    public void insertRecordEmployee(){
        try {
            String query = "insert into Employees.persons values(8,'Newman', 39)";
            statement = main.setUp().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        main.checkTable();
/*        try {
            String query = "select * from persons";
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
        }*/
    }



    @Test (dependsOnMethods = "insertRecordEmployee")
    public void updateRecordEmployee(){
        try {
            String query = "update Employees.persons set name = 'Superman' where id = 8";
            statement = main.setUp().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        main.checkTable();
/*        try {
            String query = "select * from persons";
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
        }*/
    }

    @Test (dependsOnMethods = "updateRecordEmployee")
    public void deleteRecordEmployee(){
        try {
            String query = "delete from Employees.persons where id = 8";
            statement = main.setUp().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        main.checkTable();
/*        try {
            String query = "select * from persons";
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
        }*/
    }

    @AfterClass
    public void tearDown() {
        if (connection != null) {
            try {
                System.out.println("Closing Database Connection...");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
