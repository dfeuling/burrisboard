package burrisboard.server;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//main driver of the central processing module
//intermediary between user and server/database
//basic instrumentation and analysis
//inputs: data flow from user
//outputs: requests from user
class Engine
{
    static final int maxThreadCount = 5;
    public static void main(String[] args)
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(maxThreadCount);
        int currentThread = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(3307);
            System.out.println("Server socket success.");
            int received = 1;
            Runnable[] threadHolder = new Runnable[maxThreadCount];
            while(true)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    //thread start
                    threadHolder[currentThread] = new bTask(clientSocket,currentThread);
                    threadPool.execute(threadHolder[currentThread]);
                    System.out.println("Data receipt number " + received + " for this uptime.");
                    received++;
                    currentThread++;
                    currentThread = currentThread % maxThreadCount;
                    } catch (Exception e){System.out.println("Error with client connection: Error follows: " + e);}
            }
        } catch (Exception e) {System.out.println("Error with setting server connection. Error follows: " + e);}
    }

    static void Login(bTask task)
    {
        String query = "{?=CALL Login(?,?,?)"
        task.bridge.statement = task.bridge.con.prepareCall(query);
        statement.setString("username",clientInput.getUser1().getUserName());
        statement.setString("password",clientInput.getUser1().getUserPassword());
        statement.setBoolean("failure",task.bridge.getFailure());

        task.bridge.resultSet = task.bridge.statement.executeQuery(query);
    }

    static synchronized void createParent(bTask task)
    {
        String query = "{?=CALL Login(?,?,?,?,?)"
        task.bridge.statement = this.bridge.con.prepareCall(query);
        statement.setString("username",clientInput.getUser1().getUserName());
        statement.setString("password",clientInput.getUser1().getUserPassword());
        statement.setString("first name", clientInput.getUser1.getUserFirstName());
        statement.setString("last name", clientInput.getUser1.getUserLastName());
        statement.setBoolean("failure",task.bridge.getFailure());
    }

    static synchronized void createStudent(bTask task)
    {
        String query = "{?=CALL Login(?,?,?,?,?)"
        this.bridge.statement = this.bridge.con.prepareCall(query);
        statement.setString("username",clientInput.getUser1().getUserName());
        statement.setString("password",clientInput.getUser1().getUserPassword());
        statement.setString("first name", clientInput.getUser1.getUserFirstName());
        statement.setString("last name", clientInput.getUser1.getUserLastName());
        statement.setBoolean("failure",task.bridge.getFailure());  
    }

    static synchronized void createTeacher(bTask task)
    {
        String query = "{?=CALL Login(?,?,?,?,?)"
        this.bridge.statement = this.bridge.con.prepareCall(query);
        statement.setString("username",clientInput.getUser1().getUserName());
        statement.setString("password",clientInput.getUser1().getUserPassword());
        statement.setString("first name", clientInput.getUser1.getUserFirstName());
        statement.setString("last name", clientInput.getUser1.getUserLastName());
        statement.setBoolean("failure",task.bridge.getFailure());  
    }

}
