package hello.core.adbanced.trace.template;

import hello.core.adbanced.trace.TraceStatus;
import hello.core.adbanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace logTrace) {
        this.trace = logTrace;
    }

    public T execute(String message){
        TraceStatus status = null;

        try {
            status = trace.begin(message);
            
//            로직 호출
            T result = call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status,e);
            throw e;
        }
    }

    protected abstract T call();
}
