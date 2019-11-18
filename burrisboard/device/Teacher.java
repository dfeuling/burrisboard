package burrisboard.device;

import java.util.LinkedList;

class Teacher extends User
{
    LinkedList<Class> coursesTaught; //DESIGN MODIFICATION 7: Fixed typo (<Classes> -> <Class>)
    LinkedList<Student> studentsTaught;

    public void linkUsers(Parent parentToLink, Student studentToLink)
    {
        //link a parent and a student
    }

    public void unLinkUsers(Parent parentToLink, Student studentToLink)
    {
        //unlink a parent and a student
    }
}
