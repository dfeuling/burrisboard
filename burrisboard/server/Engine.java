package burrisboard.server;

import java.net.Socket;
import java.net.ServerSocket;
import java.sql.Types;
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
             task.bridge.resultSet = task.bridge.statement.executeQuery("SELECT * FROM burrisboard.users WHERE pass = " + "\"" + task.clientInput.getUser1().getUserPassword() + "\"" + " and Username = "  + "\"" + task.clientInput.getUser1().getUserName() + "\"");
             //if the credentials are correct, the resultset is populated
             if (task.bridge.resultSet.next())
             {
                 System.out.println("Successful login by thread " + task.getID());
                 //check the user's type
                 burrisboard.device.User.Role thisUserType = burrisboard.device.User.Role.valueOf(task.bridge.resultSet.getString("User_Type"));
                 task.clientInput.getUser1().setUserType(thisUserType);

                 //User object population if it's a parent
                 if(thisUserType == User.Role.Parent)
                 {
                     task.clientInput.setResult(true);
                     task.clientInput.getUser1().setUserFirstName(task.bridge.resultSet.getString("First_Name"));
                     task.clientInput.getUser1().setUserLastName(task.bridge.resultSet.getString("Last_Name"));
                     //Statements to populate the parent's LinkedList<Student> children and boolean linked
                 }
                 if(thisUserType == User.Role.Student)
                 {
                     task.clientInput.setResult(true);
                     task.clientInput.getUser1().setUserFirstName(task.bridge.resultSet.getString("First_Name"));
                     task.clientInput.getUser1().setUserLastName(task.bridge.resultSet.getString("Last_Name"));
                     //Statements to populate the student's LinkedList<Student> parents and LinkedList<Class> courses
                 }
                 if(thisUserType == User.Role.Teacher)
                 {
                     task.clientInput.setResult(true);
                     task.clientInput.getUser1().setUserFirstName(task.bridge.resultSet.getString("First_Name"));
                     task.clientInput.getUser1().setUserLastName(task.bridge.resultSet.getString("Last_Name"));
                     //Statements to populate the teacher's LinkedList<Student> studentsTaught and LinkedList<Class> coursesTaught
                 }
             }
             else
                 task.clientInput.setErrorMessage("Incorrect credentials.");

        }catch(Exception e){System.out.println("Login error. Error follows: " + e);}
    }

    static synchronized void createParent(bTask task)
    {
        try{
            String query = "{?=CALL createGenericParent(?,?,?,?)}";
            task.bridge.cStatement = task.bridge.con.prepareCall(query);
            task.bridge.cStatement.setString(1,task.clientInput.getUser1().getUserName());
            task.bridge.cStatement.setString(2,task.clientInput.getUser1().getUserPassword());
            task.bridge.cStatement.setString(3, task.clientInput.getUser1().getUserFirstName());
            task.bridge.cStatement.setString(4, task.clientInput.getUser1().getUserLastName());
            task.bridge.cStatement.registerOutParameter(5, Types.BOOLEAN);
            task.bridge.cStatement.execute();

            if(task.bridge.cStatement.getBoolean("failure"))
            {
                System.out.println("Successful Parent created by thread " + task.getID());
                task.clientInput.setResult(true);
            }
            else
                task.clientInput.setErrorMessage("Database insertion error.");


        }catch(Exception e){System.out.println("Create Parent error. Error follows: " + e);}
    }

    static synchronized void createStudent(bTask task)
    {
        try{
            String query = "{call createGenericStudent(?,?,?,?,?)}";
            task.bridge.cStatement = task.bridge.con.prepareCall(query);
            task.bridge.cStatement.setString(1,task.clientInput.getUser1().getUserName());
            task.bridge.cStatement.setString(2,task.clientInput.getUser1().getUserPassword());
            task.bridge.cStatement.setString(3, task.clientInput.getUser1().getUserFirstName());
            task.bridge.cStatement.setString(4, task.clientInput.getUser1().getUserLastName());
            task.bridge.cStatement.registerOutParameter(5, Types.BOOLEAN);
            task.bridge.cStatement.execute();

            if(task.bridge.cStatement.getBoolean(5))
            {
                System.out.println("Successful Student created by thread " + task.getID());
                task.clientInput.setResult(true);
            }
            else
                task.clientInput.setErrorMessage("Database insertion error.");

        }catch(Exception e){System.out.println("Create Student error. Error follows: " + e);}
    }

    static synchronized void createTeacher(bTask task)
    {
        try{
            String query = "{call createGenericTeacher(?,?,?,?)}";
            task.bridge.cStatement = task.bridge.con.prepareCall(query);
            task.bridge.cStatement.setString(1,task.clientInput.getUser1().getUserName());
            task.bridge.cStatement.setString(2,task.clientInput.getUser1().getUserPassword());
            task.bridge.cStatement.setString(3, task.clientInput.getUser1().getUserFirstName());
            task.bridge.cStatement.setString(4, task.clientInput.getUser1().getUserLastName());
            task.bridge.cStatement.registerOutParameter(5, Types.BOOLEAN);
            task.bridge.cStatement.execute();

            if(task.bridge.cStatement.getBoolean("failure"))
            {
                System.out.println("Successful Teacher created by thread " + task.getID());
                task.clientInput.setResult(true);
            }
            else
                task.clientInput.setErrorMessage("Database insertion error.");

        }catch(Exception e){System.out.println("Create Teacher error. Error follows: " + e);}
    }
}
