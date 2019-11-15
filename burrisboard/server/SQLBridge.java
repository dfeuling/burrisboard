package burrisboard.server;
import java.sql.*;

class SQLBridge
{
    static void connect() throws Exception
    {
        try
        {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/burrisboard", "server", "ROBOTS!");
            System.out.println("Successfully connected to the database.");

        } catch (Exception e) {
            System.out.println("Error connecting to the database. Exception follows: " + e);
        }
    }
}
