package burrisboard.device;

//Super class for every possible registered user type of the application
//Dependencies: Document
//Dependents: Message, Parent, Student, Teacher, Document
class User
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

    private static void viewDoc(Document docToView)
    {
        //retrieve document information, create, display
        //upon close, deconstruct
    }

    private static void modifyAccount() //DESIGN MODIFICATION 6: Parameter not needed
    {
        //change account details
    }

    private static void message(User userToMessage)
    {
        //send a message from this user as the sender and userToMessage as recipient
    }
}
