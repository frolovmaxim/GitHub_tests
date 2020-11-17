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
    }
    @Test //(enabled = false)
    public void getEmployeesFromDataBase() {
        main.checkTable("select * from persons");
    }

    @Test //(enabled = false)
    public void getSpecificEmployee(){
        String perName = null;
        String actual = main.getName("select name from Employees.persons where id = 6");
        Assert.assertEquals(actual, "John");
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
        main.checkTable("select * from persons");
        Assert.assertEquals(main.getName("select name from persons where id = 8"), "Newman");
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

        main.checkTable("select * from persons");
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
        main.checkTable("select * from persons");
        Assert.assertNull(main.getName("select name from persons where id = 8"));
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
