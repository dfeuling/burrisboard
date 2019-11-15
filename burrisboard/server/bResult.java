package burrisboard.server;

import java.io.Serializable;

//Result from a server operation, reports back to client with operation outcome details
class bResult implements Serializable
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
