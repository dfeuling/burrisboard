package burrisboard.device;

import java.util.LinkedList;

class Teacher extends User {
    LinkedList<Class> coursesTaught; //DESIGN MODIFICATION 7: Fixed typo (<Classes> -> <Class>)
    LinkedList<Student> studentsTaught;

    public void addStudent(Student s)
    {
        studentsTaught.add(s);
    }

    public void addStudents(Student[] s, int i)
    {
        for(int x = 0 ; x < i ; x++)
        studentsTaught.add(s[x]);
    }

    public LinkedList<Student> getStudents()
    {
        return studentsTaught;
    }

    public void addClass(Class c)
    {
        coursesTaught.add(c);
    }

    public void addClasses(Class[] c, int i)
    {
        for(int x = 0 ; x < i ; x++)
            coursesTaught.add(c[x]);
    }

    public LinkedList<Class> getClasses()
    {
        return coursesTaught;
    }

    public void linkUsers(Parent parentToLink, Student studentToLink)
    {
        //link a parent and a student
    }

    public void unLinkUsers(Parent parentToLink, Student studentToLink)
    {
        //unlink a parent and a student
    }
}
