package burrisboard.device;

import java.io.Serializable;
import java.util.LinkedList;

//Super class for every possible registered user type of the application
//Dependencies: Document
//Dependents: Message, Parent, Student, Teacher, Document
public class User implements Serializable
{
    public enum Role
    {
        Parent,Student,Teacher;
    }

    public User(String uN, String uP, int uID, String uF, String uL, Role uT)
    {
        this.userName = uN;
        this.userPassword = uP;
        this.userID = uID;
        this.userFirstName = uF;
        this.userLastName = uL;
        this.userType = uT;
    }

    public User()
    {}

    String userName;
    String userPassword;
    int userID; //DESIGN MODIFICATION 5: int more conducive to ID numbers than String
    String userFirstName;
    String userLastName;
    Role userType;
    LinkedList<Message> messages = new LinkedList<>(); //DESIGN MODIFICATION

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

    public Role getUserType()
    {
        return this.userType;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setUserType(Role s)
    {
        this.userType = s;
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

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
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
