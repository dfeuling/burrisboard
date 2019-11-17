package burrisboard.server;

import burrisboard.device.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
        try {
            SQLBridge.connect();
        } catch (Exception e) {
            System.out.println("Error with database connection. Error follows: " + e);
        }

        try {
            ServerSocket serverSocket = new ServerSocket(3307);
            System.out.println("Server socket success.");
            int x = 1;
            while(true)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client detection established.");
                    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    bPackage clientInput = (bPackage)in.readObject();

                    //if (clientInput != null) {
                        //out.println("Burrisboard server has recieved your professional grade information. We're in good shape.");
                        System.out.println("Detected input from client: " + clientInput.toString());
                        System.out.println("Data receipt number " + x + " for this up-time.");
                        x++;
                        System.out.println(clientInput.getOpCode());
                    //}

                    //login


                    //send back result
                    //close socket

                    //clientInput = null;
                    } catch (Exception e){System.out.println("Error with client connection: Error follows: " + e);}
            }
        } catch (Exception e) {System.out.println("Error with setting server connection. Error follows: " + e);}

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
