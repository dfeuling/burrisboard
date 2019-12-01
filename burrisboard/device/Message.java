package burrisboard.device;

import java.io.Serializable;

//Represents messages between users
//Dependencies: User, Document
//Dependents: User
public class Message implements Serializable
{
    public Message(User s, User r, String su, String b, Document a)
    {
        this.sender = s;
        this.recipient = r;
        this.subject = su;
        this.body = b;
        this.attachment = a;
    }

    public Message()
    {}


    User sender;
    User recipient;
    String subject;
    String body;
    Document attachment;
    int messageID;

    public void setMessageID(int id)
    {
        this.messageID = id;
    }

    public int getMessageID()
    {
        return messageID;
    }

    public void setSubject(String s)
    {
        this.subject = s;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public void setBody(String s)
    {
        this.body = s;
    }

    public String getBody()
    {
        return this.body;
    }

    public void setSender(User u)
    {
        this.sender = u;
    }

    public void setRecipient(User u)
    {
        this.recipient = u;
    }

    public void delete()
    {
        //remove from any lists
        //deconstruct
        //remove from database
    }

    public void reply()
    {
        //create a new message with this message's sender as recipient
    }

    public void forward()
    {
        //create a new message with this message's body as the new body
    }
}
