package burrisboard.device;

import java.io.Serializable;

//Super class for every possible registered user type of the application
//Dependencies: Document
//Dependents: Message, Parent, Student, Teacher, Document
public class User implements Serializable
{
    enum userType
    {
        parent,student,teacher;
    }

    public User(String uN, String uP, int uID, String uF, String uL)
    {
        this.userName = uN;
        this.userPassword = uP;
        this.userID = uID;
        this.userFirstName = uF;
        this.userLastName = uL;
    }

    public User()
    {}

    String userName;
    String userPassword;
    int userID; //DESIGN MODIFICATION 5: int more conducive to ID numbers than String
    String userFirstName;
    String userLastName;
    userType type;

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

    public userType getUserType()
    {
        return this.userType;
    }

    public void setUserType(String s)
    {
        this.userType = userType.s;
    }

    public void setUserName(String s)
    {
        this.userName = s;
    }

    public void setUserPassword(String s)
    {
        this.userPassword = s;
    }

    public void setUserID(int i)
    {
        this.userID = i;
    }

    public void setUserFirstName(String s)
    {
        this.userFirstName = s;
    }

    public void setUserLastName(String s)
    {
        this.userLastName = s;
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
