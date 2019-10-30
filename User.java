/*
TODO: Unique ID generator compliant with spec / design in object constructor
*/
package com.example.burrisboard;

public class User
{

    String userName;
    String userPassword;
    int userID;
    String userFirstName;
    String userLastName;

    User()
    {

    }

    User(String name, String pw, int ID, String first, String last)
    {
        this.userName = name;
        this.userPassword = pw;
        this.userID = ID;
        this.userFirstName = first;
        this.userLastName = last;
    }

    public static void viewDoc(Document document)
    {

    }

    public static void modifyAccount(User user)
    {

    }

    public static void message(User user)
    {

    }
}
