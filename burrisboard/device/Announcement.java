package burrisboard.device;
import java.io.Serializable;
import java.util.Date;

//Supports a Teacher's announcement data and functions
//Dependencies: Class, java.util.Date
//Dependents: Document
class Announcement implements Serializable
{
    Announcement(Date d, Class c, String b)
    {
        this.datePosted = d;
        this.bbclass = c;
        this.body = b;
    }

    Announcement()
    { }

    Date datePosted; //DESIGN MODIFICATION 2: Clarity
    Class bbclass; //DESIGN MODIFICATION 1: Java reserves 'class'
    String body;

    private static void post(Class classToPostTo)
    {
        //send the announcement to the correct DB table for the given class
    }
}
