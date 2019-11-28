package burrisboard.server;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import burrisboard.device.User;
import burrisboard.device.bPackage;

//Burrisboard Tasks, thread class for our application
class bTask implements Runnable
{
    private Socket clientSocket;
    private int ID;
    SQLBridge bridge;
    bPackage clientInput;

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
            clientInput = (bPackage)in.readObject();
            System.out.println("Thread " + ID + " executing with operation " + clientInput.getOpCode());
            //SQLBridge object handles database connection and queries
            this.bridge = new SQLBridge(ID);
            bridge.connect();

            switch(clientInput.getOpCode())
            {
                case "Login":
                    burrisboard.server.Engine.Login(this);
                    break;

                case "Create Account":
                    burrisboard.server.Engine.createAccount(this);
                    break;

                case "Modify Account":
                    burrisboard.server.Engine.modifyAccount(this);
                    break;

                 /*case "Delete Account":
                    burrisboard.server.Engine.deleteAccount(this);
                    //delete from all tables based on user id
                    break;
                  */

                 /*case "Link Parent to Student":
                    burrisboard.server.Engine.linkParentToStudent(this);
                 */

                 default:
                     System.out.println("opCode not recognized by thread " + this.getID());

            }

            out.writeObject(clientInput);

        }catch(Exception e){System.out.println("Thread error occurred. Error follows: " + e);}
    }

    public int getID()
    {
        return this.ID;
    }
}
