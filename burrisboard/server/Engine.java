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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//main driver of the central processing module
//intermediary between user and database
//basic instrumentation and analysis
//inputs: data flow from user
//outputs: requests from user
class Engine
{
    static final int maxThreadCount = 5;
    //allocate thread pool
    //main loop
        //login count total
        //concurrent login count

    public static void main(String[] args)
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(maxThreadCount);
        int currentThread = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(3307);
            System.out.println("Server socket success.");
            int x = 1;
            Runnable[] threadHolder = new Runnable[maxThreadCount];
            while(true)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    //thread start
                    threadHolder[currentThread] = new bTask(clientSocket,currentThread);
                    threadPool.execute(threadHolder[currentThread]);
                    //
                    //System.out.println("Client detection established.");
                    //ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                    //ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    //bPackage clientInput = (bPackage)in.readObject();


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
