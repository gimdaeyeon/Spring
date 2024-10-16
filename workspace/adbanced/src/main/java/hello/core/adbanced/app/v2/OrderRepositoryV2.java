package hello.core.adbanced.app.v2;

import hello.core.adbanced.trace.TraceId;
import hello.core.adbanced.trace.TraceStatus;
import hello.core.adbanced.trace.hellotrace.HelloTraceV1;
import hello.core.adbanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;

    public void save(TraceId traceId,String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepositoryV1.save()");
//        저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
