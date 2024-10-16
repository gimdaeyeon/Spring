package hello.core.adbanced.trace.hellotrace;

import hello.core.adbanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloTraceV2Test {


    @Test
    void begin_end(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus stats1 = trace.begin("hello1");
        TraceStatus stats2 = trace.beginSync(stats1.getTraceId(),"hello2");
        trace.exception(stats2, new IllegalStateException());
        trace.exception(stats1, new IllegalStateException());
    }
}