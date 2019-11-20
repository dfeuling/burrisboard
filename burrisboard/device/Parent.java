package burrisboard.device;
import java.util.LinkedList;

//All parent personas
//Dependencies: User, java.util.LinkedList;
//Dependents: Message, Parent, Student, Teacher
class Parent extends User
{
    LinkedList<Student> children;
    boolean linked;

    public LinkedList<Student> getChildren()
    {
        return this.children;
    }

    public void addStudent(Student s)
    {
        children.add(s);
    }

    public void addStudents(Student[] s, int i)
    {
        for (int x = 0; x < i; x++)
            children.add(s[x]);
    }

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
