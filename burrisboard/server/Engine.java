package burrisboard.server;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import burrisboard.device.*;

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
        try {
            //preparing query
             task.bridge.resultSet = task.bridge.statement.executeQuery("SELECT User_Type FROM burrisboard.users WHERE pass = " + "\"" + task.clientInput.getUser1().getUserPassword() + "\"" + " and Username = "  + "\"" + task.clientInput.getUser1().getUserName() + "\"");
             //if the credentials are correct, the resultset is populated
             if (task.bridge.resultSet.next())
             {
                 //check the user's type
                 burrisboard.device.User.Role thisUserType = burrisboard.device.User.Role.valueOf(task.bridge.resultSet.getString("User_Type"));
                 task.clientInput.getUser1().setUserType(thisUserType);


                 //User object population if it's a parent
                 if(thisUserType == User.Role.Parent)
                 {
                     task.clientInput.setUser2(new burrisboard.device.Parent());
                     task.clientInput.setResult(true);
                     task.clientInput.getUser2().setUserFirstName("YA MOM");
                 }
             }
             else
                 task.clientInput.setErrorMessage("Incorrect credentials.");

        }catch(Exception e){System.out.println("Login error. Error follows: " + e);}
    }

    static synchronized void createParent(bTask task)
    {
        try{
            String query = "{?=CALL createGenericParent(?,?,?,?,?)";
            task.bridge.cStatement = task.bridge.con.prepareCall(query);
            task.bridge.cStatement.setString("username",task.clientInput.getUser1().getUserName());
            task.bridge.cStatement.setString("password",task.clientInput.getUser1().getUserPassword());
            task.bridge.cStatement.setString("first name", task.clientInput.getUser1().getUserFirstName());
            task.bridge.cStatement.setString("last name", task.clientInput.getUser1().getUserLastName());
            task.bridge.cStatement.setBoolean("failure",task.bridge.getFailure());
        }catch(Exception e){System.out.println("Create Parent error. Error follows: " + e);}
    }

    static synchronized void createStudent(bTask task)
    {
        try{
                String query = "{?=CALL createGenericStudent(?,?,?,?,?)";
                task.bridge.cStatement = task.bridge.con.prepareCall(query);
                task.bridge.cStatement.setString("username",task.clientInput.getUser1().getUserName());
                task.bridge.cStatement.setString("password",task.clientInput.getUser1().getUserPassword());
                task.bridge.cStatement.setString("first name", task.clientInput.getUser1().getUserFirstName());
                task.bridge.cStatement.setString("last name", task.clientInput.getUser1().getUserLastName());
                task.bridge.cStatement.setBoolean("failure",task.bridge.getFailure());
        }catch(Exception e){System.out.println("Create Student error. Error follows: " + e);}
    }

    static synchronized void createTeacher(bTask task)
    {
        try{
                String query = "{?=CALL createGenericTeacher(?,?,?,?,?)";
                task.bridge.cStatement = task.bridge.con.prepareCall(query);
                task.bridge.cStatement.setString("username",task.clientInput.getUser1().getUserName());
                task.bridge.cStatement.setString("password",task.clientInput.getUser1().getUserPassword());
                task.bridge.cStatement.setString("first name", task.clientInput.getUser1().getUserFirstName());
                task.bridge.cStatement.setString("last name", task.clientInput.getUser1().getUserLastName());
                task.bridge.cStatement.setBoolean("failure",task.bridge.getFailure());
        }catch(Exception e){System.out.println("Create Teacher error. Error follows: " + e);}
    }
}
