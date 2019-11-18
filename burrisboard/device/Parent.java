package burrisboard.device;
import java.util.LinkedList;

//All parent personas
//Dependencies: User, java.util.LinkedList;
//Dependents: Message, Parent, Student, Teacher
class Parent extends User
{
    LinkedList<Student> children;

    public void requestLink(Student studentToLinkTo)
    {
        //parent's side of authentication handshake to link to a student - request
    }

    public void acceptLinkRequest(Student studentToLinkTo)
    {
        //parent's side of authentication handshake to link to a student - accept
    }

    public void viewClasses(Student studentToViewClasses)
    {
        //parent able to view list of linked student's classes
    }

    public void viewDoc(Student studentToViewDoc)
    {

    }
}
