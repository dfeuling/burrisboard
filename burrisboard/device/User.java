package burrisboard.device;

import java.io.Serializable;

//Super class for every possible registered user type of the application
//Dependencies: Document
//Dependents: Message, Parent, Student, Teacher, Document
public class User implements Serializable
{

    User(String uN, String uP, int uID, String uF, String uL)
    {
        this.userName = uN;
        this.userPassword = uP;
        this.userID = uID;
        this.userFirstName = uF;
        this.userLastName = uL;
    }

    User()
    {}

    String userName;
    String userPassword;
    int userID; //DESIGN MODIFICATION 5: int more conducive to ID numbers than String
    String userFirstName;
    String userLastName;

    public String getUserName()
    {
        return this.userName;
    }

    public String getUserPassword()
    {
        return this.userPassword;
    }

    public int getUserID()
    {
        return this.userID;
    }

    public String getUserFirstName()
    {
        return this.userFirstName;
    }

    public String getUserLastName()
    {
        return this.userLastName;
    }

    private void viewDoc(Document docToView)
    {
        //retrieve document information, create, display
        //upon close, deconstruct
    }

    private void modifyAccount() //DESIGN MODIFICATION 6: Parameter not needed
    {
        //change account details
    }

    private void message(User userToMessage)
    {
        //send a message from this user as the sender and userToMessage as recipient
    }

    public void login()
    {

    }

    public void createAccount()
    {

    }
}
