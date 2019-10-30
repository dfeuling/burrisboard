package com.example.burrisboard;

public class Message
{
    User sender;
    User recipient;
    String subject;
    String body;
    Document attachment;

    Message()
    {

    }

    Message(User sender, User recipient, String subject, String body, Document attachment)
    {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
    }

    public static void delete()
    {

    }

    public static void reply()
    {

    }

    public static void forward()
    {

    }
}
