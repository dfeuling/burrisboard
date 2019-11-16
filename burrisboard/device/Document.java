package burrisboard.device;

import java.io.Serializable;

//Represents all documents, Parent for all potential assignment types
//Dependencies: User, bFile
//Dependents: Announcement, Message, User
class Document implements Serializable
{
    Document(User u, bFile f)
    {
        this.user = u;
        this.file = f;
    }

    Document()
    { }

    User user;
    bFile file; //DESIGN MODIFICATION 4: type File --> type bFile
}
