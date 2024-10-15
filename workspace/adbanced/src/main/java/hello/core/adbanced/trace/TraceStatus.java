package hello.core.adbanced.trace;


public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.message = message;
        this.startTimeMs = startTimeMs;
        this.traceId = traceId;
    }

    public String getMessage() {
        return message;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public TraceId getTraceId() {
        return traceId;
    }





}
