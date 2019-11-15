package burrisboard.server;

//Container for communication between client, server, and database, with supporting operations
class bPackage
{
    bPackage(String op, User u1, User u2, Document d, Message m, bFile f)
    {
        this.opCode = op;
        this.user1 = u1;
        this.user2 = u2;
        this.doc = d;
        this.message = m;
        this.File = f;
    }

    bPackage()
    { }

    String opCode;
    User user1;
    User user2;
    Document doc;
    Message message;
    bFile File;
}
