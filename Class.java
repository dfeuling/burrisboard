//design deviation 1: classes required a container for their announcements
package com.example.burrisboard;
import java.util.LinkedList;

public class Class
{
    Class(String name, int sectionNumber, School school, Teacher teacher, int gradeLevel)
    {
        this.name = name;
        this.sectionNumber = sectionNumber;
        this.school = school;
        this.teacher = teacher;
        this.gradeLevel = gradeLevel;
    }

    String name;
    int sectionNumber;
    School school;
    Teacher teacher;
    int gradeLevel;
    LinkedList<Student> studentRoster;
    LinkedList<Announcement> announcementList; //design deviation 1

    public static void addStudent(Student student)
    {

    }

    public static void dropStudent(Student student)
    {

    }
}
