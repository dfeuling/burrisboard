package burrisboard.server;

import java.util.LinkedList;

//Represents a school -- each school has it's own server and database
//Dependencies: Teacher, Student
//Dependents: Class
class School
{
    School(String n, String c, String s, int t, int st)
    {
        this.name = n;
        this.city = c;
        this.state = s;
        this.teacherCount = t;
        this.studentCount = st;
    }

    School()
    {}

    String name;
    String city;
    String state;
    int teacherCount;
    LinkedList<Teacher> teacherRoster;
    int studentCount;
    LinkedList<Student> studentRoster; //DESIGN MODIFICATION 8: List type from Teacher to Student
}
