package com.example.burrisboard;
import java.util.Date;

public class Announcement
{
    Date date;
    Class aClass;
    String body;
    Document attachment;

    Announcement(Date date, Class aclass, String body, Document attachment)
    {
        this.date = date;
        this.aClass = aclass;
        this.body = body;
        this.attachment = attachment;
    }

    //post announcement in a given class's feed
    public static void post(Class aClass)
    {

    }

    //delete an announcement from a given class's feed
    public static void delete(Class aClass)
    {

    }
}
