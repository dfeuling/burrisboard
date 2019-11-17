package burrisboard.server;
import java.sql.*;

class SQLBridge
{

    Statement statement;
    ResultSet resultSet;

    static void connect() throws Exception
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/burrisboard", "server", "ROBOTS!");
            System.out.println("Successfully connected to the database.");

            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO burrisboard.users VALUES (0001, 'dfeul', 'password', 'Daniel', 'Feuling')");
            ResultSet resultset = null;

        } catch (Exception e) {
            System.out.println("Error connecting to the database. Exception follows: " + e);
        }
    }
}
