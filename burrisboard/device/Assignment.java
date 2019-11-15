package burrisboard.device;

//Represents an assignment
//Dependencies: Document, Announcement
//Dependents: None
class Assignment extends Document
{
    Assignment(int g, int a)
    {
        this.grade = g;
        this.attempts = a;
    }

    Assignment()
    { }

    int grade;
    int attempts;

    private static void submit(Student submittingStudent, Announcement submittingTo)
    {
        //submit the submitting student's assignment to the corresponding announcement that posted it
    }
}
