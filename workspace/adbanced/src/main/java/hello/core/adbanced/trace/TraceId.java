package hello.core.adbanced.trace;

import java.util.UUID;

public class TraceId {
    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
//        앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0,8);
    }

    public TraceId createNextId(){
        return new TraceId(id, level +1);
    }

    public TraceId createPrevId(){
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
