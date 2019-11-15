package burrisboard.server;

//Result from a server operation, reports back to client with operation outcome details
class bResult
{
    bResult(boolean r, String re)
    {
        this.result = r;
        this.reason = re;
    }

    bResult()
    {}

    boolean result;
    String reason;
}
