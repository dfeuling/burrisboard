package burrisboard.device;

import java.util.LinkedList;

//All Student personas
//Dependencies: User, java.util.LinkedList;
//Dependents: Message, Parent, Student, Teacher
public class Student extends User
{
    LinkedList<Parent> parents;
    LinkedList<Class> courses;

    public LinkedList<Parent> getParents()
    {
        return this.parents;
    }

    public LinkedList<Class> getClasses()
    {
        return this.courses;
    }

    public void addParent(Parent p)
    {
        parents.add(p);
    }

    public void addParents(Parent[] p, int i)
    {
        for(int x = 0 ; x < i ; x++)
            parents.add(p[x]);
    }

    public void addClass(Class c)
    {
        courses.add(c);
    }

    public void addClasses(Class[] c, int i)
    {
        for(int x = 0 ; x < i ; x++)
            courses.add(c[x]);
    }

    public void requestLink(Parent parentToLinkTo)
    {
        //student's side of authentication handshake to link to a parent - request
    }

    public void acceptLinkRequest(Parent parentToLinkTo)
    {
        //student's side of authentication handshake to link to a parent - accept
    }
}
