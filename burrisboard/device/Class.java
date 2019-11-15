package burrisboard.device;

import java.util.LinkedList;
//Represents a class, taught by a teacher, in a school
//Dependencies: School, Teacher, Student, java.util.LinkedList
//Dependents: Announcement, Teacher, Student
class Class
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

    private static void addStudent(Student studentToBeAdded)
    {
        //add a student to the class
    }

    private static void dropStudent(Student studentToBeDropped)
    {
        //drop a student from the class
    }
}
