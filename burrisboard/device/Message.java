package burrisboard.device;

//Represents messages between users
//Dependencies: User, Document
//Dependents: User
class Message
{
    Message(User s, User r, String su, String b, Document a)
    {
        this.sender = s;
        this.recipient = r;
        this.subject = su;
        this.body = b;
        this.attachment = a;
    }

    Message()
    {}


    User sender;
    User recipient;
    String subject;
    String body;
    Document attachment;

    public static void delete()
    {
        //remove from any lists
        //deconstruct
        //remove from database
    }

    public static void reply()
    {
        //create a new message with this message's sender as recipient
    }

    public static void forward()
    {
        //create a new message with this message's body as the new body
    }
}
