package burrisboard.server;
import java.sql.*;

class SQLBridge
{
    public SQLBridge(int bridgeID)
    {
        this.ID = bridgeID;
    }

    int ID;
    Statement statement;
    ResultSet resultSet;

    void connect() throws Exception
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/burrisboard", "server"+ID, "ROBOTS!");
        System.out.println("Successfully connected to the database.");

        this.statement = con.createStatement();
        //statement.executeUpdate("INSERT INTO burrisboard.users VALUES (0001, 'dfeul', 'password', 'Daniel', 'Feuling')");
    }
}
