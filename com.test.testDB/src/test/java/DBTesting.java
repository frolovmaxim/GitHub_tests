import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.*;



public class DBTesting {
    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    @BeforeClass
    public void setUp() {
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
    }
    @Test
    public void getEmployeesFromDataBase() {
        try {
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
        }
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
