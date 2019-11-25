package burrisboard.server;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import burrisboard.device.bPackage;

//Burrisboard Tasks, thread class for our application
class bTask implements Runnable
{
    private Socket clientSocket;
    private int ID;
    SQLBridge bridge;

    public bTask(Socket socket, int threadID)
    {
        this.clientSocket = socket;
        this.ID = threadID;
    }

    public void run()
    {
        try{
            System.out.println("Client detection established.");
            //inputs and outputs
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            //cast the incoming object as a bPackage
            bPackage clientInput = (bPackage)in.readObject();
            System.out.println("Thread " + ID + " executing with operation " + clientInput.getOpCode());
            //SQLBridge object handles database connection and queries
            this.bridge = new SQLBridge(ID);
            bridge.connect();

            switch(clientInput.getOpCode())
            {
                case "Login":
                    bridge.resultSet = bridge.statement.executeQuery("SELECT * FROM burrisboard.users WHERE pass = " + "\"" + clientInput.getUser1().getUserPassword() + "\"" + " and Username = "  + "\"" + clientInput.getUser1().getUserName() + "\"");
                    if (bridge.resultSet.next())
                    {
                        
                        clientInput.setResult(true);
                    }
                    else
                        clientInput.setErrorMessage("Incorrect credentials.");

                    break;

                case "Create New Login":
                    bridge.resultSet = bridge.statement.executeQuery("SELECT * FROM burrisboard.users WHERE pass = " + "\"" + clientInput.getUser1().getUserPassword() + "\"" + " and Username = "  + "\"" + clientInput.getUser1().getUserName() + "\"");

                        clientInput.setResult(true);

                    break;
                //necessary functions:
                //create new login -- "Create Account"
                //delete account - "Delete Account"
                //modify account - "Modify Account"
                //login to existing account -- "Login"
                //parent linking to student  -- "Link Parent to Student"
                //student linking to parent -- "Link Student to Parent"
                //teacher linking parent and student - "Teacher Link"
                //unlink parent and student - "Teacher UnLink"
                //view a student's classes -- taken care of by login
                //view one particular class's announcements - "View All Announcements Class"
                //view announcements for all classes where student is enrolled - "View All Announcements Student"
                //view document - "View Document"
                //teacher creating class "Create Class"
                //user messaging - "Send Message"
                //view messages - "View Messages"
                //add student to class -- "Add Student"
                //update assignment attempt count -- "Update Assignment Attempt Count"
                //update assignment grade -- "Update Assignment Grade"


                //etc
            }

            out.writeObject(clientInput);

        }catch(Exception e){System.out.println("Thread error occurred. Error follows: " + e);}
    }
}
