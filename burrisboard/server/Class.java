package burrisboard.server;

import java.util.LinkedList;
//Represents a class, taught by a teacher, in a school
//Dependencies: School, Teacher, Student, java.util.LinkedList
//Dependents: Announcement, Teacher, Student
class Class
{
    Class(String n, int num, School s, Teacher t, int g)
    {
        this.name = n;
        this.sectionNumber = num;
        this.school = s;
        this.teacher = t;
        this.gradeLevel = g;
    }

    Class()
    {}

    String name;
    int sectionNumber;
    School school;
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
