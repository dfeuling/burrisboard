package burrisboard.server;
import java.util.LinkedList;

//All parent personas
//Dependencies: User, java.util.LinkedList;
//Dependents: Message, Parent, Student, Teacher
class Parent extends User
{
    LinkedList<Student> children;

    private static void requestLink(Student studentToLinkTo)
    {
        //parent's side of authentication handshake to link to a student - request
    }

    private static void acceptLinkRequest(Student studentToLinkTo)
    {
        //parent's side of authentication handshake to link to a student - accept
    }

    private static void viewClasses(Student studentToViewClasses)
    {
        //parent able to view list of linked student's classes
    }

    private static void viewDoc(Student studentToViewDoc)
    {

    }
}
