package burrisboard.server;

import java.util.LinkedList;

//All Student personas
//Dependencies: User, java.util.LinkedList;
//Dependents: Message, Parent, Student, Teacher
class Student extends User
{
    LinkedList<Parent> parents;
    LinkedList<Class> courses;

    private static void requestLink(Parent parentToLinkTo)
    {
        //student's side of authentication handshake to link to a parent - request
    }

    private static void acceptLinkRequest(Parent parentToLinkTo)
    {
        //student's side of authentication handshake to link to a parent - accept
    }
}
