package burrisboard.server;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import burrisboard.device.bPackage;

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
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            bPackage clientInput = (bPackage)in.readObject();
            System.out.println("Thread " + ID + " executing with operation " + clientInput.getOpCode());
            SQLBridge bridge = new SQLBridge(ID);
            bridge.connect();

            switch(clientInput.getOpCode())
            {
                case "Login":
                    clientInput.getUser1().login();
                    bridge.resultSet = bridge.statement.executeQuery("SELECT First_Name FROM burrisboard.users WHERE pass = " + clientInput.getUser1().getUserPassword() + " and Username = "  + clientInput.getUser1().getUserName());
                    if (bridge.resultSet.next())
                        clientInput.setResult(true);
                    else
                        clientInput.setErrorMessage("Incorrect credentials.");
                    break;
            }

            out.writeObject(clientInput);

        }catch(Exception e){System.out.println("Thread error occured. Error follows: " + e);}
    }
}
