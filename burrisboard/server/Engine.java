package burrisboard.server;
import java.net.Socket;
import java.sql.*;
import java.net.ServerSocket;

//main driver of the central processing module
//intermediary between user and database
//basic instrumentation and analysis
//inputs: data flow from user
//outputs: requests from user
class Engine
{
    //allocate thread pool
    //main loop
        //login count total
        //concurrent login count

    public static void main(String[] args)
    {

        try
        {
            SQLBridge.connect();
        }
        catch(Exception e)
        {
            System.out.println("Error with database connection. Error follows: " + e);
        }

        try
        {
            ServerSocket serverSocket = new ServerSocket(3306);
            Socket clientSocket = serverSocket.accept();
        }
        catch(Exception e)
        {
            System.out.println("Error with client connection. Error follows: " + e);
        }
    }

    //create new login
    //login
    //parent linking to student
    //student linking to parent
    //teacher linking parent and student
    //view a student's classes
    //view one particular class's announcements
    //view announcements for all classes where student is enrolled
    //view document
    //teacher creating class
    //user messaging



}
