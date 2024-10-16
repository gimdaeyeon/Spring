package hello.core.adbanced.trace.logtrace;

import hello.core.adbanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);


}
