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
                     task.clientInput.getUser1().setUserFirstName(task.bridge.resultSet.getString("First_Name"));
                     task.clientInput.getUser1().setUserLastName(task.bridge.resultSet.getString("Last_Name"));

                     //create a parent object with the correct information
                     Parent thisUser = new Parent();
                     thisUser.setUserName(task.clientInput.getUser1().getUserName());
                     thisUser.setUserPassword(task.clientInput.getUser1().getUserPassword());
                     thisUser.setUserFirstName(task.clientInput.getUser1().getUserFirstName());
                     thisUser.setUserLastName(task.clientInput.getUser1().getUserLastName());
                     thisUser.setUserID(task.clientInput.getUser1().getUserID());
                     thisUser.setUserType(thisUserType);

                     //check if the parent has any students linked
                     task.bridge.resultSet = task.bridge.statement.executeQuery("SELECT Child from related_to WHERE Parent = " + task.clientInput.getUser1().getUserID());
                     if(task.bridge.resultSet.next())
                     thisUser.setLinked(true);

                     task.clientInput.setUser1(thisUser);
                     task.clientInput.setResult(true);
                     //TODO: Populate the parent's LinkedList<Student> children
                 }
                 if(thisUserType == User.Role.Student)
                 {
                     task.clientInput.setResult(true);
                     task.clientInput.getUser1().setUserFirstName(task.bridge.resultSet.getString("First_Name"));
                     task.clientInput.getUser1().setUserLastName(task.bridge.resultSet.getString("Last_Name"));

                     //create a student object with the correct information
                     Student thisUser = new Student();
                     thisUser.setUserName(task.clientInput.getUser1().getUserName());
                     thisUser.setUserPassword(task.clientInput.getUser1().getUserPassword());
                     thisUser.setUserFirstName(task.clientInput.getUser1().getUserFirstName());
                     thisUser.setUserLastName(task.clientInput.getUser1().getUserLastName());
                     thisUser.setUserID(task.clientInput.getUser1().getUserID());
                     thisUser.setUserType(thisUserType);

                     task.clientInput.setUser1(thisUser);
                     task.clientInput.setResult(true);
                     //TODO: Populate the student's LinkedList<Student> parents and LinkedList<Class> courses
                 }
                 if(thisUserType == User.Role.Teacher)
                 {
                     task.clientInput.setResult(true);
                     task.clientInput.getUser1().setUserFirstName(task.bridge.resultSet.getString("First_Name"));
                     task.clientInput.getUser1().setUserLastName(task.bridge.resultSet.getString("Last_Name"));

                     //create a teacher object with the correct information
                     Teacher thisUser = new Teacher();
                     thisUser.setUserName(task.clientInput.getUser1().getUserName());
                     thisUser.setUserPassword(task.clientInput.getUser1().getUserPassword());
                     thisUser.setUserFirstName(task.clientInput.getUser1().getUserFirstName());
                     thisUser.setUserLastName(task.clientInput.getUser1().getUserLastName());
                     thisUser.setUserID(task.clientInput.getUser1().getUserID());
                     thisUser.setUserType(thisUserType);

                     task.clientInput.setUser1(thisUser);
                     task.clientInput.setResult(true);
                     //TODO: Populate the teacher's LinkedList<Student> studentsTaught and LinkedList<Class> coursesTaught
                 }
             }
             else
                 task.clientInput.setErrorMessage("Incorrect credentials.");

        }catch(Exception e){System.out.println("Login error for thread " + task.getID() + ". Error follows: " + e);}
    }

    static synchronized void createAccount(bTask task)
    {
        if(task.clientInput.getUser1().getUserType() == User.Role.valueOf("Parent"))
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


            }catch(Exception e){System.out.println("Create Parent error for thread " + task.getID() + ". Error follows: " + e);}
        }
        else if(task.clientInput.getUser1().getUserType() == User.Role.valueOf("Student"))
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

            }catch(Exception e){System.out.println("Create Student error for thread " + task.getID() +". Error follows: " + e);}
        }
        else if(task.clientInput.getUser1().getUserType() == User.Role.valueOf("Teacher"))
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

            }catch(Exception e){System.out.println("Create Teacher error for thread " + task.getID() + ". Error follows: " + e);}
        }
        else{System.out.println("Account creation opCode error on thread " + task.getID());}

    }

    static void modifyAccount(bTask task)
    {
        try {
            String query = "UPDATE Users SET Username = " + "\"" + task.clientInput.getUser1().getUserName() + "\"" + ", Pass = " + "\"" + task.clientInput.getUser1().getUserPassword() + "\"" + ", First_Name = " + "\"" + task.clientInput.getUser1().getUserFirstName() + "\"" + ", Last_Name = " + "\"" + task.clientInput.getUser1().getUserLastName() + "\"" + "WHERE User_ ID = " + task.clientInput.getUser1().getUserID();
            task.bridge.statement.executeUpdate(query);
        }catch(Exception e){System.out.println("Account modification error for thread " + task.getID() + ". Error follows: " + e);}
    }

    static void deleteAccount(bTask task)
    {
        try{
            String userTable = "DELETE FROM users WHERE User_ID = " + task.clientInput.getUser1().getUserID();

            if(task.clientInput.getUser1().getUserType() == User.Role.valueOf("Parent"))
            {
                String parentTable = "DELETE FROM parent WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String relatedTable = "DELETE FROM related_to WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String messageTable = "DELETE FROM message WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String recipTable = "DELETE FROM recipients WHERE User_ID = " + task.clientInput.getUser1().getUserID();

                task.bridge.statement.executeQuery(recipTable);
                task.bridge.statement.executeQuery(messageTable);
                task.bridge.statement.executeQuery(relatedTable);
                task.bridge.statement.executeQuery(parentTable);
                task.bridge.statement.executeQuery(userTable);

                System.out.println("Parent deleted by thread " + task.getID());
            }

            if(task.clientInput.getUser1().getUserType() == User.Role.valueOf("Student"))
            {
                String studentTable = "DELETE FROM student WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String relatedTable = "DELETE FROM related_to WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String messageTable = "DELETE FROM message WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String recipTable = "DELETE FROM recipients WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String attendsTable = "DELETE FROM attends WHERE User_ID = " + task.clientInput.getUser1().getUserID();

                task.bridge.statement.executeQuery(recipTable);
                task.bridge.statement.executeQuery(messageTable);
                task.bridge.statement.executeQuery(relatedTable);
                task.bridge.statement.executeQuery(studentTable);
                task.bridge.statement.executeQuery(attendsTable);
                task.bridge.statement.executeQuery(userTable);

                System.out.println("Student deleted by thread " + task.getID());
            }

            if(task.clientInput.getUser1().getUserType() == User.Role.valueOf("Teacher"))
            {
                String teacherTable = "DELETE FROM teacher WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String classTable = "DELETE FROM class WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String messageTable = "DELETE FROM message WHERE User_ID = " + task.clientInput.getUser1().getUserID();
                String recipTable = "DELETE FROM recipients WHERE User_ID = " + task.clientInput.getUser1().getUserID();

                task.bridge.statement.executeQuery(recipTable);
                task.bridge.statement.executeQuery(messageTable);
                task.bridge.statement.executeQuery(classTable);
                task.bridge.statement.executeQuery(teacherTable);
                task.bridge.statement.executeQuery(userTable);

                System.out.println("Teacher deleted by thread " + task.getID());
            }

        }catch (Exception e){System.out.println("Account deletion modification error for thread " + task.getID() + ". Error follows: " + e);}

    }

    static void teacherLink(bTask task)
    {
        String linkQuery = "INSERT INTO related_to VALUES(" + task.clientInput.getUser1().getUserID() + "," + task.clientInput.getUser2().getUserID();
        try {
            task.bridge.statement.executeQuery(linkQuery);

            Parent thisUser = new Parent();
            thisUser.setUserName(task.clientInput.getUser1().getUserName());
            thisUser.setUserPassword(task.clientInput.getUser1().getUserPassword());
            thisUser.setUserFirstName(task.clientInput.getUser1().getUserFirstName());
            thisUser.setUserLastName(task.clientInput.getUser1().getUserLastName());
            thisUser.setUserID(task.clientInput.getUser1().getUserID());
            thisUser.setUserType(User.Role.valueOf("Parent"));
            thisUser.setLinked(true);
            task.clientInput.setUser1(thisUser);
            //TODO: Add code to add this, and any other linked children, to parent's linkedlist

            task.clientInput.setResult(true);
            System.out.println("Teacher has successfully linked " + task.clientInput.getUser1().getUserName() + " with " + task.clientInput.getUser2().getUserName());
        }catch(Exception e){System.out.println("Error teacher linking on thread " + task.getID() + ". Error follows: " + e);}
    }

    static void teacherUnLink(bTask task)
    {
        String deleteLink = "DELETE FROM related_to WHERE Parent = " + task.clientInput.getUser1().getUserID() + ", Child = " + task.clientInput.getUser2().getUserID();
        try {
            task.bridge.statement.executeUpdate(deleteLink);
            System.out.println(task.clientInput.getUser1().getUserName() + " has been successfully unlinked from " + task.clientInput.getUser2().getUserName() + " on thread " + task.getID());

            //TODO: If the parent still exists in the related_to table, leave linked true. If not, set it false
        }catch(Exception e){System.out.println("Error un-linking student and parent on thread " + task.getID() + ". Error follows: " + e);}
    }
}
