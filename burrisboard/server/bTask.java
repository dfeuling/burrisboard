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
            SQLBridge bridge = new SQLBridge(ID);
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
                //necessary functions:
                //create new login
                //parent linking to student
                //student linking to parent
                //teacher linking parent and student
                //view a student's classes
                //view one particular class's announcements
                //view announcements for all classes where student is enrolled
                //view document
                //teacher creating class
                //user messaging
                //etc
            }

            out.writeObject(clientInput);

        }catch(Exception e){System.out.println("Thread error occurred. Error follows: " + e);}
    }
}
