package burrisboard.device;

import java.io.Serializable;
import java.util.LinkedList;
//Represents a class, taught by a teacher, in a school
//Dependencies: School, Teacher, Student, java.util.LinkedList
//Dependents: Announcement, Teacher, Student
public class Class implements Serializable
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
    LinkedList<Announcement> classAnnouncements; //DESIGN MODIFICATION: Class required a container for announcements

    public void setName(String s)
    {
        this.name = s;
    }

    public void setSectionNumber(int id)
    {
        this.sectionNumber = id;
    }

    public void setGradeLevel(int level)
    {
        this.gradeLevel = level;
    }

    public int getSectionNumber()
    {
        return this.sectionNumber;
    }

    public String getName()
    {
        return this.name;
    }

    public int getGradeLevel()
    {
        return this.gradeLevel;
    }


    public void addStudent(Student studentToBeAdded)
    {
        studentRoster.add(studentToBeAdded);
    }

    public void dropStudent(Student studentToBeDropped)
    {
        studentRoster.remove(studentToBeDropped);
    }
}
