package burrisboard.server;
import java.sql.*;

class SQLBridge
{
    public SQLBridge(int bridgeID)
    {
        this.ID = bridgeID;
    }

    int ID;
    CallableStatement cStatement;
    Statement statement;
    ResultSet resultSet;
    Connection con;

    void connect() throws Exception
    {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/burrisboard", "server"+ID, "ROBOTS!");
        System.out.println("Successfully connected to the database.");

        this.statement = con.createStatement();
    }
}
