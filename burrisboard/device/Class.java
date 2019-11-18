package burrisboard.device;

import java.io.Serializable;
import java.util.LinkedList;
//Represents a class, taught by a teacher, in a school
//Dependencies: School, Teacher, Student, java.util.LinkedList
//Dependents: Announcement, Teacher, Student
class Class implements Serializable
{
    Class(String n, int num, Teacher t, int g)
    {
        this.name = n;
        this.sectionNumber = num;
        this.teacher = t;
        this.gradeLevel = g;
    }

    Class()
    {}

    String name;
    int sectionNumber;
    Teacher teacher;
    int gradeLevel;
    LinkedList<Student> studentRoster;

    public void addStudent(Student studentToBeAdded)
    {
        //add a student to the class
    }

    public void dropStudent(Student studentToBeDropped)
    {
        //drop a student from the class
    }
}
